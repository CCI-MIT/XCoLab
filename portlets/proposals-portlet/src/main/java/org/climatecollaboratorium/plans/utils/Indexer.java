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

import java.util.Locale;

import javax.portlet.PortletURL;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
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

	public static final String PORTLET_ID = "plans";

	public static void addEntry(long companyId, PlanItem plan) throws SearchException, SystemException {

		Document doc = getEntryDocument(companyId, plan);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteEntry(long companyId, long entryId)
		throws SearchException {

		SearchEngineUtil.deleteDocument(companyId, getEntryUID(entryId));
	}

	public static Document getEntryDocument(long companyId, PlanItem plan) throws SystemException {

		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, plan.getPlanId());

		doc.addModifiedDate(plan.getUpdated());

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, PlanItemLocalServiceUtil.getCategoryGroupId(plan));

		doc.addText(Field.TITLE, PlanItemLocalServiceUtil.getName(plan));
        doc.addText(Field.CONTENT, PlanItemLocalServiceUtil.getDescription(plan));

		doc.addKeyword(Field.ENTRY_CLASS_NAME, PlanItem.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, plan.getPlanId());
		//doc.addText(Field.URL, Plan);
		return doc;
	}

	public static String getEntryUID(long entryId) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, entryId);

		return doc.get(Field.UID);
	}
/*
	public static void updateEntry(long companyId, PlanItem plan) throws SearchException, SystemException {

		Document doc = getEntryDocument(companyId, plan);

		SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc);
	}

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public DocumentSummary getDocumentSummary(
		com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {

	    String title = doc.get(Field.TITLE);
		String url = doc.get(Field.URL);

		return new DocumentSummary(title, url, portletURL);
	}

	public void reIndex(String className, long classPK) throws SearchException {
		try {
		    PlanItemLocalServiceUtil.reIndex(classPK);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
			PlanItemLocalServiceUtil.reIndex();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}*/

	private static final String[] _CLASS_NAMES = new String[] {
		PlanItem.class.getName()
	};

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
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        return null;
    }

}