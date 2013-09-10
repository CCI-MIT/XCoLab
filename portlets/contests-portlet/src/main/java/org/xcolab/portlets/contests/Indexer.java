package org.xcolab.portlets.contests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.PermissionChecker;

public class Indexer implements com.liferay.portal.kernel.search.Indexer {
	public static final String PORTLET_ID = "contests";
    private final long defaultCompanyId = 10112;
	
    private static Log _log = LogFactoryUtil.getLog(Indexer.class);
    
	
	private static final String[] _CLASS_NAMES = new String[] {
		Contest.class.getName()
	};
	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

    @Override
    public void delete(long companyId, String uid) throws SearchException {
        SearchEngineUtil.deleteDocument(getSearchEngineId(), companyId, uid);
        
    }
    
    

    @Override
    public void delete(Object obj) throws SearchException {
        Contest c = getContest(obj);
        if (c == null) return;
        Document doc = getDocument(c);
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        
    }

    @Override
    public Document getDocument(Object obj) throws SearchException {
        Contest c = (Contest) obj;
        
        Document doc = new DocumentImpl();

        doc.addUID(PORTLET_ID, c.getContestPK());

        doc.addModifiedDate(c.getUpdated());

        doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword(Field.GROUP_ID, c.getGroupId());
        doc.addKeyword(Field.COMPANY_ID, defaultCompanyId);

        doc.addText(Field.TITLE, c.getContestShortName());
        doc.addText(Field.CONTENT, c.getContestName() + "\n" + c.getContestDescription());

        doc.addKeyword(Field.ENTRY_CLASS_NAME, Contest.class.getName());
        doc.addKeyword(Field.ENTRY_CLASS_PK, c.getContestPK());
        
        return doc;
    }

    @Override
    public BooleanQuery getFacetQuery(String className, SearchContext searchContext) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BooleanQuery getFullQuery(SearchContext searchContext) throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IndexerPostProcessor[] getIndexerPostProcessors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPortletId() {
        return PORTLET_ID;
    }

    @Override
    public String getSearchEngineId() {
        return SearchEngineUtil.SYSTEM_ENGINE_ID;
    }


    @Override
    public String getSortField(String orderByCol) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Summary getSummary(Document document, Locale locale, String snippet, PortletURL portletURL)
            throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, long entryClassPK, String actionId)
            throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isFilterSearch() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPermissionAware() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isStagingAware() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void postProcessContextQuery(BooleanQuery contextQuery, SearchContext searchContext) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void registerIndexerPostProcessor(IndexerPostProcessor indexerPostProcessor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reindex(Object obj) throws SearchException {
        Document doc = getDocument(getContest(obj));
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.addDocument(getSearchEngineId(), defaultCompanyId, doc);
        
    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {

        Document doc = getDocument(getContest(classPK));
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.addDocument(getSearchEngineId(), defaultCompanyId, doc);
        
    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        long companyId = GetterUtil.getLong(ids[0]);
        List<Contest> contetsts = null;
        try {
            contetsts = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            _log.error("Can't reindex contests", e);
            throw new SearchException("Can't reindex contests", e);
        }
        Collection<Document> documents = new ArrayList<Document>();
        for (Contest contest : contetsts) {
            try {
                Document document = getDocument(contest);
                documents.add(document);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing contest with id: " + contest.getContestPK(), e);
            }

        }
        SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents);
        
    }

    @Override
    public Hits search(SearchContext searchContext) throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void unregisterIndexerPostProcessor(IndexerPostProcessor indexerPostProcessor) {
        // TODO Auto-generated method stub
        
    }
    
    private Contest getContest(Object obj) throws SearchException {
        Long contestId = null;
        if (obj instanceof Long) {
            contestId = (Long) obj;
        }
        else {
            try {
                Method m = obj.getClass().getMethod("getContestPK");
                contestId = (Long) m.invoke(obj);
        } catch (IllegalAccessException e) {
            _log.error("Can't reindex contest " + obj, e);
        } catch (IllegalArgumentException e) {
            _log.error("Can't reindex contest " + obj, e);
        } catch (InvocationTargetException e) {
            _log.error("Can't reindex contest " + obj, e);
        } catch (NoSuchMethodException e) {
            _log.error("Can't reindex contest " + obj, e);
        } catch (SecurityException e) {
            _log.error("Can't reindex contest " + obj, e);
        }
            
             
        }
        if (contestId == null) 
            return null;
        
            try {
                Contest c = ContestLocalServiceUtil.getContest(contestId);
                
                return c == null ? (Contest) obj : c;
            } catch (PortalException e) {
                _log.error("Can't reindex contest " + obj, e);
                return null;
            } catch (SystemException e) {
                _log.error("Can't reindex contest " + obj, e);
                return null;
            }
        
        
    }
    
}
