package org.xcolab.utils.sitemonitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.util.AntPathMatcher;
import org.xcolab.utils.sitemonitor.config.CheckerConfig;
import org.xcolab.utils.sitemonitor.config.CheckerMapping;
import org.xcolab.utils.sitemonitor.config.CrawlConfig;
import org.xcolab.utils.sitemonitor.config.SiteMonitorConfig;

public class SiteMonitor {
	private final static Logger _log = Logger.getLogger(SiteMonitor.class);
	
	private final HttpClient client = HttpClients.createDefault();
	private final Pattern linkPattern = Pattern
			.compile("<a[^>]*href=['\\\"]([^'\\\"]*)['\\\"][^>]*>");
	private final Set<String> urlsProcessed = new HashSet<String>();
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	private final Map<String, PageChecker> pageCheckers = new HashMap<String, PageChecker>();
	private final List<CheckerMapping> checkerMappings = new ArrayList<CheckerMapping>();
	private final Set<String> oldErrors = new HashSet<String>();
	private final File oldErrorsFile = new File("oldErrors.log");
	private final Map<String, Set<DetectedError>> detectedErrorsByUrl = new HashMap<String, Set<DetectedError>>();
	private final StringBuilder emailBuilder = new StringBuilder();
	private final Queue<UrlToCrawl> urlsToCrawl = new LinkedList<UrlToCrawl>();
	
	public void go() throws Exception {
		// read monitor rules
		URL configUrl = SiteMonitor.class
				.getResource("/siteMonitor-config.xml");
		Serializer serializer = new Persister();

		final SiteMonitorConfig config = serializer.read(SiteMonitorConfig.class,
				new File(configUrl.getFile()));

		for (CheckerConfig checkerConfig : config.getCheckers()) {
			PageChecker checker = (PageChecker) SiteMonitor.class
					.getClassLoader().loadClass(checkerConfig.getClasz())
					.newInstance();
			checker.configure(checkerConfig.getConfiguration(),
					checkerConfig.getMessage(), this);

			pageCheckers.put(checkerConfig.getName(), checker);
		}
		for (CheckerMapping mapping: config.getCheckerMappings()) {
            _log.info("using checker: "+mapping.getChecker());
			if (! pageCheckers.containsKey(mapping.getChecker())) {
				throw new RuntimeException("Can't find page checker for name (in mapping) " + mapping.getChecker());
			}
		}
		checkerMappings.addAll(config.getCheckerMappings());


		if (oldErrorsFile.exists()) {
			BufferedReader bis = new BufferedReader(new FileReader(
					oldErrorsFile));
			String line = null;
			while ((line = bis.readLine()) != null) {
				oldErrors.add(line);
			}
			bis.close();
		}

		for (CrawlConfig crawlConfig : config.getCrawlerConfig()) {
			urlsToCrawl.add(new UrlToCrawl(crawlConfig.getUrl(), 0, crawlConfig));
		}
		while (! urlsToCrawl.isEmpty()) {
			doCrawl(urlsToCrawl.poll());
		}

		BufferedWriter oldErrorsWriter = new BufferedWriter(new FileWriter(
				oldErrorsFile));
		if (!detectedErrorsByUrl.isEmpty()) {

			for (Map.Entry<String, Set<DetectedError>> entry : detectedErrorsByUrl
					.entrySet()) {

				StringBuilder emailNewUrlErrors = new StringBuilder();
				for (DetectedError error : entry.getValue()) {
                    _log.info("FOUND ERROR: "+error.getMessage());

					if (!oldErrors.contains(getUrlCheckerErrorKey(entry.getKey(), error.getChecker()))) {

						emailNewUrlErrors.append("\t");
						emailNewUrlErrors.append(error.getChecker());
						emailNewUrlErrors.append("\n\t\t");
						emailNewUrlErrors.append(error.getMessage());
						emailNewUrlErrors.append("\n");

					}
					oldErrorsWriter.write(getUrlCheckerErrorKey(entry.getKey(),
							error.getChecker()));
					oldErrorsWriter.write('\n');
				}
				if (emailNewUrlErrors.length() > 0) {
					emailBuilder.append(entry.getKey());
					emailBuilder.append('\n');
					emailBuilder.append(emailNewUrlErrors.toString());
					emailBuilder.append("\n");
				}
			}

			oldErrorsWriter.flush();
			oldErrorsWriter.close();

		}
		
		// send email notifications
		if (config.getEmailNotificationConfig() != null && emailBuilder.length() > 0) {
			Email email = new SimpleEmail();
			
			email.setHostName(config.getEmailNotificationConfig().getSmtphost());
			if (config.getEmailNotificationConfig().getPort() > 0) {
				email.setSmtpPort(config.getEmailNotificationConfig().getPort());
			}
			if (config.getEmailNotificationConfig().isUseTsl()) {
				email.setSSLOnConnect(true);
			}
			email.setFrom(config.getEmailNotificationConfig().getFrom());
			email.setSubject(config.getEmailNotificationConfig().getSubject());
			email.setMsg(emailBuilder.toString());
			for (String to: config.getEmailNotificationConfig().getTo()) {
				email.addTo(to);
			}

			if (config.getEmailNotificationConfig().getSmtpuser() != null && 
					config.getEmailNotificationConfig().getSmtppassword() != null) {
				email.setAuthentication(config.getEmailNotificationConfig().getSmtpuser(), 
						config.getEmailNotificationConfig().getSmtppassword());
			}
			email.send();
            _log.info("Sent email.");
			
		}
	}

