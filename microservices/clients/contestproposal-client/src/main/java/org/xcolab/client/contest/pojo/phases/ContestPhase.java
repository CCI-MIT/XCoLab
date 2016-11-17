package org.xcolab.client.contest.pojo.phases;

import org.apache.commons.lang.StringUtils;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ContestPhase extends AbstractContestPhase {

    public static final long SCHEDULE_TEMPLATE_PHASE_CONTEST_ID = 0L;

    private final ContestClient contestClient;

    private RestService restService;

    protected ContestStatus status;

    public ContestPhase() {
        contestClient = ContestClientUtil.getClient();
    }

    public ContestPhase(ContestPhase value) {
        super(value);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestPhase(Long contestphasepk, Long contestpk, Long contestphasetype,
            Long contestscheduleid, Boolean fellowscreeningactive, String contestphaseautopromote,
            String contestphasedescriptionoverride, Boolean phaseactiveoverride,
            Boolean phaseinactiveoverride, Timestamp phasestartdate, Timestamp phaseenddate,
            Timestamp phasebufferenddated, String nextstatus, Timestamp created,
            Timestamp updated, Long authorid) {

        super(contestphasepk, contestpk, contestphasetype, contestscheduleid, fellowscreeningactive,
                contestphaseautopromote, contestphasedescriptionoverride, phaseactiveoverride,
                phaseinactiveoverride, phasestartdate, phaseenddate, phasebufferenddated,
                nextstatus, created, updated, authorid);
        contestClient = ContestClientUtil.getClient();
    }

    public ContestPhase(AbstractContestPhase abstractContestPhase, RestService restService) {
        super(abstractContestPhase);
        contestClient = ContestClient.fromService(restService);
    }

    public static ContestPhase createFromContestPhase(ContestPhase originalPhase) {
        ContestPhase newPhase = new ContestPhase();

        newPhase.setContestPK(originalPhase.getContestPK());
        newPhase.setPhaseStartDate(originalPhase.getPhaseStartDate());
        newPhase.setPhaseEndDate(originalPhase.getPhaseEndDate());
        newPhase.setContestScheduleId(originalPhase.getContestScheduleId());
        newPhase.setContestPhaseType(originalPhase.getContestPhaseType());
        newPhase.setContestScheduleId(originalPhase.getContestScheduleId());
        newPhase.setFellowScreeningActive(originalPhase.getFellowScreeningActive());
        newPhase.setContestPhaseAutopromote(originalPhase.getContestPhaseAutopromote());
        newPhase.setContestPhaseDescriptionOverride(
                originalPhase.getContestPhaseDescriptionOverride());
        newPhase.setPhaseBufferEndDated(originalPhase.getPhaseBufferEndDated());
        newPhase.setNextStatus(originalPhase.getNextStatus());
        newPhase.setCreated(new Timestamp(new Date().getTime()));
        newPhase.setUpdated(new Timestamp(new Date().getTime()));
        newPhase.setAuthorId(originalPhase.getAuthorId());

        return newPhase;
    }

    public int compareTo(ContestPhase contestPhase) {

        if (getPhaseStartDate().getTime() == contestPhase.getPhaseStartDate().getTime()) {
            return 0;
        } else {
            if (getPhaseStartDate().getTime() < contestPhase.getPhaseStartDate().getTime()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public boolean getPhaseActive() {
        if (this.getPhaseActiveOverride() != null) {
            if(this.getPhaseActiveOverride()) {
                return this.getPhaseActiveOverride();
            }
        }
        if (this.getPhaseInactiveOverride() != null) {
            if(this.getPhaseInactiveOverride()) {
                return this.getPhaseInactiveOverride();
            }
        }
        if (this.getPhaseStartDate() != null) {
            Date now = new Date();
            if (now.after(this.getPhaseStartDate())) {
                return this.getPhaseEndDate() == null
                        || now.before(this.getPhaseEndDate());
            }
        }
        return false;
    }

    public String getContestPhaseLinkUrl() {
        try {
            String link = "/";
            Contest contest = contestClient.getContest(this.getContestPK());
            link += ContestClientUtil.getContestType(contest.getContestTypeId())
                    .getFriendlyUrlStringContests();
            link += "/%d/%s/phase/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    this.getContestPhasePK());
        } catch (ContestNotFoundException ignored) {
            return "/contests/";
        }

    }

    public String getContestStatusStr() {
        return contestClient.getContestPhaseType(getContestPhaseType()).getStatus();
    }

    public ContestPhaseType getContestPhaseTypeObject() {
        return contestClient.getContestPhaseType(this.getContestPhaseType());
    }

    public Date getPhaseStartDateDt() {
        return this.getPhaseStartDate();
    }

    public Date getPhaseEndDateDt() {
        return this.getPhaseEndDate();
    }

    public Date getPhaseReferenceDate() {
        return (this.getPhaseEndDate() == null) ? this.getPhaseStartDateDt()
                : this.getPhaseEndDateDt();
    }

    public String getPhaseReferenceYear() {
        Date referenceDate = getPhaseReferenceDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        return "" + cal.get(Calendar.YEAR);
    }


    public ContestStatus getStatus() {
        if (status == null) {
            String statusStr = contestClient.getContestStatusStr(this.getContestPhasePK());
            if (statusStr != null) {
                status = ContestStatus.valueOf(statusStr);
            }
        }
        return status;
    }

    public boolean getCanVote() {
        return (getStatus() != null) && getStatus().isCanVote();
    }

    public boolean getCanEdit() {
        return (getStatus() != null) && getStatus().isCanEdit();
    }

    public boolean isActive() {
        return this.getPhaseActive();
    }

    public long getMillisecondsTillEnd() {
        return (this.getPhaseEndDate() != null) ? (this.getPhaseEndDate().getTime() - System
                .currentTimeMillis()) : -1;
    }

    public String getName() {
        return contestClient.getContestPhaseType(this.getContestPhaseType()).getName();
    }

    public boolean isEnded() {
        Date now = new Date();
        return (this.getPhaseEndDate() != null) && this.getPhaseEndDate().before(now);
    }

    public boolean isAlreadyStarted() {
        Date now = new Date();
        return this.getPhaseStartDate().before(now);
    }

    public Boolean getProposalVisibility(long proposalId) {
        ProposalPhaseClient proposalPhaseClient;
        if(restService!=null) {
            RestService proposalService = restService.withServiceName(CoLabService.PROPOSAL.getServiceName());
            proposalPhaseClient = ProposalPhaseClient.fromService(proposalService);
        }else{
            proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        }
        ProposalContestPhaseAttribute attr =  proposalPhaseClient
                .getProposalContestPhaseAttribute(proposalId, this.getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.VISIBLE);
        return attr.getNumericValue() == 1;

    }

    public boolean setProposalVisibility(long proposalId, boolean visible) {
        RestService proposalService = restService.withServiceName(CoLabService.PROPOSAL.getServiceName());
        ProposalPhaseClient.fromService(proposalService)
                .setProposalContestPhaseAttribute(proposalId, this.getContestPhasePK(),
                        ProposalContestPhaseAttributeKeys.VISIBLE, 0l, visible ? 1l : 0l, "");
        return true;
    }

    public String getPhaseStatusDescription() {
        String descriptionOverride = this.getContestPhaseDescriptionOverride();
        if (StringUtils.isBlank(descriptionOverride)) {
            return contestClient.getContestPhaseType(this.getContestPhaseType())
                    .getDescription();

        }
        return descriptionOverride;
    }


    public ContestPhase getWrapped() {
        return this;
    }

    public String getContestPhaseUrl() {
        return this.getContestPhaseLinkUrl();
    }
}
