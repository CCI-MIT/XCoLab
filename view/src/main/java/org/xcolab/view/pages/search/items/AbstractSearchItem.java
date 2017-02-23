package org.xcolab.view.pages.search.items;

import org.jsoup.Jsoup;

import org.xcolab.client.search.pojo.SearchPojo;

public abstract class AbstractSearchItem {
    private static final String HTML_CLEAN_UP_REGEXP = "<[^>]*>";

    public final static int MAX_CONTENT_LENGTH = 255;
    public final static int B_TAG_LENGHT = 8;

    public abstract String getPrintName();

    public abstract void init(SearchPojo pojo, String searchQuery);

    public abstract String getTitle();

    public abstract String getLinkUrl();
    public abstract String getContent();
    public String getContent(String contentz, String searchQuery){
        String content = highlight(contentz,searchQuery);
        int maxContentLenght = Math.min(content.length(), MAX_CONTENT_LENGTH);
        int tolerance = maxContentLenght;
        if(content.length() > MAX_CONTENT_LENGTH +B_TAG_LENGHT){
            tolerance = maxContentLenght +B_TAG_LENGHT;
        }
        String finalString = content.substring(maxContentLenght-B_TAG_LENGHT,tolerance);
        if(finalString.contains("b>")){
            if(finalString.contains("<b>")){
                return content.substring(0,tolerance - B_TAG_LENGHT)+ " ...";
            }else{
                return content.substring(0,tolerance)+ " ...";
            }

        }
        return content.substring(0, maxContentLenght) + " ...";
    };

    public static String html2text(String html) {
        if(html == null) {
            return null;
        }else {
            return Jsoup.parse(html).text();
        }
    }
    public String highlight(String content, String queryToHighlight) {

        if (content == null) {return null;}
        content = html2text(content);
        if (queryToHighlight == null) {return content;}
        String[] tokensInUserContent = queryToHighlight.split("[\\p{Punct}\\s]+");
        if (tokensInUserContent != null) {
            for(String token : tokensInUserContent) {
                content = content.replace(token, "<b>"+token+"</b>");
            }
        }
        //content = content.replace(queryToHighlight, "<b>"+queryToHighlight+"</b>");

        return content;
    }

    public static AbstractSearchItem newInstance(Class<? extends AbstractSearchItem> clazz) {
        if (ContentSearchItem.class == clazz) {
            return new ContentSearchItem();
        }
        if (ContestSearchItem.class == clazz) {
            return new ContestSearchItem();
        }
        if (DiscussionSearchItem.class == clazz) {
            return new DiscussionSearchItem();
        }
        if (ProposalSearchItem.class == clazz) {
            return new ProposalSearchItem();
        }
        if (UserSearchItem.class == clazz) {
            return new UserSearchItem();
        }
        throw new IllegalArgumentException("Invalid class name provided to factory method: " + clazz.getName());
    }

    public boolean isVisible() {
        return true;
    }
}
