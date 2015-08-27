package org.xcolab.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 * Utility class to sanitize HTML inputs.
 *
 * Created by johannes on 8/10/15.
 */
public class HtmlCleaner {

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
}
