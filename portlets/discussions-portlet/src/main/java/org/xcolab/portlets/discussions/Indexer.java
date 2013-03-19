package org.xcolab.portlets.discussions;

/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
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

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Raymond Augé
 *
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

	public static final String PORTLET_ID = "Discussions";
    private static final Log _log = LogFactoryUtil.getLog(Indexer.class);
    private long defaultCompanyId = 10112L;

	public String getEntryUID(long entryId) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, entryId);

		return doc.get(Field.UID);
	}


	public String[] getClassNames() {
		return _CLASS_NAMES;
	}


	public void reIndex(String className, long classPK) throws SearchException {
		DiscussionMessage msg = getMessage(classPK);
		Document doc = getDocument(msg);
		SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.updateDocument(getSearchEngineId(), defaultCompanyId, doc);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		DiscussionMessage.class.getName()
	};

    @Override
    public void delete(long companyId, String uid) throws SearchException {
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, uid);
        
    }

    @Override
    public void delete(Object obj) throws SearchException {
        DiscussionMessage msg = getMessage(obj);
        Document doc = getDocument(msg);
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
    }

    @Override
    public Document getDocument(Object obj) throws SearchException {

        DiscussionMessage message = (DiscussionMessage) obj;
        
        Document doc = new DocumentImpl();

        doc.addUID(PORTLET_ID, message.getMessageId());

        doc.addModifiedDate(message.getCreateDate());

        doc.addKeyword(Field.COMPANY_ID, 10112L);
        doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword(Field.GROUP_ID, message.getCategoryGroupId());

        doc.addText(Field.TITLE, message.getSubject());
        doc.addText(Field.CONTENT, message.getBody());

        doc.addKeyword(Field.ENTRY_CLASS_NAME, DiscussionMessage.class.getName());
        doc.addKeyword(Field.ENTRY_CLASS_PK, message.getMessageId());
        return doc;
    }

    @Override
    public BooleanQuery getFacetQuery(String className, SearchContext searchContext) throws Exception {
        return null;
    }

    @Override
    public BooleanQuery getFullQuery(SearchContext searchContext) throws SearchException {
        return null;
    }

    @Override
    public IndexerPostProcessor[] getIndexerPostProcessors() {
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
        return null;
    }

    @Override
    public Summary getSummary(Document document, Locale locale, String snippet, PortletURL portletURL)
            throws SearchException {
        return null;
    }

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, long entryClassPK, String actionId)
            throws Exception {
        return true;
    }

    @Override
    public boolean isFilterSearch() {
        return false;
    }

    @Override
    public boolean isPermissionAware() {
        return false;
    }

    @Override
    public boolean isStagingAware() {
        return false;
    }

    @Override
    public void postProcessContextQuery(BooleanQuery contextQuery, SearchContext searchContext) throws Exception {
        
    }

    @Override
    public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext) throws Exception {
     
    }

    @Override
    public void registerIndexerPostProcessor(IndexerPostProcessor indexerPostProcessor) {
        
    }

    @Override
    public void reindex(Object obj) throws SearchException {
        DiscussionMessage msg = getMessage(obj);
        Document doc = getDocument(msg);
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.updateDocument(getSearchEngineId(), defaultCompanyId, doc);
        
    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {
        DiscussionMessage msg = getMessage(classPK);
        Document doc = getDocument(msg);
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.updateDocument(getSearchEngineId(), defaultCompanyId, doc);
        
    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        long companyId = GetterUtil.getLong(ids[0]);
        List<DiscussionMessage> messages = null;
        try {
            messages = DiscussionMessageLocalServiceUtil.getDiscussionMessages(0,  Integer.MAX_VALUE);
        } catch (SystemException e) {
            _log.error("Can't reindex plans", e);
            throw new SearchException("Can't reindex plans", e);
        }
        Collection<Document> documents = new ArrayList<Document>();
        for (DiscussionMessage message : messages) {
            
            if (message.getDeleted() != null) continue;
            try {
                
                Document document = getDocument(message);
                documents.add(document);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing plan with id: " + message.getMessageId(), e);
            }

        }
        SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId, documents);
    }

    @Override
    public Hits search(SearchContext searchContext) throws SearchException {
        return null;
    }

    @Override
    public void unregisterIndexerPostProcessor(IndexerPostProcessor indexerPostProcessor) {
        
    }
    
    private DiscussionMessage getMessage(Object obj) throws SearchException {
        Long messageId = null;
        if (obj instanceof Long) {
            messageId = (Long) obj;
        }
        else {
            try {
                Method m = obj.getClass().getMethod("getMessageId");
                messageId = (Long) m.invoke(obj);
        } catch (IllegalAccessException e) {
            _log.error("Can't reindex message " + obj, e);
        } catch (IllegalArgumentException e) {
            _log.error("Can't reindex message " + obj, e);
        } catch (InvocationTargetException e) {
            _log.error("Can't reindex message " + obj, e);
        } catch (NoSuchMethodException e) {
            _log.error("Can't reindex message " + obj, e);
        } catch (SecurityException e) {
            _log.error("Can't reindex message " + obj, e);
        }
            
             
        }
        if (messageId == null) 
            return null;
        
            try {
                DiscussionMessage c = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
                return c;
            } catch (PortalException e) {
                _log.error("Can't reindex message " + obj, e);
                return null;
            } catch (SystemException e) {
                _log.error("Can't reindex message " + obj, e);
                return null;
            }
        
        
    }

}