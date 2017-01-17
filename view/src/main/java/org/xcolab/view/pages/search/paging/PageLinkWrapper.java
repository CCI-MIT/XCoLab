package org.xcolab.view.pages.search.paging;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.view.pages.search.SearchBean;

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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/search");

        uriBuilder.queryParam("searchPhrase", searchPhrase);
        if (!StringUtils.isEmpty(searchLocation)) {
            uriBuilder.queryParam("searchLocation", searchLocation);
        }
        if (page > 1) {
            uriBuilder.queryParam("page", page);
        }
        return uriBuilder.toUriString();
    }

    public boolean isCurrent(SearchBean searchBean) {
        return searchPhrase.equals(searchBean.getSearchPhrase())
                && searchLocation.equals(searchBean.getSearchLocation())
                && page == searchBean.getPageNumber();
    }
}
