package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
/**
 * The implementation of the proposal2 phase local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.Proposal2PhaseLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.Proposal2PhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.Proposal2PhaseLocalServiceUtil
 */
public class Proposal2PhaseLocalServiceImpl
    extends Proposal2PhaseLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.Proposal2PhaseLocalServiceUtil} to access the proposal2 phase local service.
     */

    public Proposal2Phase create(long proposalId, long contestPhaseId) {
        return createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
    }
    
    public Proposal2Phase create(long proposalId, long contestPhaseId, int versionFrom, int versionTo) throws SystemException {
        Proposal2Phase p2p = createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
        p2p.setVersionFrom(versionFrom);
        p2p.setVersionTo(versionTo);
        
        p2p = addProposal2Phase(p2p);
        /*
        if (publishActivity) eventBus.post(new ProposalAssociatedWithContestPhaseEvent(proposal,
                contestPhaseLocalService.getContestPhase(contestPhaseId), UserLocalServiceUtil.getUser(authorId)));
 		*/
        return p2p;
    }

    public Proposal2Phase getByProposalIdContestPhaseId(long proposalId, long contestPhaseId) throws PortalException, SystemException {
        return getProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
    }
    
    public List<Proposal2Phase> getByProposalId(long proposalId) throws PortalException, SystemException {
    	return proposal2PhasePersistence.findByProposalId(proposalId);
    }

    public int getLatestProposalVersionInActiveContest(Long proposalId) throws Exception{
        int newestVersion = 0;

        List<Proposal2Phase> proposal2Phases = proposal2PhasePersistence.findByProposalId(proposalId);
        if (proposal2Phases.isEmpty()) {
            throw new SystemException("Proposal " + proposalId + " isn't associated with any contest");
        }

        for (Proposal2Phase p2p : proposal2Phases){
            long contestPhaseId = p2p.getContestPhaseId();
            if (p2p.getVersionTo() == -1 && isContestPhaseValidInContest(contestPhaseId)){
                return -1;
            } else if(p2p.getVersionTo() > newestVersion && isContestPhaseValidInContest(contestPhaseId)){
                newestVersion = p2p.getVersionTo();
            }
        }

        if(newestVersion != 0){
            return newestVersion;
        } else {
            throw new SystemException("Proposal " + proposalId + " has no active association.");
        }

    }
    public ContestPhase getLatestContestPhaseInContest(Long proposalId) throws SystemException, PortalException{
        int newestVersion = 0;
        long newestVersionContestPhaseId = 0;

        List<Proposal2Phase> proposal2Phases = proposal2PhasePersistence.findByProposalId(proposalId);
        if (proposal2Phases.isEmpty()) {
            throw new SystemException("Proposal " + proposalId + " isn't associated with any contest");
        }

        for (Proposal2Phase p2p : proposal2Phases){
            long contestPhaseId = p2p.getContestPhaseId();
            if (p2p.getVersionTo() == -1 && isContestPhaseValidInContest(contestPhaseId)){
                return contestPhaseLocalService.getContestPhase(contestPhaseId);
            } else if(p2p.getVersionTo() > newestVersion && isContestPhaseValidInContest(contestPhaseId)){
                newestVersion = p2p.getVersionTo();
                newestVersionContestPhaseId = contestPhaseId;
            }
        }

        if(newestVersion != 0 && newestVersionContestPhaseId != 0){
            return contestPhaseLocalService.getContestPhase(newestVersionContestPhaseId);
        } else {
            throw new SystemException("Proposal " + proposalId + " has no active association.");
        }

    }

    public Contest getCurrentContestForProposal(long proposalId) throws SystemException, PortalException {
        ContestPhase contestPhase = getLatestContestPhaseInContest(proposalId);
        return contestLocalService.getContest(contestPhase.getContestPK());
    }

    private boolean isContestPhaseValidInContest(long contestPhaseId){
        boolean isContestPhaseValidInContest = true;
        try {
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
            contestLocalService.getContest(contestPhase.getContestPK());
        } catch (Exception e){
            isContestPhaseValidInContest = false;
        }
        return isContestPhaseValidInContest;
    }

    public Proposal2Phase getForVersion(Long proposalId, int proposalVersionId) throws SystemException, PortalException {
        ProposalVersion proposalVersion =
                ProposalVersionLocalServiceUtil.getByProposalIdVersion(proposalId, proposalVersionId);
        return getForVersion(proposalVersion);
    }

    public Proposal2Phase getForVersion(ProposalVersion proposalVersion) throws SystemException, PortalException {
        List<Proposal2Phase> proposal2Phases = proposal2PhasePersistence.findByProposalId(proposalVersion.getProposalId());
        for (Proposal2Phase p2p : proposal2Phases){
            // closed phases
            if (p2p.getVersionFrom() <= proposalVersion.getVersion() && p2p.getVersionTo() >= proposalVersion.getVersion())
                return p2p;
            // open phase
            if (p2p.getVersionFrom() <= proposalVersion.getVersion() && p2p.getVersionTo() == -1)
                return p2p;
        }
        throw new SystemException("Proposal " + proposalVersion.getProposalId() + " isn't associated with any contest phase");
    }
    
    public List<Long> getContestPhasesForProposal(long proposalId) throws SystemException {
        List<Proposal2Phase> proposal2Phases = proposal2PhasePersistence.findByProposalId(proposalId);
        List<Long> ret = new LinkedList<>();

        for(Proposal2Phase p2p : proposal2Phases) {
            ret.add(p2p.getContestPhaseId());
        }

        return ret;
    }

    public List<ContestPhase> getActiveContestPhasesForProposal(long proposalId) throws SystemException, PortalException {
        List<Long> allPhases = getContestPhasesForProposal(proposalId);
        List<ContestPhase> ret = new LinkedList<>();
        Date now = new Date();
        for(Long pId : allPhases) {
            ContestPhase p = ContestPhaseLocalServiceUtil.getContestPhase(pId);
            if(p.getPhaseActiveOverride() && !p.getPhaseInactiveOverride()) { //take care of overrides
                if(p.getPhaseStartDate().before(now) && (p.getPhaseEndDate() ==null || p.getPhaseEndDate().after(now))) {
                    //apparently we are right in the window this proposal. yay!
                    ret.add(p);
                }
            }
        }
        return ret;
    }

    public List<Proposal2Phase> getByContestPhaseId(long contestPhaseId) throws Exception{
        final DynamicQuery contestPhasesByContestPhaseId = DynamicQueryFactoryUtil.forClass(Proposal2Phase.class, "phaseProposalIds");
        contestPhasesByContestPhaseId.setProjection(ProjectionFactoryUtil.property("phaseProposalIds.primaryKey.proposalId"));
        contestPhasesByContestPhaseId.add(PropertyFactoryUtil.forName("phaseProposalIds.primaryKey.contestPhaseId").eq(contestPhaseId));
        return dynamicQuery(contestPhasesByContestPhaseId);
    }

}
