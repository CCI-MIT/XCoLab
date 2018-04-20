package org.xcolab.view.util.pagination;

public class PageLink {

    private final String url;
    private final int currentPage;
    private final int page;

    public PageLink(String url, int currentPage, int page) {
        this.url = url;
        this.currentPage = currentPage;
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public boolean getIsCurrent() {
        return currentPage == page;
    }

    public int getPage() {
        return page;
    }
}
