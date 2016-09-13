package com.ext.portlet.service.impl;

import com.ext.utils.promotion.AutoPromoteHelper;
import com.ext.utils.promotion.PhasePromotionHelper;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
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

import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.utils.Clock;
import org.xcolab.utils.ClockImpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.mail.internet.AddressException;

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
    private static final String SERVER_PORT_PROPS_KEY = "climatecolab.server.port";
    private Clock clock = new ClockImpl();

    /** This can be used by unit tests to set a different clock than the standard one */
    @Override
    public void overrideClock(Clock clock) {
        this.clock = clock;
    }

    @Override
    public ContestStatus getContestStatus(ContestPhase contestPhase) throws SystemException, PortalException {
        String status = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType())
                .getStatus();
        return status == null ? null : ContestStatus.valueOf(status);
    }

    @Override
    public String getContestStatusStr(ContestPhase contestPhase) throws SystemException, PortalException {
        return ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType())
                .getStatus();
    }

    @Override
    public List<String> getPhaseColumns(ContestPhase contestPhase) throws SystemException {
        List<String> ret = new ArrayList<>();
        for (ContestPhaseColumn phaseColumn : ContestPhaseColumnLocalServiceUtil.getPhaseColumns(contestPhase
                .getContestPhasePK())) {
            ret.add(phaseColumn.getColumnName());
        }
        return ret;
    }

    @Override
    public List<ContestPhaseColumn> getPhaseColumnsRaw(ContestPhase contestPhase) throws SystemException {
        return ContestPhaseColumnLocalServiceUtil.getPhaseColumns(contestPhase.getContestPhasePK());
    }

    @Override
    public List<ContestPhase> getPreviousPhases(ContestPhase contestPhase) throws SystemException, PortalException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(getContest(contestPhase));
        List<ContestPhase> ret = new ArrayList<>();
        for (ContestPhase phase : phases) {
            if (phase.getPhaseStartDate().before(contestPhase.getPhaseStartDate())) {
                ret.add(phase);
            }
        }
        return ret;
    }

    @Override
    public ContestPhase getNextContestPhase(ContestPhase contestPhase) throws SystemException, PortalException {
        // First sort by contest phase type (the list has to be initialized as modifiable..)
        List<ContestPhase> contestPhases = new ArrayList<>(ContestPhaseLocalServiceUtil.getPhasesForContest(getContest(contestPhase)));
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

    @Override
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
                return contestPhase.getPhaseEndDate() == null
                        || now.before(contestPhase.getPhaseEndDate());
            }
        }
        return false;
    }

    @Override
    public List<ContestPhase> getPhasesForContest(Contest contest) throws SystemException {
        return contestPhasePersistence.findByContestId(contest.getContestPK());
    }

    @Override
    public List<ContestPhase> getPhasesForContest(long contestPK) throws SystemException {
        return contestPhasePersistence.findByContestId(contestPK);
    }

    @Override
    public List<ContestPhase> getPhasesForContestScheduleId(long contestScheduleId) throws SystemException {
        return contestPhasePersistence.findByContestScheduleId(contestScheduleId, org.xcolab.client.contest.pojo.ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID);
    }

    @Override
    public List<ContestPhase> getPhasesForContestScheduleIdAndContest(long contestScheduleId, long contestPK) throws SystemException {
        return contestPhasePersistence.findByContestScheduleId(contestScheduleId, contestPK);
    }


    @Override
    public List<ContestPhase> getPhasesForContestScheduleIdAndPhaseType(long contestScheduleId, long contestPhaseType) throws SystemException {

        DynamicQuery queryPhasesForContestScheduleIdAndPhaseType =
                DynamicQueryFactoryUtil.forClass(ContestPhase.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("contestScheduleId").eq(contestScheduleId))
                        .add(PropertyFactoryUtil.forName("ContestPhaseType").eq(contestPhaseType));

        return contestPhasePersistence.findWithDynamicQuery(queryPhasesForContestScheduleIdAndPhaseType);

    }

    @Override
    public ContestPhase getActivePhaseForContest(Contest contest) throws SystemException, PortalException {
        Date now = clock.now();
        try {
            return contestPhasePersistence.findByPhaseActiveOverride_Last(contest.getContestPK(), true,
                    new OrderByComparator() {

                        private final String[] ORDERY_BY_FIELDS = {"PhaseStartDate"};

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
        } catch (NoSuchContestPhaseException ignored) { }

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

    @Override
    public Contest getContest(ContestPhase contestPhase) throws SystemException, PortalException {
        return ContestLocalServiceUtil.getContest(contestPhase.getContestPK());
    }

    @Override
    public String getName(ContestPhase contestPhase) throws PortalException, SystemException {
        return ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType()).getName();
    }

    @Override
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
        if (currentProposalVersion <= 0) {
            _log.error(String.format("Proposal %d not found: version was %d", proposalId, currentProposalVersion));
            throw new SystemException("Proposal not found");
        }
        ContestPhase nextPhase = getContestPhase(nextPhaseId);
        if (nextPhase == null) {
            throw new SystemException("phase not found");
        }

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
        if (!candidatePhase.isEmpty()) {
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
            } else {
                isBoundedVersion = true;
            }
        }

        Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.create(proposalId, nextPhaseId);
        p2p.setVersionFrom(currentProposalVersion.intValue());
        p2p.setVersionTo(isBoundedVersion ? currentProposalVersion.intValue() : -1);
        Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);

        PhasePromotionHelper.createProposalContestPhasePromotionDoneAttribute(proposalId, currentPhaseId);
    }

    /**
     * Method responsible for autopromotion of proposals between phases.
     *
     * @throws SystemException
     * @throws PortalException
     */
    @Override
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

        AutoPromoteHelper autoPromoteHelper = new AutoPromoteHelper(now, serviceContext);
        autoPromoteHelper.doBasicPromotion();
        autoPromoteHelper.doJudgingBasedPromotion();
        autoPromoteHelper.distributeRibbons();
    }

    /**
     * Creates a new contest phase object by copying all attributes of the original contest phase
     * @param originalPhase     The contest phase to copy
     */
    @Override
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

    @Override
    public void forcePromotionOfProposalInPhase(Proposal p, ContestPhase phase) throws SystemException, PortalException {
        ContestPhase nextPhase = getNextContestPhase(phase);
        PhasePromotionHelper phasePromotionHelper = new PhasePromotionHelper(phase);
        //skip already promoted proposal
        if (phasePromotionHelper.hasProposalAlreadyBeenPromoted(p)) {
            return;
        }

        // Decide about the promotion
        if (phasePromotionHelper.didJudgeDecideToPromote(p)) {
            promoteProposal(p.getProposalId(), nextPhase.getContestPhasePK(), phase.getContestPhasePK());
        }

        // Enable this line to post promotion comment to Evaluation tab comment section
        // proposalLocalService.contestPhasePromotionCommentNotifyProposalContributors(p, phase);

        try {
            proposalLocalService.contestPhasePromotionEmailNotifyProposalContributors(p,  phase, null);
        } catch (MailEngineException | AddressException | UnsupportedEncodingException e) {
            _log.error("Could not send proposal promotion colab messaging notification", e);
        }

        _log.info("done forcefully promoting proposal "+ p.getProposalId() +" from phase " + phase.getContestPhasePK());
    }

    @Override
    public int getNumberOfProposalsForJudge(User judge, ContestPhase phase) throws PortalException, SystemException{
        List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(phase.getContestPhasePK());
        int counter = 0;
        for (Proposal p : proposals){
            String judges = "";
            try{
                judges = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(), phase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();
            } catch (NoSuchProposalContestPhaseAttributeException ignored) {  }
            if (StringUtils.containsIgnoreCase(judges, judge.getUserId() + "")) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Returns the URL link address for the passed contest phase
     *
     * @param contestPhase   The contest object
     * @return          Contest phase URL as String
     */
    @Override
    public String getContestPhaseLinkUrl(ContestPhase contestPhase) {
        try {
            String link = "/";
            Contest contest = contestLocalService.fetchContest(contestPhase.getContestPK());
            link += contestTypeLocalService.getContestType(contest).getFriendlyUrlStringContests();
            link += "/%d/%s/phase/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(), contestPhase.getContestPhasePK());
        } catch (SystemException e) {
            _log.error("Could not create link Url for contestPhase " + contestPhase.getContestPhasePK());
            return "/contests/";
        }
    }
}
