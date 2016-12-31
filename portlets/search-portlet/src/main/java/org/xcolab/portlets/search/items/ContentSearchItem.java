package org.xcolab.portlets.search.items;

import org.xcolab.client.search.pojo.SearchPojo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ContentSearchItem extends AbstractSearchItem {

    @Override
    public void init(SearchPojo pojo, String searchQuery) {

    }

    @Override
    public String getPrintName() {
        return "Content";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getLinkUrl() {
        try {
            return "/web/guest/resources/-/wiki/Main/" + URLEncoder.encode("", "UTF-8");
        } catch (UnsupportedEncodingException ignored) {

        }
        return "";
    }

    @Override
    public String getContent() {
        String content = "";
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }
}
