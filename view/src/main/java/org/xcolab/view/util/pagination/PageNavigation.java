package org.xcolab.view.util.pagination;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class PageNavigation {

    private static final int PAGER_RANGE = 5;

    private final UriComponentsBuilder baseUrl;
    private final int currentPage;
    private final int numPages;

    public PageNavigation(String basePath, int currentPage, int numPages) {
        this(UriComponentsBuilder.fromPath(basePath), currentPage, numPages);
    }

    public PageNavigation(UriComponentsBuilder baseUrl, int currentPage, int numPages) {
        this.baseUrl = baseUrl;
        this.currentPage = currentPage;
        this.numPages = numPages;
    }

    public boolean getShouldShowNavigation() {
        return numPages > 1;
    }

    public List<PageLink> getPageLinks() {
        List<PageLink> pageLinks = new ArrayList<>();
        pageLinks.add(new PageLink("<< First", baseUrl, currentPage, 1));
        if (currentPage > 1) {
            pageLinks.add(new PageLink("< Previous", baseUrl, currentPage, currentPage - 1));
        }
        for (int i = Math.max(1, currentPage - PAGER_RANGE), stop =
                Math.min(numPages, currentPage + PAGER_RANGE); i <= stop; i++) {
            pageLinks.add(new PageLink("", baseUrl, currentPage, i));
        }
        if (currentPage < numPages) {
            pageLinks.add(new PageLink("Next >", baseUrl, currentPage, currentPage + 1));
        }
        pageLinks.add(new PageLink("Last >>", baseUrl, currentPage, numPages));
        return pageLinks;
    }
}
