package org.xcolab.client.contest.pojo.phases;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.Date;

public class ContestPhase extends AbstractContestPhase {

    public static final long SCHEDULE_TEMPLATE_PHASE_CONTEST_ID = 0L;

    private final ContestClient contestClient;

    public ContestPhase() {
        contestClient = ContestClientUtil.getContestClient();
    }

    public ContestPhase(ContestPhase value) {
        super(value);
        contestClient = ContestClientUtil.getContestClient();
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
        contestClient = ContestClientUtil.getContestClient();
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
            return this.getPhaseActiveOverride();
        }
        if (this.getPhaseInactiveOverride() != null) {
            return this.getPhaseInactiveOverride();
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

}
