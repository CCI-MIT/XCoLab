package org.xcolab.portlets.search.paging;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.search.SearchBean;

/**
 * Created by johannes on 12/14/15.
 */
public class PageLinkWrapper {

    private final String text;
    private final int page;
    private final String searchPhrase;
    private final String searchLocation;

    public PageLinkWrapper(String text, int page, String searchPhrase, String searchLocation) {
        this.text = text;
        this.page = page;
        this.searchPhrase = searchPhrase;
        this.searchLocation = searchLocation;
    }

    public String getLinkText() {
        if (StringUtils.isEmpty(text)) {
            return Integer.toString(page);
        }
        return text;
    }

    public int getPage() {
        return page;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public String getLinkUrl() {
        String url = "/web/guest/search/-/search/for/" + searchPhrase;
        if (!StringUtils.isEmpty(searchLocation)) {
            url += "/in/" + searchLocation;
        }
        if (page > 1) {
            url += "/page/" + page;
        }
        return url;
    }

    public boolean isCurrent(SearchBean searchBean) {
        return searchPhrase.equals(searchBean.getSearchPhrase())
                && searchLocation.equals(searchBean.getSearchLocation())
                && page == searchBean.getPageNumber();
    }
}
