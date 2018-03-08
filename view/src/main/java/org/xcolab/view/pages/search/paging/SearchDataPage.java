package org.xcolab.view.pages.search.paging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.search.SearchClient;
import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.view.pages.search.SearchResultItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchDataPage {

    private static final int PAGE_SIZE = 10;

    private final int page;
    private final String searchPhrase;
    private final String searchLocation;

    private int totalResults;

    private List<SearchResultItem> items;

    @SuppressWarnings("unused")
    public SearchDataPage() {
        this(1, "", "");
    }

    public SearchDataPage(int page, String searchPhrase, String searchLocation) {
        this.page = page;
        this.searchPhrase = StringUtils.trim(searchPhrase);
        this.searchLocation = searchLocation;

        initializeItems();
    }

    private void initializeItems() {

        if (StringUtils.isEmpty(searchPhrase)) {
            items = Collections.emptyList();
            return;
        }

        String queryStr = searchPhrase.trim();
        if (queryStr.isEmpty()) {
            items = Collections.emptyList();
            return;
        }
        final int endRow = page * PAGE_SIZE;
        final int startRow = endRow - PAGE_SIZE;

        List<SearchPojo> searchPojoList =
                SearchClient.search(startRow, endRow, searchLocation, queryStr);
        totalResults = SearchClient.searchCount(searchLocation, queryStr);

        items = new ArrayList<>();
        int i = 0;
        for (SearchPojo pojo : searchPojoList) {
            final SearchResultItem resultItem =
                    new SearchResultItem(pojo, queryStr, (i++ % 2) == 0);
            if (resultItem.isVisible()) {
                items.add(resultItem);
            }
        }
    }

    public int getNumberOfPages() {
        int numPages = totalResults / PAGE_SIZE;
        if (totalResults % PAGE_SIZE != 0) {
            numPages++;
        }
        return numPages;
    }

    public List<SearchResultItem> getItems() {
        return items;
    }

    public long getTotalResults() {
        return totalResults;
    }
}
