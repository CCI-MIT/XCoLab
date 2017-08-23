package org.xcolab.util.html;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to sanitize and format HTML inputs.
 */
public final class HtmlUtil {

    private HtmlUtil() { }

    /**
     * Removes all html form the input string
     * @param text unsafe input
     * @return input string without any html tags
     */
    public static String cleanAll(String text) {
        return clean(text, Whitelist.none(), "");
    }

    /**
     * Removes unsafe and structural html from the input string
     * @param text unsafe input
     * @return input string without dangerous and structural html tags
     */
    public static String cleanMost(String text) {
        return clean(text, Whitelist.simpleText(), "");
    }

    /**
     * Removes unsafe html from the input string
     * @param text unsafe input
     * @param baseUri used to evaluate relative links
     * @return input string without dangerous html tags
     */
    public static String cleanSome(String text, String baseUri) {
        final Whitelist whitelist = Whitelist.basicWithImages();
        whitelist.addAttributes("img", "style");
        whitelist.addAttributes("a", "name");
        whitelist.addAttributes("a", "class");
        return clean(text, whitelist, baseUri);
    }

    /**
     * Removes html from the input string, allowing only tags as indicated by the whitelist.
     * @param text the unsafe input text
     * @param whitelist a list of allowed tags
     * @param baseUri used to evaluate relative links
     * @return input text without html tags other than those on the whitelist
     */
    public static String clean(String text, Whitelist whitelist, String baseUri) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }
        Document doc = Jsoup.parse(text, baseUri);
        doc = new Cleaner(whitelist).clean(doc);
        // Adjust escape mode, http://stackoverflow.com/questions/8683018/jsoup-clean-without-adding-html-entities
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.body().html();
    }

    /**
     * This method unescapes all of entities that are stored in the database, that are in xhml format
     * but those are not all html4 entities, the quote, is escaped as &apos; instead of &quote; so this clears it up
     * @param body the text to be cleaned
     * @return unescaped text for emails
     */
    public static String decodeHTMLEntitiesForEmail(String body){
        return StringEscapeUtils.unescapeHtml4(body).replace("&apos;","'");

    }

    public static String makeRelativeLinksAbsolute(String html, String baseUrl) {
        return html.replaceAll("(href=[\"\'])/", "$1" +  baseUrl + "/");
    }

    public static String createLink(String url, String desc) {
        if (! url.contains("http://") && ! url.contains("https://")) {
            url = "http://" + url;
        }
        return "<a rel='nofollow' href='" + url + "'>" + desc + "</a>";
    }

    public static String addHtmlLineBreaks(String content) {
        return content.replaceAll("\n", " <br />\n");
    }

    public static String filterAndFormatContent(String content) {
        String tmp = content;
        if (! content.contains("<br")) {
            tmp = addHtmlLineBreaks(tmp);
        }
        tmp = linkifyUrls(tmp);
        tmp = tmp.replaceAll("\"", "'");

        return tmp;
    }

    public static String linkifyUrls(String content) {
        Pattern existingLinksPattern = Pattern.compile("(<a[^>]*>[^<]*</a>|<img[^>]*>|<a[^>]*>)");
        Matcher existingLinksMatcher = existingLinksPattern.matcher(content);

        List<Integer[]> linksBeginEnd = new ArrayList<>();
        while (existingLinksMatcher.find()) {
            linksBeginEnd.add(new Integer[] {existingLinksMatcher.start(), existingLinksMatcher.end()});
        }

        Pattern pattern = Pattern.compile("(http://|https://|www\\.)([{\\w-]*\\.)+\\w{1,4}([^\\s]*)");
        Matcher matcher = pattern.matcher(content);
        StringBuilder strBuilder = new StringBuilder();

        int lastIndex = 0;
        while (matcher.find()) {
            // check if this link isn't already part of existing <a href=...
            boolean partOfAnchor = false;
            for (Integer[] linkStartEnd: linksBeginEnd) {
                if (matcher.start() > linkStartEnd[0] && matcher.start() < linkStartEnd[1]) {
                    partOfAnchor = true;
                    break;
                }
            }
            if (partOfAnchor) {
                continue;
            }

            strBuilder.append(content.substring(lastIndex, matcher.start()));
            String url = content.substring(matcher.start(), matcher.end());
            strBuilder.append(createLink(url, url));

            strBuilder.append(content.substring(matcher.end(), matcher.end()));
            lastIndex = matcher.end();
        }

        strBuilder.append(content.substring(lastIndex));
        return strBuilder.toString();
    }

    public static Document addNoFollowToLinkTagsInDocument(Document document){
        for (Element aTagElement : document.select("a")) {
            if (!aTagElement.attr("rel").equals("nofollow")) {
                String linkURL = aTagElement.attr("href");
                String linkText = aTagElement.text();
                String linkWithNoFollow;
                if(linkText.equals("")) {
                    linkWithNoFollow = createLink(linkURL, linkURL);
                } else {
                    linkWithNoFollow = createLink(linkURL, linkText);
                }
                aTagElement.after(linkWithNoFollow);
                aTagElement.remove();
            }
        }
        return document;
    }
}
