package org.xcolab.portlets.admintasks;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentFilteringHelper {
    
    private static Map<String, String[]> replacementTags = new HashMap<String, String[]>();
    
    static {
        replacementTags.put("h1", new String[] {"p", "strong"});
        replacementTags.put("h2", new String[] {"p", "strong"});
        replacementTags.put("h3", new String[] {"p", "strong"});
        replacementTags.put("h4", new String[] {"p", "strong"});
        replacementTags.put("h5", new String[] {"p", "strong"});
        replacementTags.put("h6", new String[] {"p", "strong"});
        replacementTags.put("sub", new String[] {"span"});
        replacementTags.put("sup", new String[] {"span"});
    }
    
    public static String removeStylingFromHTMLContent(String content) {

        content = removeTagWithContents(content, "xml");
        content = removeTagWithContents(content, "style");
        content = removeAttribute(content, "style");
        content = removeAttribute(content, "border");
        content = removeTagLeaveContents(content, "font");
        
        for (Map.Entry<String, String[]> entry: replacementTags.entrySet()) {
            content = replaceTagWithTag(content, entry.getKey(), entry.getValue());
        }
        
        return content;
    }

    private static String removeTagWithContents(String content, String tagName) {
        int startPos = content.indexOf("<" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf("</" + tagName, startPos);
            endPos = content.indexOf(">", endPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("<" + tagName);
        }
            
        
        return content;
    }
    
    private static String removeTagLeaveContents(String content, String tagName) {
        int startPos = content.indexOf("<" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf(">", startPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("<" + tagName);
        }
        startPos = content.indexOf("</" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf(">", startPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("</" + tagName);
        }
            
        
        return content;
    }
    
    private static String replaceTagWithTag(String content, String fromTag, String...toTags) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("<(/?)" + fromTag + "[^>]*>");
        Matcher m = p.matcher(content);
        int prev = 0;
        while (m.find()) {
            // copy everything from previous tag end till < character
            sb.append(content, prev, m.start());
            // if there is 1 group then it means it's closing tag
            for (String toTag: toTags) {
                sb.append("<");
                if (m.group(1).length() > 0) {
                    sb.append("/");
                }
                sb.append(toTag);
                sb.append(">");
            }
            prev = m.end();
        }
        sb.append(content, prev, content.length());
        
        return sb.toString();
    }
    
    private static String removeAttribute(String content, String name) {
        return content.replaceAll(name + "=\"[^\"]*\"", "");
        
    }
    
    public static void main(String[] args) {
        String tmp = replaceTagWithTag("<h1>fasdfasdfasdf</h1> asdfasdfasdfasdfa <span>..........</span>fasdfasdfasdf", "h1", "p");
        System.out.println(tmp);
        tmp = replaceTagWithTag(tmp, "span", "p", "strong");
        System.out.println(tmp);
    }
    
}
