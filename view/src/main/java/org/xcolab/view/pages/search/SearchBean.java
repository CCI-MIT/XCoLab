package org.xcolab.view.pages.search;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.search.paging.SearchDataPage;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class SearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String searchLocation;
    private final int pageNumber;
    private String searchPhrase = "";
    private final SearchDataPage dataPage;

    public SearchBean(String searchPhrase, String searchLocation, Integer pageNumber) {
        this.pageNumber = pageNumber == null || pageNumber < 1 ? 1 : pageNumber;
        this.searchLocation = searchLocation == null ? "" : searchLocation;
        if (StringUtils.isEmpty(searchPhrase)) {
            this.searchPhrase = "";
        } else {
            try {
                this.searchPhrase = HtmlUtil.cleanAll(URLDecoder.decode(searchPhrase, "UTF-8"));
            } catch (UnsupportedEncodingException ignored) {

            }
        }

        this.dataPage = new SearchDataPage(this.pageNumber, this.searchPhrase, this.searchLocation);
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public List<SearchResultItem> getItems() {
        return dataPage.getItems();
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public SearchDataPage getDataPage() {
        return dataPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public SearchItemType[] getItemTypes() {
        return SearchItemType.values();
    }

    public SearchItemType getSelectedItemType() {
        if (StringUtils.isEmpty(searchLocation) || searchLocation.equals("FULL_SITE")) {
            return null;
        }
        return SearchItemType.valueOf(searchLocation);
    }

    public boolean getShowBlogReference() {
        return !ConfigurationAttributeKey.BLOG_URL.get().isEmpty();
    }

    public String getSearchBlogURL() {
        return ConfigurationAttributeKey.BLOG_URL.get() + "/?submit=Search&s=" + getSearchPhrase();
    }
}
