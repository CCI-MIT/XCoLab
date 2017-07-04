package org.xcolab.view.pages.search.items;

import org.xcolab.client.search.pojo.SearchPojo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ContentSearchItem extends AbstractSearchItem {

    private String searchQuery;
    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        this.searchQuery = searchQuery;
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
            return "/wiki/" + URLEncoder.encode("", "UTF-8");
        } catch (UnsupportedEncodingException ignored) {

        }
        return "";
    }


    @Override
    public String getContent() {
        return super.getContent("",searchQuery);
    }
}
