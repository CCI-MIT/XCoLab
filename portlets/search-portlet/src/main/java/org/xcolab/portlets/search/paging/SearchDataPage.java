package org.xcolab.portlets.search.paging;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.StringQueryImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.xcolab.portlets.search.SearchItemType;
import org.xcolab.portlets.search.SearchResultItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchDataPage {
    private final static int PAGE_SIZE = 10;

    private final int page;
    private final String searchPhrase;
    private final String searchLocation;

    private int totalResults;

    private List<SearchResultItem> items;

    @SuppressWarnings("unused")
    public SearchDataPage()
            throws SystemException, SearchException, ParseException, org.apache.lucene.queryParser.ParseException,
            InvalidTokenOffsetsException, IOException {
        this(1, "", "");
    }

    public SearchDataPage(int page, String searchPhrase, String searchLocation)
            throws SystemException, SearchException, ParseException, org.apache.lucene.queryParser.ParseException,
            InvalidTokenOffsetsException, IOException {
        this.page = page;
        this.searchPhrase = StringUtils.trim(searchPhrase);
        this.searchLocation = searchLocation;

        initializeItems();
    }

    private void initializeItems() throws SearchException, org.apache.lucene.queryParser.ParseException, ParseException,
            InvalidTokenOffsetsException, IOException, SystemException {
        StringBuilder querySb = new StringBuilder();

        if (StringUtils.isEmpty(searchPhrase)) {
            items = Collections.emptyList();
            return;
        }
        SearchItemType[] selectedSearchItemTypes;

        if (StringUtils.isEmpty(searchLocation) || "FULL_SITE".equals(searchLocation)) {
            selectedSearchItemTypes = SearchItemType.values();
        } else {
            selectedSearchItemTypes = new SearchItemType[]{SearchItemType.valueOf(searchLocation)};
        }
        boolean separator = false;
        for (SearchItemType type : selectedSearchItemTypes) {
            if (separator) {
                querySb.append(" OR ");
            }
            querySb.append(type.getQuery(searchPhrase));
            separator = true;
        }

        String queryStr = querySb.toString().trim();
        if (queryStr.isEmpty()) {
            items = Collections.emptyList();
            return;
        }
        Query query = new StringQueryImpl(queryStr);

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        org.apache.lucene.search.Query luceneQuery =
                new QueryParser(Version.LUCENE_34, "content", analyzer).parse(queryStr);

        //Query query = new StringQueryImpl(searchPhrase);

        final int endRow = page * PAGE_SIZE;
        final int startRow = endRow - PAGE_SIZE;

        Hits hits = SearchEngineUtil.search(10112L, query, startRow, endRow);

        totalResults = hits.getLength();

        items = new ArrayList<>();
        int i = 0;
        for (Document doc : hits.getDocs()) {
            //TODO: don't reduce page size for invisible items -> incorporate in query
            final SearchResultItem resultItem = new SearchResultItem(doc, query, luceneQuery, (i++ % 2) == 0);
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
