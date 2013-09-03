package org.xcolab.portlets.members;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.apache.commons.lang.StringUtils;
import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.xcolab.portlets.members.utils.DataPage;
import org.xcolab.portlets.members.utils.DataSource;
import org.xcolab.portlets.members.utils.PagedListDataModel;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccurImpl;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactory;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;

public class MembersBean extends DataSource implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startFrom = 0;
    private int RESULTS_PER_PAGE = 25;
    private final static long DEFAULT_COMPANY_ID = 10112L;
    private MembersListColumns sortColumn = MembersListColumns.ACTIVITY;
    private String sortColumnStr = sortColumn.name();
    private boolean sortAscending = false;
    private String searchPhrase = "";
    private EventBus eventBus;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    

    private MemberCategory categoryFilter = MemberCategory.MEMBER;
    
    /**
     * Represents a mapping from member category to a role that represents this category. 
     * This is used for filtering when doing user search by category.
     */
    private final Map<MemberCategory, Role> categoryRoleMap;
    
    private List<MemberListItemBean> searchResults = new ArrayList<MemberListItemBean>();
    
    // bound to rows attribute in dataTable
    protected int pageSize = 20;
    private final static Log _log = LogFactoryUtil.getLog(MembersBean.class);
    
    public MembersBean() throws SystemException, PortalException, NumberFormatException, ParseException {
        super("ACTIVITY");
    	categoryRoleMap = new HashMap<MemberCategory, Role>();

        Object filterObj = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("filter");
        if (filterObj != null) {
            try {
                categoryFilter = MemberCategory.valueOf(filterObj.toString());
            }
            catch (Exception e) {
                // ignore
            }
        }
        for (MemberCategory category: MemberCategory.values()) {
            try {
                if (category.equals(MemberCategory.ALL)) continue;
                for (String roleName: category.getRoleNames()) {
                    categoryRoleMap.put(category, RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, roleName));
                }
            }
            catch (com.liferay.portal.NoSuchRoleException e) {
                _log.warn("Can't find role for user category: " + category.name());
            }
        }
        
        updateSearchResults();
    }

    public int getStartFrom() {
        return startFrom;
    }
    
    public int getResultsPerPage() {
        return RESULTS_PER_PAGE;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public DataPage getData(int startRow, int pageSize) throws NumberFormatException, SystemException, PortalException, ParseException {
        
        searchResults.clear();

        SearchContext context = new SearchContext();
        context.setCompanyId(10112L);
        BooleanQuery query = BooleanQueryFactoryUtil.create(context);
        query.addRequiredTerm(Field.ENTRY_CLASS_NAME, "com.liferay.portal.model.User");
        if (categoryFilter != null && !categoryFilter.equals(MemberCategory.ALL)) {
            if (categoryFilter.equals(MemberCategory.MODERATOR) || categoryFilter.equals(MemberCategory.STAFF)) {
                BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
                subQuery.addExactTerm("memberCategory", MemberCategory.MODERATOR.name().toLowerCase());
                subQuery.addExactTerm("memberCategory", MemberCategory.STAFF.name().toLowerCase());
                query.add(subQuery, BooleanClauseOccurImpl.MUST);
            }
            else if (categoryFilter.equals(MemberCategory.MEMBER)) {
                BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
                subQuery.addExactTerm("memberCategory", MemberCategory.MEMBER.name().toLowerCase());
                
                
                BooleanQuery subQueryExclude = BooleanQueryFactoryUtil.create(context);
                subQueryExclude.addExactTerm("memberCategory", MemberCategory.ADVISOR.name().toLowerCase());
                subQueryExclude.addExactTerm("memberCategory", MemberCategory.STAFF.name().toLowerCase());
                subQueryExclude.addExactTerm("memberCategory", MemberCategory.EXPERT.name().toLowerCase());
                subQueryExclude.addExactTerm("memberCategory", MemberCategory.MODERATOR.name().toLowerCase());

                query.add(subQuery, BooleanClauseOccurImpl.MUST);

                query.add(subQueryExclude, BooleanClauseOccurImpl.MUST_NOT);
                
                
            }
            else {
                query.addRequiredTerm("memberCategory", categoryFilter.name(), false);
            }
        }
        if (StringUtils.isNotBlank(searchPhrase)) {
            BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
            String fuzzyPhrase = searchPhrase + "*";
            subQuery.addTerm("screenName", fuzzyPhrase);
            subQuery.addTerm("firstName", fuzzyPhrase);
            subQuery.addTerm("lastName", fuzzyPhrase);
            query.add(subQuery, BooleanClauseOccurImpl.MUST);
        }
        Sort sort = SortFactoryUtil.create(sortColumn.getField(), sortColumn.getType(), !sortAscending);
            

        Hits hits = SearchEngineUtil.search(10112L, query, sort, startRow, startRow + pageSize);
       
        
       for (Document userDoc: hits.getDocs()) {
           if (categoryFilter != null && !categoryFilter.equals(MemberCategory.ALL)) {
               // user has enabled category filter, show results from given category and mark them as
               // from that category
               String[] categories = userDoc.getValues("memberCategory");
               boolean include = true;
               if (categoryFilter.equals(MemberCategory.MEMBER) && categories.length > 1) {
                   include = false;
               }
               if (include) 
                   searchResults.add(new MemberListItemBean(userDoc, categoryFilter));
           }
           else {
               // autodetect member category
               searchResults.add(new MemberListItemBean(userDoc));
           }
        }
       
        return new DataPage(hits.getLength(), startRow, searchResults);
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        sortSearchResults();
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumnStr = sortColumn;
        try {
            this.sortColumn = MembersListColumns.valueOf(sortColumn);
        }
        catch (Exception e) {
            // ignore
        }
        sortSearchResults();
    }

    public String getSortColumn() {
        return sortColumnStr;
    }

    public void setSearchPhrase(String searchPhrase) throws SystemException, NumberFormatException, PortalException, ParseException {
        this.searchPhrase = searchPhrase;
        
        if (StringUtils.isNotBlank(searchPhrase)) 
            categoryFilter = MemberCategory.ALL;
        updateSearchResults();
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }
    
    public void doSearch(ActionEvent e) throws SystemException, NumberFormatException, PortalException, ParseException {
        updateSearchResults();
    }
    
    public void updateCategoryFilter(ActionEvent e) throws SystemException, NumberFormatException, PortalException, ParseException {
        MemberCategory category = null;
        try {
            category = MemberCategory.valueOf(e.getComponent().getAttributes().get("category").toString());
        }
        catch (Exception ex) {
            // ignore, enum value parsing error
        }
        
        if (category != null && ! category.equals(categoryFilter)) {
            categoryFilter = category;
            updateSearchResults();
        }
        
    }

    private void updateSearchResults() throws SystemException, NumberFormatException, PortalException, ParseException {
        /*
    	searchResults.clear();

    	 SearchContext context = new SearchContext();
    	 context.setCompanyId(10112L);
    	 BooleanQuery query = BooleanQueryFactoryUtil.create(context);
         query.addRequiredTerm(Field.ENTRY_CLASS_NAME, "com.liferay.portal.model.User");
         if (categoryFilter != null && !categoryFilter.equals(MemberCategory.ALL)) {
             if (categoryFilter.equals(MemberCategory.MODERATOR) || categoryFilter.equals(MemberCategory.STAFF)) {
                 BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
                 subQuery.addExactTerm("memberCategory", MemberCategory.MODERATOR.name().toLowerCase());
                 subQuery.addExactTerm("memberCategory", MemberCategory.STAFF.name().toLowerCase());
                 query.add(subQuery, BooleanClauseOccurImpl.MUST);
             }
             else {
                 query.addRequiredTerm("memberCategory", categoryFilter.name(), false);
             }
         }
         if (StringUtils.isNotBlank(searchPhrase)) {
             BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
             String fuzzyPhrase = searchPhrase + "*";
             subQuery.addTerm("screenName", fuzzyPhrase);
             subQuery.addTerm("firstName", fuzzyPhrase);
             subQuery.addTerm("lastName", fuzzyPhrase);
             query.add(subQuery, BooleanClauseOccurImpl.MUST);
         }


         Hits hits = SearchEngineUtil.search(10112L, query, 0, Integer.MAX_VALUE);
        
         
        System.out.println(hits.getLength());
        
        for (Document userDoc: hits.getDocs()) {
            if (categoryFilter != null && !categoryFilter.equals(MemberCategory.ALL)) {
                // user has enabled category filter, show results from given category and mark them as
                // from that category
                String[] categories = userDoc.getValues("memberCategory");
                boolean include = true;
                if (categoryFilter.equals(MemberCategory.MEMBER) && categories.length > 1) {
                	include = false;
                }
                if (include) 
                	searchResults.add(new MemberListItemBean(userDoc, categoryFilter));
            }
            else {
                // autodetect member category
                searchResults.add(new MemberListItemBean(userDoc));
            }
        }
        
        sortSearchResults();
        */
        onePageDataModel = null;
       
        
    }
    
    public int getUsersCount() {
        return searchResults.size();
    }
    
    public boolean isHasMembers() {
        return searchResults.size() > 0;
    }
    
    /**
     * Sorting search results is done manually and not with lucene because we can't track
     * user activity count with lucene. 
     */
    private void sortSearchResults() {
        onePageDataModel = null;
    }

    public MemberCategory getCategoryFilter() {
        return categoryFilter;
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
                if (event.hasSource("members")) {
                    try {
                        String filter = event.getParameters("members").get("filter");
                        
                        if (filter != null) {
                            MemberCategory filterCat = MemberCategory.valueOf(filter);
                            if (filterCat != categoryFilter) {
                                categoryFilter = filterCat;
                                updateSearchResults();
                            }
                        }
                    } catch (Exception e) {
                        _log.error("error when trying to filter users", e);
                        
                    }
                }
            }
            
        }));
        
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

        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
            if (resetPager) {
                startRow = 0;
                resetPager = false;
            }
            try {
                return getData(startRow, pageSize);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return new DataPage(0, 0, new ArrayList<MemberListItemBean>());
        }
    }

    @Override
    protected boolean isDefaultAscending(String sortColumn) {
        return true;
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
}
