package org.climatecollaboratorium.plans.utils;

public class ContentFilteringHelper {
    
    public static String removeStylingFromHTMLContent(String content) {

        content = removeTagWithContents(content, "xml");
        content = removeTagWithContents(content, "style");
        content = removeTagWithContents(content, "style");
        //content = removeAttribute(content, "style");
        content = removeAttribute(content, "class");
        content = removeAttribute(content, "border");
        content = removeTagLeaveContents(content, "font");
        content = removeTagLeaveContents(content, "sub");
        
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
    
    private static String removeAttribute(String content, String name) {
        return content.replaceAll(name + "=\"[^\"]*\"", "");
        
    }
    
}
