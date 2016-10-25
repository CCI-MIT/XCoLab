package org.xcolab.portlets.proposals.utils;



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
import org.apache.commons.lang3.StringUtils;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletURL;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Indexes a proposal
 * taken from previous proposals portlet and adapted to new environment: https://github.com/jintrone/XCoLab/blob/20b3f27a6f9e9f346ebc6675dcf58fa7052b9ae2/portlets/proposals-portlet/src/main/java/org/climatecollaboratorium/plans/utils/Indexer.java
 *
 * @author pdeboer
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

    public static final String PORTLET_ID = "plans";
    private static final Log _log = LogFactoryUtil.getLog(Indexer.class);
    private long defaultCompanyId = 10112L;

    private static final String[] _CLASS_NAMES = new String[]{Proposal.class.getName()};

    @Override
    public void delete(long companyId, String uid) throws SearchException {

    }

    @Override
    public void delete(Object obj) throws SearchException {
        Long id = -1L;
        if (obj instanceof Long) {
            id = (Long) obj;
        } else if (obj instanceof Proposal) {
            Proposal p = (Proposal) obj;
            id = p.getProposalId();
        }

        if (id < 0) {
            _log.error("id should never be below 0. PAAANIIIIC!!!");
        }

        try {
            Proposal plan = ProposalClientUtil.getProposal(id);
            SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, getDocument(plan).getUID());
        } catch (Throwable e) {
            _log.error("Can't remove plan from index: " + obj, e);
        }

    }

    @Override
    public Document getDocument(Object obj) throws SearchException {
        try {
            Proposal proposal = (Proposal) obj;

            Document doc = new DocumentImpl();

            doc.addUID(PORTLET_ID, proposal.getProposalId());

            doc.addModifiedDate(proposal.getUpdatedDate());

            doc.addKeyword(Field.COMPANY_ID, defaultCompanyId);
            doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
            
            Contest currentContest = ProposalClientUtil.getCurrentContestForProposal(proposal.getProposalId());
            
            

            ProposalWrapper wrapper = new ProposalWrapper(proposal, proposal.getCurrentVersion(), currentContest, null, null);
            

            doc.addText(Field.TITLE, wrapper.getName());
            doc.addText("pitch", wrapper.getPitch());
            doc.addText(Field.CONTENT, wrapper.getDescription());

            StringBuilder sectionsCombined = new StringBuilder();
            List<ProposalSectionWrapper> sections = wrapper.getSections();
            if (sections != null) {
                for (ProposalSectionWrapper section : sections) {
                    if (StringUtils.isNotBlank(section.getContent())) {
                        sectionsCombined.append(section.getContent());
                        sectionsCombined.append(" ");
                    }
                }
            }
            doc.addText("sections", sectionsCombined.toString());

            doc.addKeyword(Field.ENTRY_CLASS_NAME, Proposal.class.getName());
            doc.addKeyword(Field.ENTRY_CLASS_PK, proposal.getProposalId());
            // doc.addText(Field.URL, Plan);
            return doc;
        } catch (ContestNotFoundException | SystemException e) {
            throw new SearchException("Can't index plan " + obj, e);
        } catch (PortalException e) {
            throw new SearchException("Can't index plan " + obj, e);
        }
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
        return null;
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
    public boolean isVisible(long l, int i) throws Exception {
        return true;
    }

    @Override
    public boolean isVisibleRelatedEntry(long l, int i) throws Exception {
        return true;
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
        Proposal p = null;
        if (obj instanceof Long) {
            Long proposalId = 0L;
            try {
                proposalId = (Long) obj;
                p = ProposalClientUtil.getProposal(proposalId);
            } catch (Throwable e) {
                _log.error("Can't reindex plan " + proposalId, e);
            }

        } else {
            try {
                Method m = obj.getClass().getMethod("getProposalId");
                Long proposalId = (Long) m.invoke(obj);
                p = ProposalClientUtil.getProposal(proposalId);
            } catch (Throwable e) {
                _log.error("Can't reindex plan " + obj, e);
            }
        }


        if (p == null || !p.getVisible()) {
            return;
        }
        Document doc = getDocument(p);
        SearchEngineUtil.deleteDocument(getSearchEngineId(), defaultCompanyId, doc.getUID());
        SearchEngineUtil.addDocument(getSearchEngineId(), defaultCompanyId, doc);

    }

    @Override
    public void reindex(String className, long classPK) throws SearchException {
    }

    @Override
    public void reindex(String[] ids) throws SearchException {
        long companyId = GetterUtil.getLong(ids[0]);
        List<Proposal> proposals = null;
        
            proposals = ProposalClientUtil.getAllProposals();

        Collection<Document> documents = new ArrayList<>();
        for (Proposal p : proposals) {
            if (!p.getVisible()) {
                continue;
            }
            try {
                Document document = getDocument(p);
                documents.add(document);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing plan with id: " + p.getProposalId(), e);
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

    @Override
    public String[] getClassNames() {
        return _CLASS_NAMES;
    }

	@Override
	public void addRelatedEntryFields(Document document, Object obj)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSortField(String orderByCol, int sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
			String entryClassName, long entryClassPK, String actionId)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reindexDDMStructures(List<Long> ddmStructureIds)
			throws SearchException {
		// TODO Auto-generated method stub
		
	}

}