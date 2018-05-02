package org.xcolab.view.pages.members.users.utils;

import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.view.util.pagination.SortFilterPage;

public class MembersNavigation {

    private static final String BASE_URL = "/members";
    private static final boolean DEFAULT_SORT_IS_ASCENDING = false;

    private final UriBuilder baseUriBuilder = new UriBuilder(
            UriComponentsBuilder.fromUriString(BASE_URL));

    private final SortFilterPage sortFilterPage;
    private final String memberCategoryParam;
    private final String filter;

    public MembersNavigation(SortFilterPage sortFilterPage, String memberCategoryParam,
            String filter) {

        this.sortFilterPage = sortFilterPage;
        this.memberCategoryParam = memberCategoryParam;
        this.filter = filter;
    }

    public String getCurrentUrl() {
        return getUrl(sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(),
                memberCategoryParam, filter);
    }

    public String getBlankFilterUrl() {
        return getUrl(sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(),
                memberCategoryParam, null);
    }

    public String getSortUrl(String newSortColumn) {
        final boolean isCurrentSortColumn = newSortColumn.equalsIgnoreCase(
                sortFilterPage.getSortColumn());
        final boolean sortAscending = isCurrentSortColumn ? !sortFilterPage.isSortAscending()
                : DEFAULT_SORT_IS_ASCENDING;
        return getUrl(newSortColumn, sortAscending, memberCategoryParam, filter);
    }

    public String getCategoryUrl(String newCategory) {
        return getUrl(sortFilterPage.getSortColumn(), sortFilterPage.isSortAscending(), newCategory, filter);
    }

    private String getUrl(String sortColumn, Boolean sortAscending, String memberCategoryParam, String filter) {
        return baseUriBuilder.cloneBuilder()
                .optionalQueryParam("sortColumn", sortColumn)
                .optionalQueryParam("filter", filter)
                .optionalQueryParam("sortAscending", sortAscending)
                .optionalQueryParam("memberCategory", memberCategoryParam)
                .buildAndExpandString();
    }
}