	private void doCrawl(UrlToCrawl urlToCrawl)  //String url, CrawlConfig crawlConfig, int level)
			throws IllegalStateException, IOException {
		String url = urlToCrawl.getUrl();
		CrawlConfig crawlConfig = urlToCrawl.getCrawlConfig();
		int level = urlToCrawl.getLevel();

		if (url.contains("#")) {
			// remove hash and everything after it
			url = url.substring(0, url.indexOf("#"));
		}
		if (urlsProcessed.contains(url) || crawlConfig.getRecursionLevel() < level) {
			//System.out.println("Won't crawl anymore: " + level + "\t" + crawlConfig.getRecursionLevel() + "\t: " + url + "\t" + urlsProcessed.contains(url));
			return;
		}
		urlsProcessed.add(url);

		int slashPos = url.indexOf("/", 9);
		String siteUrl = slashPos < 0 ? url : url.substring(0,
				url.indexOf("/", 9));
		String currentPath = slashPos < 0 ? "" : url
				.substring(siteUrl.length());
        //urlencode whitespace in urls
        url = url.replaceAll(" ", "%20");
		_log.info("Crawling url " + url + "\tlevel: " + level + " of " + crawlConfig.getRecursionLevel());
		HttpResponse response = client.execute(new HttpGet(url));
		String pageContent = IOUtils
				.toString(response.getEntity().getContent());
		response.getEntity().getContent().close();

		// process page with checkers
		for (CheckerMapping checkerMapping : checkerMappings) {
			boolean useChecker = false;
			for (String urlPattern : checkerMapping.getUrl()) {
				useChecker = pathMatcher.match(urlPattern, currentPath);
				if (useChecker)
					break;
			}
			if (useChecker) {
				PageChecker checker = pageCheckers.get(checkerMapping
						.getChecker());
				if (!checker.isPageValid(pageContent, response)) {
					_log.error("page is invalid! " + checker.getMessage());
					Set<DetectedError> urlErrors = detectedErrorsByUrl.get(url);
					if (urlErrors == null) {
						urlErrors = new HashSet<DetectedError>();
						detectedErrorsByUrl.put(url, urlErrors);
					}
					urlErrors.add(new DetectedError(
							checkerMapping.getChecker(), checker.getMessage()));
				}
			}
		}

		// if maximum recursion depth has been reached - don't go deeper
		if (crawlConfig.getRecursionLevel() < level + 1) {
			return;
		}

		// process all of the links on the page
		Matcher linkMatcher = linkPattern.matcher(pageContent);

		while (linkMatcher.find()) {
			String linkHref = linkMatcher.group(1);
			String newPageUrl = linkHref;
			if (linkHref.startsWith("/")) {
				newPageUrl = siteUrl + linkHref;
			} else if (linkHref.startsWith("http://")
					|| linkHref.startsWith("https://")) {
				// take link as it is
			} else if (linkHref.contains(":")) {
				// there is a different protocol used (mailto/javascript etc.) ,
				// ignore it
				continue;
			} else {
				// we have a relative url
				newPageUrl = url + linkHref;
			}

			if (!newPageUrl.startsWith(siteUrl)) {
				// external link, ignore it
				continue;
			}

			newPageUrl = newPageUrl.trim();
			// check if any of crawler link patterns, if a match is found,
			// proceed with crawl

			for (String linkPatter : crawlConfig.getLinkPatterns()) {
                int positionOf10thSlash = newPageUrl.indexOf("/", 9);
                if (positionOf10thSlash != -1) {
                    String path = newPageUrl.substring(positionOf10thSlash);
                    if (pathMatcher.match(linkPatter, path)) {
                        urlsToCrawl.add(new UrlToCrawl(newPageUrl, level + 1, crawlConfig));
                        break;
                    } else {
                        //System.out.println("Url rejected: " + path);
                    }
                }
			}

		}
		

	}

	private String getUrlCheckerErrorKey(String url, String checkerName) {
		return url +" " + checkerName;
	}

	public static void main(String[] args) throws Exception {
		try {
			new SiteMonitor().go();
		}
		catch (Exception e) {
            e.printStackTrace();
			_log.error(e);
		}
	}
	
	public PageChecker getChecker(String name) {
		return pageCheckers.get(name);
	}
}
