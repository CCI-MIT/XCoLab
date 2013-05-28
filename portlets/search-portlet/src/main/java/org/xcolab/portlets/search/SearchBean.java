package org.xcolab.portlets.search;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.xcolab.portlets.search.utils.DataPage;
import org.xcolab.portlets.search.utils.DataSource;
import org.xcolab.portlets.search.utils.PagedListDataModel;

import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.StringQueryImpl;

public class SearchBean extends DataSource implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchPhrase = "";
    private final static int RESULTS_PER_PAGE = 50;
    private int startFrom = 0;
    private int length = 0;
    private SearchItemType selectedSearchItemType;
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private DataPaginator paginator1 = new DataPaginator();
    private DataPaginator paginator2 = new DataPaginator();
    
    private final static Log _log = LogFactoryUtil.getLog(SearchBean.class); 
    
    
    public SearchBean() {
        super("");
        
        selectedSearchItemType = null;
    }

    public void setSearchPhrase(String searchPhrase) {
        if (searchPhrase != null && searchPhrase.trim().length() == 0) {
            this.searchPhrase = null;
        }
        else {
            this.searchPhrase = searchPhrase;
        }
        onePageDataModel = null;
        
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }
    
    public void navigate(ActionEvent e) {
        Object start = e.getComponent().getAttributes().get("start");
        try {
            Integer newStart = Integer.parseInt(start.toString());
            if (length > 0 && newStart > 0) {
                startFrom = newStart;
                startFrom = Math.min(newStart, length);
            }
        }
        catch (NumberFormatException ex) {
            // 
        }
    }
    
    public int getStartFrom() {
        return startFrom;
    }
    
    public int getResultsPerPage() {
        return RESULTS_PER_PAGE;
    }
    
    /**
     * Bound to DataTable value in the ui.
     */
    public DataModel getData() {
        if(onePageDataModel == null){
            onePageDataModel = new LocalDataModel(pageSize);
        }
        return onePageDataModel;
    }
    
    /**
     * This is where the Customer data is retrieved from the database and
     * returned as a list of CustomerBean objects for display in the UI.
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

    private class LocalDataModel extends PagedListDataModel {
    	private boolean resetPager = true;
        public LocalDataModel(int pageSize) {
            super(pageSize);
        }

        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
        	if (resetPager) {
        		startRow = 0;
            	resetPager = false;
        	}
            try {
                return getDataPage(startRow, pageSize);
            }
            catch (Exception e) {
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
    
    public void selectType(ActionEvent e) {
        Object type = e.getComponent().getAttributes().get("type");
        String typeStr = String.valueOf(type);
        if (type == null || typeStr.equals("FULL_SITE")) {
            selectedSearchItemType = null;
        }
        else {
            selectedSearchItemType = SearchItemType.valueOf(typeStr);
            startFrom = 0;
            paginator1.gotoFirstPage();
        }
        onePageDataModel = null;
    }
    
    public void doSearch(ActionEvent e) {
        // just refresh search results
    }
    
    public SearchItemType getSelectedItemType() {
        return selectedSearchItemType;
    }
    
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }
    
    private void bind() {
        for (HandlerRegistration reg: handlerRegistrations) {
            reg.unregister();
        }
        
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                if (event.hasSource("search")) {
                    try {
                        String newPhrase = event.getParameters("search").get("searchPhrase");
                        if (newPhrase != null && newPhrase.trim().length() > 0) {
                            searchPhrase = URLDecoder.decode(event.getParameters("search").get("searchPhrase"), "UTF-8");
                        }
                        else {
                            searchPhrase = null;
                            
                        }
                        
                        onePageDataModel = null;
                    } catch (UnsupportedEncodingException e) {
                        _log.error("Can't read search phrase", e);
                    }
                }
            }
            
        }));
        
    }

	public DataPaginator getPaginator1() {
		return paginator1;
	}

	public void setPaginator1(DataPaginator paginator1) {
		this.paginator1 = paginator1;
	}

	public DataPaginator getPaginator2() {
		return paginator2;
	}

	public void setPaginator2(DataPaginator paginator2) {
		this.paginator2 = paginator2;
	}
}
