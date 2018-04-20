package org.xcolab.view.util.pagination;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a paged navigation controller.
 *
 * It is used in the pagination tag to display a pagination menu.
 * The first page is page 1 while the last page is equal to the number of pages.
 */
public class PageNavigation {

    private static final int PAGER_SIZE = 5;

    private final UriComponentsBuilder baseUrl;
    private final int currentPage;
    private final int numPages;
    private final String pageParamName;

    public PageNavigation(String basePath, int currentPage, int numPages) {
        this(UriComponentsBuilder.fromUriString(basePath), currentPage, numPages);
    }

    public PageNavigation(UriComponentsBuilder baseUrl, int currentPage, int numPages) {
        this(baseUrl, currentPage, numPages, "page");
    }

    public PageNavigation(UriComponentsBuilder baseUrl, int currentPage, int numPages,
            String pageParamName) {
        this.baseUrl = baseUrl;
        this.currentPage = currentPage;
        this.numPages = numPages;
        this.pageParamName = pageParamName;
    }

    public boolean getShouldShowNavigation() {
        return numPages > 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getNumPages() {
        return numPages;
    }

    public boolean getIsFirstPage() {
        return currentPage == 1;
    }

    public boolean getIsLastPage() {
        return currentPage == numPages;
    }

    public boolean getIsFirstPageShown() {
        final int lowestPageShownIndex = currentPage - PAGER_SIZE / 2;
        return lowestPageShownIndex <= 1;
    }

    public boolean getIsLastPageShown() {
        final int highestPageShownIndex = currentPage + PAGER_SIZE / 2;
        return highestPageShownIndex >= numPages;
    }

    public PageLink getFirstPageLink() {
        return createPageLink(1);
    }

    public PageLink getLastPageLink() {
        return createPageLink(numPages);
    }

    public PageLink getPreviousPageLink() {
        return createPageLink(currentPage - 1);
    }

    public PageLink getNextPageLink() {
        return createPageLink(currentPage + 1);
    }

    public List<PageLink> getPageLinks() {
        List<PageLink> pageLinks = new ArrayList<>();
        int firstPageShownIndex = Math.max(1, Math.min(currentPage - PAGER_SIZE / 2, numPages - PAGER_SIZE + 1));
        int lastPageShownIndex = Math.min(numPages, firstPageShownIndex + PAGER_SIZE -1);
        for (int i = firstPageShownIndex; i <= lastPageShownIndex; i++) {
            pageLinks.add(createPageLink(i));
        }
        return pageLinks;
    }

    private PageLink createPageLink(int page) {
        String pageUrl = baseUrl.cloneBuilder().queryParam(pageParamName, page).build().toUriString();;
        return new PageLink(pageUrl, currentPage, page);
    }
}
