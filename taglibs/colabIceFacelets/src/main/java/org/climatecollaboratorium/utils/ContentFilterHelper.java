package org.climatecollaboratorium.utils;

import org.xcolab.utils.HtmlUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContentFilterHelper {

    public static final int MAX_SHORTENED_LENGTH=80;

    public static String filterLineBreaks(String content) {
        return content.replaceAll("\n", " <br />\n");
    }
    
    public static String filterUrlEmbeddedLinks(String content) {
        Pattern pattern = Pattern.compile("\\[url=[^\\]]*\\][^\\[]*\\[/url\\]");
        Matcher matcher = pattern.matcher(content);
        StringBuilder strBuilder = new StringBuilder();
        int lastIndex = 0;
        while (matcher.find()) {
            strBuilder.append(content.substring(lastIndex, matcher.start()));
            String urlDef = matcher.group();
            int urlDefEnd = urlDef.indexOf("]");
            int urlDescEnd = urlDef.lastIndexOf("[");
            
            String url = urlDef.substring(5, urlDefEnd);
            String urlDesc = urlDef.substring(urlDefEnd + 1, urlDescEnd); 
            
            strBuilder.append(createLink(url, urlDesc));
            lastIndex = matcher.end();
        }
        strBuilder.append(content.substring(lastIndex));
        
        return strBuilder.toString();
    }
    
     public static String getShortString(String content) {

        //strip leading whitespace, breaks
        content = content.trim();
        content = content.replaceAll("\n+"," ");
        content = content.replaceAll("(?:<br\\s*/\\s*>)+"," ");


        Pattern p = Pattern.compile("((?:\\s*<br\\s*/\\s*>)*).*");
        Matcher m = p.matcher(content);
        m.find();
        if (!m.group(1).isEmpty()) {
            content = content.substring(m.group(1).length(),content.length());
        }

        //replace remaining breaks

        if (content.length() <= MAX_SHORTENED_LENGTH) {
            return content;
        }
        String remainder = content.substring(MAX_SHORTENED_LENGTH,content.length());
        content = content.substring(0,MAX_SHORTENED_LENGTH);

        Pattern pattern = Pattern.compile("\\[url=[^\\]]*\\][^\\[]*");
        Matcher matcher = pattern.matcher(content);


        if (matcher.find()) {
              int idx = remainder.indexOf("[/url]");
              content+=remainder.substring(idx,idx+6);
        }

        return content+"...";
    }

    private static String createLink(String url, String desc) {
        return createLink(url, desc, "");
    }

    private static String createLink(String url, String desc, String title) {
        if (! url.contains("http://")) {
            url = "http://" + url;
        }
        return "<a href='" + url + "' title='" + title + "' class='" + title + "'>" + desc + "</a>";
    }
    
    public static String filterContent(String content) {
        String tmp = content;
        if (! content.contains("<br")) {
            tmp = filterLineBreaks(tmp);
        }
        tmp = filterUrlEmbeddedLinks(tmp);
        tmp = HtmlUtil.linkifyUrls(tmp);
        
        return tmp;
    }
}
