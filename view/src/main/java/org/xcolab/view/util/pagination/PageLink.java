package org.xcolab.view.util.pagination;

import org.springframework.web.util.UriComponentsBuilder;

public class PageLink {

    private final String text;
    private final int currentPage;
    private final int page;
    private final String url;

    public PageLink(String text, UriComponentsBuilder baseUri, int currentPage, int page) {
        this.text = text;
        this.currentPage = currentPage;
        this.page = page;
        url = baseUri.cloneBuilder().queryParam("page", page).build().toUriString();
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public boolean getIsCurrent() {
        return currentPage == page;
    }
}
