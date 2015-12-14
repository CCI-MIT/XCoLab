package org.xcolab.portlets.search;

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
import org.xcolab.utils.HtmlUtil;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static int PAGE_SIZE = 10;
    private final static int PAGER_RANGE = 5;
    private final String searchLocation;
    private final int pageNumber;

    private String searchPhrase = "";
    private final SearchItemType selectedSearchItemType;

    private List<SearchResultItem> items;
    private int totalNumberCustomers;

    public SearchBean(String searchPhrase, String searchLocation, Integer pageNumber) throws IOException, ParseException, SearchException, org.apache.lucene.queryParser.ParseException, InvalidTokenOffsetsException {
        this.pageNumber = pageNumber == null || pageNumber < 1 ? 1 : pageNumber;
        this.searchLocation = searchLocation == null? "" : searchLocation;
        if (StringUtils.isEmpty(searchPhrase)) {
            this.searchPhrase = "";
        } else {
            this.searchPhrase = HtmlUtil.cleanAll(URLDecoder.decode(searchPhrase, "UTF-8"));
        }

        if (StringUtils.isEmpty(searchLocation) || searchLocation.equals("FULL_SITE")) {
            selectedSearchItemType = null;
        } else {
            selectedSearchItemType = SearchItemType.valueOf(searchLocation);
        }
        initializeItems();
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    private void initializeItems() throws SearchException, org.apache.lucene.queryParser.ParseException, ParseException, InvalidTokenOffsetsException, IOException {
        StringBuilder querySb = new StringBuilder();

        if (StringUtils.isEmpty(searchPhrase)) {
            items = Collections.emptyList();
            return;
        }
        searchPhrase = searchPhrase.trim();
        SearchItemType[] selectedSearchItemTypes;
        if (selectedSearchItemType == null) {
            selectedSearchItemTypes = SearchItemType.values();
        } else {
            selectedSearchItemTypes = new SearchItemType[]{selectedSearchItemType};
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
        org.apache.lucene.search.Query luceneQuery = new QueryParser(Version.LUCENE_34, "content", analyzer).parse(queryStr);

        //Query query = new StringQueryImpl(searchPhrase);

        final int endRow = pageNumber * PAGE_SIZE;
        final int startRow = endRow - PAGE_SIZE;

        Hits hits = SearchEngineUtil.search(10112L, query, startRow, endRow);

        totalNumberCustomers = hits.getLength();

        items = new ArrayList<>();
        int i = 0;
        for (Document doc : hits.getDocs()) {
            items.add(new SearchResultItem(doc, query, luceneQuery, (i++ % 2) == 0));
        }
    }

    public List<SearchResultItem> getItems() {
        return items;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public SearchItemType[] getItemTypes() {
        return SearchItemType.values();
    }

    public SearchItemType getSelectedItemType() {
        return selectedSearchItemType;
    }

    public int getNumberOfPages() {
        int numPages =  totalNumberCustomers / PAGE_SIZE;
        if (totalNumberCustomers % PAGE_SIZE != 0) {
            numPages++;
        }
        return numPages;
    }

    public List<PageLinkWrapper> getPageLinks() {
        List<PageLinkWrapper> pageLinks = new ArrayList<>();
        pageLinks.add(new PageLinkWrapper("<< First", 1, searchPhrase, searchLocation));
        if (pageNumber > 1) {
            pageLinks.add(new PageLinkWrapper("< Previous", pageNumber - 1, searchPhrase, searchLocation));
        }
        for (int i = Math.max(1, pageNumber - PAGER_RANGE), stop = Math.min(getNumberOfPages(), pageNumber + PAGER_RANGE); i <= stop; i++) {
            pageLinks.add(new PageLinkWrapper("", i, searchPhrase, searchLocation));
        }
        if (pageNumber < getNumberOfPages()) {
            pageLinks.add(new PageLinkWrapper("Next >", pageNumber + 1, searchPhrase, searchLocation));
        }
        pageLinks.add(new PageLinkWrapper("Last >>", getNumberOfPages(), searchPhrase, searchLocation));
        return pageLinks;
    }

    public int getTotalNumberOfResults() {
        return totalNumberCustomers;
    }
}
