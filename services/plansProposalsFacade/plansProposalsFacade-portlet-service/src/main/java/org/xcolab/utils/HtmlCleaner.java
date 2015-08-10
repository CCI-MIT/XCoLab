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

    public static String cleanAll(String text) {
        return clean(text, Whitelist.none());
    }

    public static String cleanSome(String text) {
        return clean(text, Whitelist.basicWithImages());
    }

    public static String clean(String text, Whitelist whitelist) {
        Document doc = Jsoup.parse(text);
        doc = new Cleaner(whitelist).clean(doc);
        // Adjust escape mode, http://stackoverflow.com/questions/8683018/jsoup-clean-without-adding-html-entities
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.body().html();
    }
}
