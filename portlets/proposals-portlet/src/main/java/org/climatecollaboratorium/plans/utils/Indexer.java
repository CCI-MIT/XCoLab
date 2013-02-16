package org.climatecollaboratorium.plans.utils;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
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
import com.liferay.portal.service.GroupLocalServiceUtil;

import edu.mit.cci.roma.client.EntityState;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Raymond Augé
 * 
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

    public static final String PORTLET_ID = "plans";
    private static final Log _log = LogFactoryUtil.getLog(Indexer.class);

    /*
     * public static void updateEntry(long companyId, PlanItem plan) throws
     * SearchException, SystemException {
     * 
     * Document doc = getEntryDocument(companyId, plan);
     * 
     * SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc); }
     * 
     * public String[] getClassNames() { return _CLASS_NAMES; }
     * 
     * public DocumentSummary getDocumentSummary(
     * com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {
     * 
     * String title = doc.get(Field.TITLE); String url = doc.get(Field.URL);
     * 
     * return new DocumentSummary(title, url, portletURL); }
     * 
     * public void reIndex(String className, long classPK) throws
     * SearchException { try { PlanItemLocalServiceUtil.reIndex(classPK); }
     * catch (Exception e) { throw new SearchException(e); } }
     * 
     * public void reIndex(String[] ids) throws SearchException { try {
     * PlanItemLocalServiceUtil.reIndex(); } catch (Exception e) { throw new
     * SearchException(e); } }
     */

    private static final String[] _CLASS_NAMES = new String[] { PlanItem.class.getName() };

    @Override
    public void delete(long companyId, String uid) throws SearchException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Object obj) throws SearchException {
        // TODO Auto-generated method stub

    }

    @Override
    public Document getDocument(Object obj) throws SearchException {
        try {
        PlanItem plan = (PlanItem) obj;

        Document doc = new DocumentImpl();

        doc.addUID(PORTLET_ID, plan.getPlanId());

        doc.addModifiedDate(plan.getUpdated());

        doc.addKeyword(Field.COMPANY_ID, GroupLocalServiceUtil.getGroup(PlanItemLocalServiceUtil.getPlanMeta(plan).getPlanGroupId()).getCompanyId());
        doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword(Field.GROUP_ID, PlanItemLocalServiceUtil.getCategoryGroupId(plan));

        doc.addText(Field.TITLE, PlanItemLocalServiceUtil.getName(plan));
        doc.addText(Field.CONTENT, PlanItemLocalServiceUtil.getDescription(plan));

        doc.addKeyword(Field.ENTRY_CLASS_NAME, PlanItem.class.getName());
        doc.addKeyword(Field.ENTRY_CLASS_PK, plan.getPlanId());
        // doc.addText(Field.URL, Plan);
        return doc;
        }
        catch (SystemException e) {
            throw new SearchException("Can't index plan " + obj, e);
        } catch (PortalException e) {
            throw new SearchException("Can't index plan " + obj, e);
        }
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
        // TODO Auto-generated method stub
        return null;
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

    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {

    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        long companyId = GetterUtil.getLong(ids[0]);
        List<PlanItem> plans = null;
        try {
            plans = PlanItemLocalServiceUtil.getPlans();
        } catch (SystemException e) {
            _log.error("Can't reindex plans", e);
            throw new SearchException("Can't reindex plans", e);
        }
        Collection<Document> documents = new ArrayList<Document>();
        for (PlanItem plan : plans) {
            if (plan.getState().equals("DELETED")) continue;
            try {
                
                Document document = getDocument(plan);
                documents.add(document);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing plan with id: " + plan.getPlanId(), e);
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

    @Override
    public String[] getClassNames() {
        return new String[] { PlanItem.class.getName() };
    }

}