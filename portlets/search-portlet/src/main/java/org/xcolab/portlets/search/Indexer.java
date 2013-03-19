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

package org.xcolab.portlets.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 * @author Bruno Farache
 * @author Raymond Augé
 *
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

	public static final String PORTLET_ID = PortletKeys.JOURNAL;
    private static Log _log = LogFactoryUtil.getLog(Indexer.class);

	public static void addArticle(
			long companyId, long groupId, String articleId, double version,
			String title, String description, String content, String type,
			Date displayDate, String[] tagsCategories, String[] tagsEntries,
			ExpandoBridge expandoBridge)
		throws SearchException {

		Document doc = getArticleDocument(
			companyId, groupId, articleId, version, title, description, content,
			type, displayDate, tagsCategories, tagsEntries, expandoBridge);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteArticle(long companyId, String articleId)
		throws SearchException {

		SearchEngineUtil.deleteDocument(companyId, getArticleUID(articleId));
		SearchEngineUtil.deleteDocument(companyId, getArticleUID(articleId, true));
	}

	public static Document getArticleDocument(
		long companyId, long groupId, String articleId, double version,
		String title, String description, String content, String type,
		Date displayDate, String[] tagsCategories, String[] tagsEntries,
		ExpandoBridge expandoBridge) {

		if ((content != null) &&
			((content.indexOf("<dynamic-content") != -1) ||
			 (content.indexOf("<static-content") != -1))) {

			content = _getIndexableContent(content);

			content = StringUtil.replace(
				content, "<![CDATA[", StringPool.BLANK);
			content = StringUtil.replace(content, "]]>", StringPool.BLANK);
		}

		content = StringUtil.replace(content, "&amp;", "&");
		content = StringUtil.replace(content, "&lt;", "<");
		content = StringUtil.replace(content, "&gt;", ">");

		content = HtmlUtil.extractText(content);
		
		Document doc = new DocumentImpl();

		// check if this is an most recent version of an article
		JournalArticle article;
		boolean isOld = false;
		try {
			article = JournalArticleLocalServiceUtil.getArticle(groupId, articleId);
			if (article.getVersion() != version) {
				isOld = true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// ignore
		} 
				
				
		if (isOld) {
			doc.addUID(PORTLET_ID, articleId, "old");
		}
		else {
			doc.addUID(PORTLET_ID, articleId);
		}

		doc.addModifiedDate(displayDate);

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, groupId);

		doc.addText(Field.TITLE, title);
		doc.addText(Field.CONTENT, content);
		doc.addText(Field.DESCRIPTION, description);

		doc.addKeyword(Field.ENTRY_CLASS_PK, articleId);
		doc.addKeyword(Field.VERSION, version);
		doc.addKeyword(Field.TYPE, type);
		

		ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);
		
		try {
			Layout pageLayout = getLayoutForContent(articleId);
			
			if (pageLayout != null) {
				Layout tmp = pageLayout;
				boolean isAbout = false;
				while (tmp != null && !isAbout) {
					if (tmp.getFriendlyURL().toLowerCase().contains("/about") || tmp.getFriendlyURL().toLowerCase().contains("/contests")) {
						isAbout = true;
					}
					if (tmp.getParentLayoutId() > 0) {
						try {
							tmp = LayoutLocalServiceUtil.getLayout(tmp.getGroupId(), tmp.getPrivateLayout(), tmp.getParentLayoutId());
							
						}
						catch (com.liferay.portal.NoSuchLayoutException e) {
							tmp = null;
						} catch (PortalException e) {
							tmp = null;
						}
					}
					else {
						tmp = null;
					}
				}
				
				if (isAbout) {
					doc.addKeyword("PAGE_URL", pageLayout.getFriendlyURL());
					doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName() + ".index");
				}
			}
			else {
				doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			}
		} catch (SystemException e) {
			doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			e.printStackTrace();
		}
		return doc;
	}

	public static String getArticleUID(String articleId) {
		return getArticleUID(articleId, false);
	}
	
	public static String getArticleUID(String articleId, boolean isOld) {
		Document doc = new DocumentImpl();

		if (isOld) {
			doc.addUID(PORTLET_ID, articleId, "old");
		}
		else {
			doc.addUID(PORTLET_ID, articleId);
		}

		return doc.get(Field.UID);
	}


	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	private static String _getIndexableContent(String content) {
		try {
			StringBuilder sb = new StringBuilder();

			com.liferay.portal.kernel.xml.Document doc = SAXReaderUtil.read(
				content);

			Element root = doc.getRootElement();

			_getIndexableContent(sb, root);

			return sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();

			return content;
		}
	}

	private static void _getIndexableContent(StringBuilder sb, Element root)
		throws Exception {

		for (Element el : root.elements()) {
			String elType = el.attributeValue("type", StringPool.BLANK);

			if (elType.equals("text") || elType.equals("text_box") ||
				elType.equals("text_area")) {

				for (Element dynamicContent : el.elements("dynamic-content")) {
					String text = dynamicContent.getText();

					sb.append(text);
					sb.append(StringPool.SPACE);
				}
			}
			else if (el.getName().equals("static-content")) {
				String text = el.getText();

				sb.append(text);
				sb.append(StringPool.SPACE);
			}

			_getIndexableContent(sb, el);
		}
	}

	private static final String[] _CLASS_NAMES = new String[] {
		JournalArticle.class.getName(),
		JournalArticle.class.getName() + ".index"
	};
	
	private static Layout getLayoutForContent(String articleId) throws SystemException {
		DynamicQuery layoutQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader());
		
		DynamicQuery preferencesQuery = DynamicQueryFactoryUtil.forClass(PortletPreferences.class, PortalClassLoaderUtil.getClassLoader());
		
		preferencesQuery.add(RestrictionsFactoryUtil.like("portletId", "56_INSTANCE%"));  
		preferencesQuery.add(RestrictionsFactoryUtil.like("preferences", "%<name>articleId</name><value>" + articleId + "</value>%"));  
		preferencesQuery.setProjection(ProjectionFactoryUtil.property("plid"));  
		
		layoutQuery.add(PropertyFactoryUtil.forName("plid").in(preferencesQuery));  
		
		//List result = PortletPreferencesLocalServiceUtil.dynamicQuery(preferencesQuery);
		
		List result = LayoutLocalServiceUtil.dynamicQuery(layoutQuery);
		
		if (result.size() > 0) {
			Layout layout = (Layout) result.get(0);
			return layout;
		}
		return null;
	}

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
        JournalArticle article = (JournalArticle) obj;
        
        String articleId = article.getArticleId();
        long groupId = article.getGroupId();
        long companyId = article.getCompanyId();
        double version = article.getVersion();
        
        String content = article.getContent();
        String title = article.getTitle();
        Date displayDate = article.getDisplayDate();
        String description = article.getDescription();
        String type = article.getType();
        ExpandoBridge expandoBridge = article.getExpandoBridge();
        
        if ((content != null) &&
            ((content.indexOf("<dynamic-content") != -1) ||
             (content.indexOf("<static-content") != -1))) {

            content = _getIndexableContent(content);

            content = StringUtil.replace(
                content, "<![CDATA[", StringPool.BLANK);
            content = StringUtil.replace(content, "]]>", StringPool.BLANK);
        }

        content = StringUtil.replace(content, "&amp;", "&");
        content = StringUtil.replace(content, "&lt;", "<");
        content = StringUtil.replace(content, "&gt;", ">");

        content = HtmlUtil.extractText(content);
        
        Document doc = new DocumentImpl();

        // check if this is an most recent version of an article
        boolean isOld = false;
        try {
            JournalArticle tmpArticle = JournalArticleLocalServiceUtil.getArticle(groupId, String.valueOf(articleId));
            if (article.getVersion() != version) {
                isOld = true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            // ignore
        } 
                
                
        if (isOld) {
            doc.addUID(PORTLET_ID, articleId, "old");
        }
        else {
            doc.addUID(PORTLET_ID, articleId);
        }

        doc.addModifiedDate(displayDate);

        doc.addKeyword(Field.COMPANY_ID, companyId);
        doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword(Field.GROUP_ID, groupId);

        doc.addText(Field.TITLE, title);
        doc.addText(Field.CONTENT, content);
        doc.addText(Field.DESCRIPTION, description);

        doc.addKeyword(Field.ENTRY_CLASS_PK, articleId);
        doc.addKeyword(Field.VERSION, version);
        doc.addKeyword(Field.TYPE, type);
        

        ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);
        
        try {
            Layout pageLayout = getLayoutForContent(articleId);
            
            if (pageLayout != null) {
                Layout tmp = pageLayout;
                boolean isAbout = false;
                while (tmp != null && !isAbout) {
                    if (tmp.getFriendlyURL().toLowerCase().contains("/about") || tmp.getFriendlyURL().toLowerCase().contains("/contests")) {
                        isAbout = true;
                    }
                    if (tmp.getParentLayoutId() > 0) {
                        try {
                            tmp = LayoutLocalServiceUtil.getLayout(tmp.getGroupId(), tmp.getPrivateLayout(), tmp.getParentLayoutId());
                            
                        }
                        catch (com.liferay.portal.NoSuchLayoutException e) {
                            tmp = null;
                        } catch (PortalException e) {
                            tmp = null;
                        }
                    }
                    else {
                        tmp = null;
                    }
                }
                
                if (isAbout) {
                    doc.addKeyword("PAGE_URL", pageLayout.getFriendlyURL());
                    doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName() + ".index");
                }
            }
            else {
                doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
            }
        } catch (SystemException e) {
            doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
            e.printStackTrace();
        }
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

        JournalArticle article = (JournalArticle)obj;

        Document document = getDocument(article);

        if (!article.isIndexable() ||
            (!article.isApproved() &&
             (article.getVersion() !=
                  JournalArticleConstants.VERSION_DEFAULT))) {

            SearchEngineUtil.deleteDocument(
                getSearchEngineId(), article.getCompanyId(),
                document.get(Field.UID));

            return;
        }

        SearchEngineUtil.deleteDocument(
            getSearchEngineId(), article.getCompanyId(), document.getUID());
        SearchEngineUtil.updateDocument(
            getSearchEngineId(), article.getCompanyId(), document);
        
    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {

        JournalArticle article;
        try {
            article = JournalArticleLocalServiceUtil.getLatestArticle(
                classPK, WorkflowConstants.STATUS_APPROVED);

            reindex(article);
        } catch (PortalException e) {
            throw new SearchException(e);
        } catch (SystemException e) {
            throw new SearchException(e);
        }
        
    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        long companyId = GetterUtil.getLong(ids[0]);
        List<JournalArticle> articles = null;
        try {
            articles = JournalArticleLocalServiceUtil.getJournalArticles(0,  Integer.MAX_VALUE);
        } catch (SystemException e) {
            _log.error("Can't reindex plans", e);
            throw new SearchException("Can't reindex plans", e);
        }
        Collection<Document> documents = new ArrayList<Document>();
        for (JournalArticle article : articles) {
            try {
                Document document = getDocument(article);
                documents.add(document);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing article with id: " + article.getArticleId(), e);
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

}