package org.xcolab.utils;

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
 *
 * Created by johannes on 8/10/15.
 */
public class HtmlUtil {

    /**
     * Removes all html form the input string
     * @param text unsafe input
     * @return input string without any html tags
     */
    public static String cleanAll(String text) {
        return clean(text, Whitelist.none());
    }

    /**
     * Removes unsafe and structural html from the input string
     * @param text unsafe input
     * @return input string without dangerous and structural html tags
     */
    public static String cleanMost(String text) {
        return clean(text, Whitelist.simpleText());
    }

    /**
     * Removes unsafe html from the input string
     * @param text unsafe input
     * @return input string without dangerous html tags
     */
    public static String cleanSome(String text) {
        final Whitelist whitelist = Whitelist.basicWithImages();
        whitelist.addAttributes("img", "style");
        whitelist.preserveRelativeLinks(true);
        return clean(text, whitelist);
    }

    /**
     * Removes html from the input string, allowing only tags as indicated by the whitelist.
     * @param text the unsafe input text
     * @param whitelist a list of allowed tags
     * @return input text without html tags other than those on the whitelist
     */
    public static String clean(String text, Whitelist whitelist) {
        Document doc = Jsoup.parse(text);
        doc = new Cleaner(whitelist).clean(doc);
        // Adjust escape mode, http://stackoverflow.com/questions/8683018/jsoup-clean-without-adding-html-entities
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.body().html();
    }

    public static String createLink(String url, String desc) {
        if (! url.contains("http://")) {
            url = "http://" + url;
        }
        return "<a rel='nofollow' href='" + url + "'>" + desc + "</a>";
    }

    public static String filterLineBreaks(String content) {
        return content.replaceAll("\n", " <br />\n");
    }

    public static String filterAndFormatContent(String content) {
        String tmp = content;
        if (! content.contains("<br")) {
            tmp = filterLineBreaks(tmp);
        }
        tmp = linkifyUrls(tmp);
        tmp = tmp.replaceAll("\"", "'");

        return tmp;
    }

    public static String linkifyUrls(String content) {
        Pattern existingLinksPattern = Pattern.compile("(<a[^>]*>[^<]*</a>|<img[^>]*>)");
        Matcher existingLinksMatcher = existingLinksPattern.matcher(content);

        List<Integer[]> linksBeginEnd = new ArrayList<>();
        while (existingLinksMatcher.find()) {
            linksBeginEnd.add(new Integer[] {existingLinksMatcher.start(), existingLinksMatcher.end()});
        }

        Pattern pattern = Pattern.compile("(http://|www\\.)([{\\w-]*\\.)+\\w{1,4}([^\\s]*)");
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
                if(linkText.equals(""))
                    linkWithNoFollow = createLink(linkURL, linkURL);
                else
                    linkWithNoFollow = createLink(linkURL, linkText);
                aTagElement.after(linkWithNoFollow);
                aTagElement.remove();
            }
        }
        return document;
    }
}
