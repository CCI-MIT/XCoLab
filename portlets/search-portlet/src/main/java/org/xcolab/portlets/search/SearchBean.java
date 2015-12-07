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
import org.xcolab.portlets.search.utils.DataPage;
import org.xcolab.portlets.search.utils.DataSource;
import org.xcolab.portlets.search.utils.PagedListDataModel;
import org.xcolab.utils.HtmlUtil;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchBean extends DataSource implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private  static final int PAGE_SIZE = 20;
    private final String searchLocation;

    private String searchPhrase = "";
    private final SearchItemType selectedSearchItemType;

    public SearchBean(String searchPhrase, String searchLocation) throws UnsupportedEncodingException {
        super("");
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
        onePageDataModel = null;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    /**
     * Bound to DataTable value in the ui.
     */
    public PagedListDataModel getData() {
        if (onePageDataModel == null) {
            onePageDataModel = new LocalDataModel(pageSize);
        }
        return onePageDataModel;
    }

    public List<SearchResultItem> getItems() throws InvalidTokenOffsetsException, ParseException, org.apache.lucene.queryParser.ParseException, IOException, SearchException {
        StringBuilder querySb = new StringBuilder();

        if (StringUtils.isEmpty(searchPhrase)) {
            return Collections.emptyList();
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
            return Collections.emptyList();
        }
        Query query = new StringQueryImpl(queryStr);

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        org.apache.lucene.search.Query luceneQuery = new QueryParser(Version.LUCENE_34, "content", analyzer).parse(queryStr);

        //Query query = new StringQueryImpl(searchPhrase);

        final int startRow = 0;

        Hits hits = SearchEngineUtil.search(10112L, query, startRow, startRow + PAGE_SIZE);
        // Retrieve the total number of customers from the database.  This
        // number is required by the DataPage object so the paginator will know
        // the relative location of the page data.
        int totalNumberCustomers = hits.getLength();

        List<SearchResultItem> items = new ArrayList<>();
        int i = 0;
        for (Document doc : hits.getDocs()) {
            items.add(new SearchResultItem(doc, query, luceneQuery, (i++ % 2) == 0));
        }
        return items;
    }

    /**
     * This is where the Customer data is retrieved from the database and
     * returned as a list of CustomerBean objects for display in the UI.
     *
     * @throws SearchException
     * @throws IOException
     * @throws ParseException
     * @throws org.apache.lucene.queryParser.ParseException
     * @throws InvalidTokenOffsetsException
     */
    private DataPage getDataPage(int startRow, int pageSize) throws SearchException, ParseException, IOException, org.apache.lucene.queryParser.ParseException, InvalidTokenOffsetsException {
        StringBuilder querySb = new StringBuilder();
        boolean separator = false;
        
        searchPhrase = searchPhrase != null ? searchPhrase.trim() : null;
        if (searchPhrase == null || searchPhrase.length() == 0) {
            return new DataPage(0, 0, new ArrayList<SearchResultItem>());
        }
        SearchItemType[] selectedSearchItemTypes;
        if (selectedSearchItemType == null) {
            selectedSearchItemTypes = SearchItemType.values();
        }
        else {
            selectedSearchItemTypes = new SearchItemType[] { selectedSearchItemType };
        } 
        
        for (SearchItemType type: selectedSearchItemTypes) {
            if (separator) {
                querySb.append(" OR ");
            }
            querySb.append(type.getQuery(searchPhrase));
            separator = true;
        }

        String queryStr = querySb.toString().trim();
        if (queryStr.length() == 0) {
            return new DataPage(0, 0, new ArrayList<SearchResultItem>());
        }
        Query query = new StringQueryImpl(queryStr);

        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
        org.apache.lucene.search.Query luceneQuery = new QueryParser(Version.LUCENE_34, "content", analyzer).parse(queryStr);
         
        //Query query = new StringQueryImpl(searchPhrase);
        
        Hits hits = SearchEngineUtil.search(10112L, query, startRow, startRow + pageSize);
        // Retrieve the total number of customers from the database.  This
        // number is required by the DataPage object so the paginator will know
        // the relative location of the page data.
        int totalNumberCustomers = hits.getLength();

        // Calculate indices to be displayed in the ui.
        int endIndex = startRow + pageSize;
        if (endIndex > totalNumberCustomers) {
            endIndex = totalNumberCustomers;
        }

        List<SearchResultItem> items = new ArrayList<SearchResultItem>();
        int i=0; 
        for (Document doc: hits.getDocs()) {
            items.add(new SearchResultItem(doc, query, luceneQuery, (i++ % 2) == 0));
        }

        return new DataPage(totalNumberCustomers,startRow,items);
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    private class LocalDataModel extends PagedListDataModel implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private boolean resetPager = true;

        public LocalDataModel(int pageSize) {
            super(pageSize);
        }

        @Override
        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
            if (resetPager) {
                startRow = 0;
                resetPager = false;
            }
            try {
                return getDataPage(startRow, pageSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new DataPage(0, 0, new ArrayList<SearchResultItem>());
        }
    }

    @Override
    protected boolean isDefaultAscending(String sortColumn) {
        // TODO Auto-generated method stub
        return false;
    }

    public SearchItemType[] getItemTypes() {
        return SearchItemType.values();
    }

    public SearchItemType getSelectedItemType() {
        return selectedSearchItemType;
    }

}
