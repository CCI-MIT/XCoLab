package org.xcolab.commons.html;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.nibor.autolink.LinkExtractor;
import org.nibor.autolink.LinkSpan;
import org.nibor.autolink.LinkType;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Utility class to sanitize and format HTML inputs.
 */
public final class HtmlUtil {

    private static final String EXISTING_LINKS_REGEX = "(<a[^>]*>.*?</a>|<img[^>]*>|<a[^>]*>)";
    private static final Pattern existingLinksPattern = Pattern.compile(EXISTING_LINKS_REGEX);
    private static final LinkExtractor linkExtractor = LinkExtractor.builder()
            .linkTypes(EnumSet.of(LinkType.URL, LinkType.WWW))
            .build();

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

    /**
     * Converts all detected URLs to HTML links (anchor tags).
     * @param content The input text.
     * @return Input text with URLs converted to HTML links.
     */
    public static String linkifyUrlsInText(String content) {

        StringBuilder result = new StringBuilder();

        int lastIndex = 0;
        for (LinkSpan link : linkExtractor.extractLinks(content)) {
            int startIndex = link.getBeginIndex();
            int endIndex = link.getEndIndex();

            String textBeforeLink = content.substring(lastIndex, startIndex);
            result.append(textBeforeLink);
            String url = content.substring(startIndex, endIndex);
            result.append(createLink(url, url));

            lastIndex = endIndex;
        }

        String textAfterLastLink = content.substring(lastIndex);
        result.append(textAfterLastLink);

        return result.toString();
    }

    /**
     * Converts detected URLs to HTML links (anchor tags), except for those that are part of an
     * HTML anchor or img tag.
     * @param content The input HTML string.
     * @return Input HTML string with URLs converted to HTML links.
     */
    public static String linkifyUrlsInHtml(String content) {
        Matcher existingLinksMatcher = existingLinksPattern.matcher(content);

        StringBuilder result = new StringBuilder();

        int lastIndex = 0;
        while (existingLinksMatcher.find()) {
            int startIndex = existingLinksMatcher.start();
            int endIndex = existingLinksMatcher.end();

            String beforeText = content.substring(lastIndex, startIndex);
            result.append(linkifyUrlsInText(beforeText));
            String existingLink = content.substring(startIndex, endIndex);
            result.append(existingLink);

            lastIndex = endIndex;
        }

        String afterText = content.substring(lastIndex, content.length());
        result.append(linkifyUrlsInText(afterText));

        return result.toString();
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
        tmp = linkifyUrlsInHtml(tmp);
        tmp = tmp.replaceAll("\"", "'");

        return tmp;
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
