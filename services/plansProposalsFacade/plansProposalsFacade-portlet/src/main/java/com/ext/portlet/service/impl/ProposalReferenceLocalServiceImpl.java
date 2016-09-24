package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalReferenceException;
import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.base.ProposalReferenceLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalReferencePK;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.utils.LinkUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The implementation of the proposal reference local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalReferenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalReferenceLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalReferenceLocalServiceUtil
 */
public class ProposalReferenceLocalServiceImpl
    extends ProposalReferenceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalReferenceLocalServiceUtil} to access the proposal reference local service.
     */

    private final static Log _log = LogFactoryUtil.getLog(ProposalReferenceLocalServiceImpl.class);

    @Override
    public List<ProposalReference> getByProposalId(long proposalId) throws SystemException {
        return proposalReferencePersistence.findByProposalId(proposalId);
    }

    @Override
    public List<ProposalReference> getBySubProposalId(long subProposalId) throws SystemException {
        return proposalReferencePersistence.findBySubProposalId(subProposalId);
    }

    @Override
    public ProposalReference getByProposalIdSubProposalId(long proposalId, long subProposalId) throws NoSuchProposalReferenceException, SystemException {
        return proposalReferencePersistence.findByPrimaryKey(new ProposalReferencePK(proposalId, subProposalId));
    }

    @Override
    public void populateTable() throws SystemException, PortalException {

        Set<Long> processedProposals = new HashSet<>();

        List<Contest> contests = ContestLocalServiceUtil.getContests(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        _log.debug(String.format("Populating table using %d contests", contests.size()));

        for (Contest contest : contests) {
            for (Proposal proposal : proposalLocalService.getProposalsInContest(contest.getContestPK())) {
                populateTableWithProposal(proposal, processedProposals);
                _log.debug(String.format("Populating table using proposal %d", proposal.getProposalId()));
            }
        }
    }

    @Override
    public void populateTableWithProposal(Proposal proposal) throws PortalException, SystemException {
        final List<ProposalReference> existingReferences = getByProposalId(proposal.getProposalId());
        for (ProposalReference existingReference : existingReferences) {
                deleteProposalReference(existingReference);
        }
        populateTableWithProposal(proposal, new HashSet<Long>());
    }

    private void populateTableWithProposal(Proposal proposal, Set<Long> processedProposals) throws SystemException, PortalException {
        if (processedProposals.contains(proposal.getProposalId())) {
            return;
        }
        final List<ProposalReference> existingReferences = getByProposalId(proposal.getProposalId());
        for (ProposalReference existingReference : existingReferences) {
            deleteProposalReference(existingReference);
        }
        processedProposals.add(proposal.getProposalId());
        /*
        for (ProposalAttribute attribute : new ProposalAttributeHelper(proposal).getAttributesByName(ProposalAttributeKeys.SECTION)) {
            PlanSectionDefinition psd = planSectionDefinitionLocalService.getPlanSectionDefinition(attribute.getAdditionalId());

            if (StringUtils.isBlank(psd.getType())) {
                continue;
            }

            PlanSectionTypeKeys type = PlanSectionTypeKeys.valueOf(psd.getType());
            Set<Long> subProposalIds = new HashSet<>();
            switch (type) {
                case PROPOSAL_REFERENCE: {
                    final long subProposalId = attribute.getNumericValue();
                    if (subProposalId != 0) {
                        subProposalIds.add(subProposalId);
                    }
                    break;
                }
                case PROPOSAL_LIST_REFERENCE: {
                    if (Validator.isNull(attribute.getStringValue())) {
                        break;
                    }
                    String[] referencedProposals = attribute.getStringValue().split(",");
                    for (String referencedProposal : referencedProposals) {
                        final long subProposalId = Long.parseLong(referencedProposal);
                        subProposalIds.add(subProposalId);
                    }
                    break;
                }
                case PROPOSAL_LIST_TEXT_REFERENCE: {
                    subProposalIds.addAll(LinkUtils.getProposalIdsFromLinksInText(attribute.getStringValue()));
                    break;
                }
            }
            for (long subProposalId : subProposalIds) {
                addProposalReference(proposal.getProposalId(), subProposalId, attribute.getId());
                populateTableWithProposal(proposalLocalService.fetchProposal(subProposalId), processedProposals);
            }
        }*/
    }

    private void addProposalReference(long proposalId, long subProposalId, long sectionAttributeId) throws SystemException {
        ProposalReferencePK proposalReferencePK = new ProposalReferencePK(proposalId, subProposalId);
        if (proposalReferencePersistence.fetchByPrimaryKey(proposalReferencePK) == null) {
            ProposalReference proposalReference = proposalReferencePersistence.create(proposalReferencePK);
            proposalReference.setSectionAttributeId(sectionAttributeId);
            proposalReference.persist();
            _log.debug(String.format("Added ProposalReference for %d, %d, %d", proposalId, subProposalId, sectionAttributeId));
        } else {
            _log.debug(String.format("ProposalReference with primary key {%d, %d} already exists", proposalId, subProposalId));
        }
    }
}
