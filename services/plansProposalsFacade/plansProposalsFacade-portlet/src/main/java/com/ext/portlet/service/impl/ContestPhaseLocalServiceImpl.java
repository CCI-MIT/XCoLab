package com.ext.portlet.service.impl;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.base.ContestPhaseLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;
import com.liferay.util.portlet.PortletProps;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.utils.Clock;
import org.xcolab.utils.ClockImpl;

import javax.mail.internet.AddressException;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * The implementation of the contest phase local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.ContestPhaseLocalService} interface.
 * <p/>
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestPhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestPhaseLocalServiceUtil
 */
public class ContestPhaseLocalServiceImpl extends ContestPhaseLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     * 
     * Never reference this interface directly. Always use {@link
     * com.ext.portlet.service.ContestPhaseLocalServiceUtil} to access the
     * contest phase local service.
     */
    private final static Log _log = LogFactoryUtil.getLog(ContestPhaseLocalServiceImpl.class);
    private static final Long DEFAULT_CONTEST_ID_FOR_SCHEDULE = 0L;
    private static final String SERVER_PORT_PROPS_KEY = "climatecolab.server.port";
    private Clock clock = new ClockImpl();

    /** This can be used by unit tests to set a different clock than the standard one */
    public void overrideClock(Clock clock) {
        this.clock = clock;
    }

    public List<PlanItem> getPlans(ContestPhase contestPhase) throws SystemException, PortalException {
        return PlanItemLocalServiceUtil.getPlans(Collections.emptyMap(), Collections.emptyMap(), 0L,
                contestPhase.getContestPhasePK(), 0, Integer.MAX_VALUE, "", "", false);
        // return PlanItemLocalServiceUtil.getPlansInContestPhase(this);
    }

    public ContestStatus getContestStatus(ContestPhase contestPhase) throws SystemException, PortalException {
        String status = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType())
                .getStatus();
        return status == null ? null : ContestStatus.valueOf(status);
    }

    public String getContestStatusStr(ContestPhase contestPhase) throws SystemException, PortalException {
        String status = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType())
                .getStatus();
        return status;
    }

    public List<String> getPhaseColumns(ContestPhase contestPhase) throws SystemException {
        List<String> ret = new ArrayList<String>();
        for (ContestPhaseColumn phaseColumn : ContestPhaseColumnLocalServiceUtil.getPhaseColumns(contestPhase
                .getContestPhasePK())) {
            ret.add(phaseColumn.getColumnName());
        }
        return ret;
    }

    public List<ContestPhaseColumn> getPhaseColumnsRaw(ContestPhase contestPhase) throws SystemException {
        return ContestPhaseColumnLocalServiceUtil.getPhaseColumns(contestPhase.getContestPhasePK());
    }

    public List<ContestPhase> getPreviousPhases(ContestPhase contestPhase) throws SystemException, PortalException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(getContest(contestPhase));
        List<ContestPhase> ret = new ArrayList<ContestPhase>();
        for (ContestPhase phase : phases) {
            if (phase.getPhaseStartDate().before(contestPhase.getPhaseStartDate())) {
                ret.add(phase);
            }
        }
        return ret;
    }

    public ContestPhase getNextContestPhase(ContestPhase contestPhase) throws SystemException, PortalException {
        // First sort by contest phase type (the list has to be initialized as modifiable..)
        List<ContestPhase> contestPhases = new ArrayList(ContestPhaseLocalServiceUtil.getPhasesForContest(getContest(contestPhase)));
        Collections.sort(contestPhases, new Comparator<ContestPhase>() {
            @Override
            public int compare(ContestPhase o1, ContestPhase o2) {
                return o1.getPhaseStartDate().compareTo(o2.getPhaseStartDate());
            }
        });

        boolean currentFound = false;
        for (ContestPhase phase : contestPhases) {
            if (currentFound) {
                return phase;
            }
            if (phase.getContestPhasePK() == contestPhase.getContestPhasePK()) {
                currentFound = true;
            }
        }
        throw new SystemException("Can't find next phase for phase with id: " + contestPhase.getContestPhasePK());
    }

    public boolean getPhaseActive(ContestPhase contestPhase) {
        if (contestPhase.getPhaseActiveOverride()) {
            return contestPhase.getPhaseActiveOverride();
        }
        if (contestPhase.getPhaseInactiveOverride()) {
            return contestPhase.getPhaseInactiveOverride();
        }
        if (contestPhase.getPhaseStartDate() != null) {
            Date now = clock.now();
            if (now.after(contestPhase.getPhaseStartDate())) {
                if (contestPhase.getPhaseEndDate() != null) {
                    return now.before(contestPhase.getPhaseEndDate());
                }
                return true;
            }
        }
        return false;
    }

    public List<ContestPhase> getPhasesForContest(Contest contest) throws SystemException {
        return contestPhasePersistence.findByContestId(contest.getContestPK());
    }

    public List<ContestPhase> getPhasesForContest(long contestPK) throws SystemException {
        return contestPhasePersistence.findByContestId(contestPK);
    }

    public List<ContestPhase> getPhasesForContestScheduleId(long contestScheduleId) throws SystemException {
        return contestPhasePersistence.findByContestScheduleId(contestScheduleId, DEFAULT_CONTEST_ID_FOR_SCHEDULE);
    }

    public List<ContestPhase> getPhasesForContestScheduleIdAndContest(long contestScheduleId, long contestPK) throws SystemException {
        return contestPhasePersistence.findByContestScheduleId(contestScheduleId, contestPK);
    }

    public List<ContestPhase> getPhasesForContestScheduleIdAndPhaseType(long contestScheduleId, long contestPhaseType) throws SystemException {

        DynamicQuery queryPhasesForContestScheduleIdAndPhaseType =
                DynamicQueryFactoryUtil.forClass(ContestPhase.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("contestScheduleId").eq(contestScheduleId))
                        .add(PropertyFactoryUtil.forName("ContestPhaseType").eq(contestPhaseType));

        return contestPhasePersistence.findWithDynamicQuery(queryPhasesForContestScheduleIdAndPhaseType);

    }

    public ContestPhase getActivePhaseForContest(Contest contest) throws SystemException, PortalException {
        Date now = clock.now();
        try {
            return contestPhasePersistence.findByPhaseActiveOverride_Last(contest.getContestPK(), true,
                    new OrderByComparator() {

                        private final String[] ORDERY_BY_FIELDS = new String[]{"PhaseStartDate"};

                        @Override
                        public String[] getOrderByFields() {
                            return ORDERY_BY_FIELDS;
                        }

                        @Override
                        public int compare(Object arg0, Object arg1) {
                            if (((ContestPhase) arg0).getPhaseStartDate().before(
                                    ((ContestPhase) arg1).getPhaseStartDate())) {
                                return -1;
                            }
                            return 1;
                        }
                    });
        } catch (NoSuchContestPhaseException e) {
            // ignore
        }
        List<ContestPhase> phases = contestPhasePersistence.findByContestIdStartEnd(contest.getContestPK(), now, now);
        // Either there is no contest phase or there is a phase which enddate is open
        if (phases.isEmpty()) {

            // Check whether the contestPhase with the latest start date is open (no endDate)
            ContestPhase latestOpenPhase = null;
            long latestStartTime = 0;
            for (ContestPhase phase : contestLocalService.getVisiblePhases(contest)) {
                if (phase.getPhaseStartDate().getTime() > latestStartTime) {
                    if (phase.getPhaseEndDate() != null) {
                        latestOpenPhase = null;
                    } else {
                        latestOpenPhase = phase;
                    }
                }
            }

            if (latestOpenPhase == null) {
                throw new SystemException("Can't find active contest phase for contest " + contest.getContestPK());
            }

            return latestOpenPhase;

        }
        return phases.get(0);
    }

    /**
     * from ContestPhaseImpl *
     */

    public Contest getContest(ContestPhase contestPhase) throws SystemException, PortalException {
        return ContestLocalServiceUtil.getContest(contestPhase.getContestPK());
    }

    public String getName(ContestPhase contestPhase) throws PortalException, SystemException {
        return ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType()).getName();
    }

    public void promoteProposal(long proposalId, long nextPhaseId, long currentPhaseId) throws SystemException, PortalException {
    	try {
        	// check if proposal isn't already associated with requested phase
    		if (proposal2PhaseLocalService.getProposal2Phase(new Proposal2PhasePK(proposalId, nextPhaseId)) != null) {
                _log.warn("Proposal is already associated with given contest phase");
                return;
    		}
        }
    	catch (NoSuchProposal2PhaseException e) {
    		// no such proposal2phase, we can safely add association
    	}
        Long currentProposalVersion = ProposalVersionLocalServiceUtil.countByProposalId(proposalId);
        if (currentProposalVersion == null || currentProposalVersion < 0)
            throw new SystemException("Proposal not found");

        ContestPhase nextPhase = getContestPhase(nextPhaseId);
        if (nextPhase == null) throw new SystemException("phase not found");

        //find phase the proposal is in currently in contest c
        List<Long> phases = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposalId);
        List<ContestPhase> candidatePhase = new LinkedList<>();

        for (Long phId : phases) {
            ContestPhase ph = getContestPhase(phId);
            if (ph.getContestPK() == nextPhase.getContestPK()) { //this contestphase is in our target contest
                candidatePhase.add(ph);
            }
        }

        boolean isBoundedVersion = false;
        if (candidatePhase.size() > 0) {
            //candidate phase now contains all contestphases the proposal has been submitted to of the target contest
            //we now need to find the one that is closest to the next phase in order to provide a smooth promotion
            //set end version of previous phase to now
            ContestPhase closestPhase = candidatePhase.get(0);
            for (ContestPhase current : candidatePhase) {
                if (current.getPhaseStartDate().after(closestPhase.getPhaseStartDate())) {
                    closestPhase = current;
                }
            }

            Proposal2Phase o = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, closestPhase.getContestPhasePK());
            if (o.getVersionTo() < 0) {
                o.setVersionTo(currentProposalVersion.intValue());
                Proposal2PhaseLocalServiceUtil.updateProposal2Phase(o);
            } else isBoundedVersion = true;
        }

        Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.create(proposalId, nextPhaseId);
        p2p.setVersionFrom(currentProposalVersion.intValue());
        p2p.setVersionTo(isBoundedVersion ? currentProposalVersion.intValue() : -1);
        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);

        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalContestPhaseAttributeLocalServiceUtil.persistAttribute(
                    proposalId,
                    currentPhaseId,
                    ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                    0,
                    "true"
            );
        }
    }

    /**
     * Method responsible for autopromotion of proposals between phases.
     *
     * @throws SystemException
     * @throws PortalException
     */
    public void autoPromoteProposals() throws SystemException, PortalException {
    	
        Date now = clock.now();
        ServiceContext serviceContext = new ServiceContext();
        int port = GetterUtil.getInteger(PortletProps.get(SERVER_PORT_PROPS_KEY));
        if (Validator.isNull(port) || port <= 0) {
            port = PortalUtil.getPortalPort(false);
        }
        if (port <= 0) {
        	port = 80;
        }

		Company company = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId());
		
        serviceContext.setPortalURL(PortalUtil.getPortalURL(company.getVirtualHostname(), port, false));
        for (ContestPhase phase : contestPhasePersistence.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE.getValue())) {
            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !getPhaseActive(phase)) {
                // we have a candidate for promotion, find next phase
                try {
                	_log.info("promoting phase " + phase.getContestPhasePK());
                	Contest contest = contestLocalService.getContest(phase.getContestPK());
                    ContestPhase nextPhase = getNextContestPhase(phase);
                    for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
                        //skip already promoted proposal
                        if (hasProposalAlreadyBeenPromoted(p, phase)) {
                            continue;
                        }

                        if(!proposalIsVisible(p, phase)) {
                            continue;
                        }

                        promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                    }

                    // update phase for which promotion was done (mark it as
                    // "promotion done")
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    updateContestPhase(phase);

                    // if transition is to voting phase
                    if (getContestStatus(nextPhase).isCanVote()) {
                    	//contestLocalService.transferSupportsToVote(contest, serviceContext); //TODO enable me again
                    }
                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } catch (SystemException | PortalException e) {
                    _log.error("Exception thrown when doing autopromotion for phase " + phase.getContestPhasePK(), e);
                    continue;
                }
            }
        }
        //Judging-based promotion
        for (ContestPhase phase : contestPhasePersistence.findByPhaseAutopromote(ContestPhasePromoteType.PROMOTE_JUDGED.getValue())) {
            if (phase.getPhaseEndDate() != null && phase.getPhaseEndDate().before(now) && !getPhaseActive(phase)) {
                _log.info("promoting phase " + phase.getContestPhasePK() + " (judging)");

                // Only do the promotion if all proposals have been successfully reviewed
                if (allProposalsReviewed(phase)) {
                	Contest contest = contestLocalService.getContest(phase.getContestPK());
                    ContestPhase nextPhase = getNextContestPhase(phase);
                    for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
                    	try {
                        	// check if proposal isn't already associated with requested phase
                    		if (proposal2PhaseLocalService.getProposal2Phase(new Proposal2PhasePK(p.getProposalId(), nextPhase.getContestPhasePK())) != null) {
                                _log.warn("Proposal is already associated with given contest phase");
                                continue;
                    		}
                        }
                    	catch (NoSuchProposal2PhaseException e) {
                    		// no such proposal2phase, we can safely add association
                    	}
                        //skip already promoted proposal
                        if (hasProposalAlreadyBeenPromoted(p, phase)) {
                            System.out.println("Proposal already promoted "+p.getProposalId());
                            continue;
                        }

                        // Decide about the promotion
                        if (didJudgeDecideToPromote(p, phase)) {
                            System.out.println("Promote proposal "+p.getProposalId());
                            promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
                        }

                        proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);
                        try {
                            proposalLocalService.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
                        } catch (MailEngineException | AddressException e) {
                            _log.error("Could not send proposal promotion colab messaging notification", e);
                        }
                    }

                    // if transition is to voting phase
                    if (getContestStatus(nextPhase).isCanVote()) {
                    	//contestLocalService.transferSupportsToVote(contest, serviceContext); //TODO enable me again
                    }
                    phase.setContestPhaseAutopromote("PROMOTE_DONE");
                    updateContestPhase(phase);
                    _log.info("done promoting phase " + phase.getContestPhasePK());
                } else {
                    _log.warn("Judge promoting failed for ContestPhase with ID " + phase.getContestPhasePK() + " - not all proposals have been reviewed");
                }
            }
        }
    }

    /**
     * Creates a new contest phase object by copying all attributes of the original contest phase
     * @param originalPhase     The contest phase to copy
     * @return
     */
    public ContestPhase createFromContestPhase(ContestPhase originalPhase) throws SystemException {
        ContestPhase newPhase = createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

        newPhase.setContestPK(originalPhase.getContestPK());
        newPhase.setPhaseStartDate(originalPhase.getPhaseStartDate());
        newPhase.setPhaseEndDate(originalPhase.getPhaseEndDate());
        newPhase.setContestScheduleId(originalPhase.getContestScheduleId());
        newPhase.setContestPhaseType(originalPhase.getContestPhaseType());
        newPhase.setContestScheduleId(originalPhase.getContestScheduleId());
        newPhase.setFellowScreeningActive(originalPhase.getFellowScreeningActive());
        newPhase.setContestPhaseAutopromote(originalPhase.getContestPhaseAutopromote());
        newPhase.setContestPhaseDescriptionOverride(originalPhase.getContestPhaseDescriptionOverride());
        newPhase.setPhaseBufferEndDated(originalPhase.getPhaseBufferEndDated());
        newPhase.setNextStatus(originalPhase.getNextStatus());
        newPhase.setCreated(new Date());
        newPhase.setUpdated(new Date());
        newPhase.setAuthorId(originalPhase.getAuthorId());

        addContestPhase(newPhase);
        return newPhase;
    }

    private boolean proposalIsVisible(Proposal p, ContestPhase phase) {
        if(!p.getVisible()) return false;

        try {
            ProposalContestPhaseAttribute attr = getProposalContestPhaseAttributeLocalService().getProposalContestPhaseAttribute(p.getProposalId(), phase.getContestPhasePK(), "VISIBLE");
            if(attr.getNumericValue() == 0) return false;
        } catch (Exception e) {
        }

        return true;
    }

    private boolean didJudgeDecideToPromote(Proposal p, ContestPhase phase) {
        ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
        Long judgeDecisionValue = (judgeDecision == null) ? JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue() : judgeDecision.getNumericValue();

        if (JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue()) == JudgingSystemActions.AdvanceDecision.MOVE_ON) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasProposalAlreadyBeenPromoted(Proposal p, ContestPhase phase) throws SystemException {
        boolean hasProposalAlreadyBeenPromoted = ProposalContestPhaseAttributeLocalServiceUtil.isAttributeSetAndTrue(
                p.getProposalId(),
                phase.getContestPhasePK(),
                ProposalContestPhaseAttributeKeys.PROMOTE_DONE,
                0
        );

        if (hasProposalAlreadyBeenPromoted) {
            _log.info("proposal "+ p.getProposalId() +" in phase " + phase.getContestPhasePK() + " has already been promoted.");
            return true;
        } else {
            return false;
        }
    }


    public void forcePromotionOfProposalInPhase(Proposal p, ContestPhase phase) throws SystemException, PortalException {
        Date now = clock.now();
        ContestPhase nextPhase = getNextContestPhase(phase);

        //skip already promoted proposal
        if (hasProposalAlreadyBeenPromoted(p, phase)) {
            return;
        }

        // Decide about the promotion
        if (didJudgeDecideToPromote(p, phase)) {
            promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
        }

        proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);
        try {
            proposalLocalService.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
        } catch (MailEngineException | AddressException e) {
            _log.error("Could not send proposal promotion colab messaging notification", e);
        }

        _log.info("done forcefully promoting proposal "+ p.getProposalId() +" from phase " + phase.getContestPhasePK());
    }


    public int getNumberOfProposalsForJudge(User judge, ContestPhase phase) throws PortalException, SystemException{
        List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK());
        int counter=0;
        for (Proposal p : proposals){
            String judges = "";
            try{
                judges = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();
            } catch (NoSuchProposalContestPhaseAttributeException e) {  }
            if (StringUtils.containsIgnoreCase(judges, judge.getUserId() + "")) counter++;
        }
        return counter;
    }

    private boolean allProposalsReviewed(ContestPhase phase) throws SystemException, PortalException {
        boolean allProposalsReviewed = true;
        for (Proposal p : ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK())) {
            ProposalContestPhaseAttribute judgeDecision = getAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.JUDGE_DECISION);
            Long judgeDecisionValue = (judgeDecision == null) ? JudgingSystemActions.AdvanceDecision.NO_DECISION.getAttributeValue() : judgeDecision.getNumericValue();
            ProposalContestPhaseAttribute fellowAction = getAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
            Long fellowActionValue = (fellowAction == null) ? JudgingSystemActions.FellowAction.NO_DECISION.getAttributeValue() : fellowAction.getNumericValue();

            if (JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue.intValue()) == JudgingSystemActions.AdvanceDecision.NO_DECISION &&
                    !JudgingSystemActions.FellowAction.fromInt(fellowActionValue.intValue()).isActionProhibitingAdvancing()) {
                allProposalsReviewed = false;
            }
        }

        return allProposalsReviewed;
    }

    private ProposalContestPhaseAttribute getAttribute(long proposalId, long phaseId, String key) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, phaseId, key);
        } catch (Exception e) {
            return null;
        }
    }


}
