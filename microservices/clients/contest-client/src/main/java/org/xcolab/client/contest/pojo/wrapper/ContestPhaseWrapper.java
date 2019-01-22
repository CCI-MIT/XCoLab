package org.xcolab.client.contest.pojo.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ContestPhase;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.ProposalPhaseClientUtil;
import org.xcolab.commons.time.DurationFormatter;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class ContestPhaseWrapper extends ContestPhase {

    public static final TypeProvider<ContestPhaseWrapper> TYPES = new TypeProvider<>(
            ContestPhaseWrapper.class,
            new ParameterizedTypeReference<List<ContestPhaseWrapper>>() {});

    public static final long SCHEDULE_TEMPLATE_PHASE_CONTEST_ID = 0L;

    private final ContestClient contestClient;

    protected ContestStatus status;

    public ContestPhaseWrapper() {
        contestClient = StaticContestContext.getContestClient();
    }

    public ContestPhaseWrapper(ContestPhaseWrapper value) {
        super(value);
        contestClient = value.contestClient;
    }

    public ContestPhaseWrapper(ContestPhase abstractContestPhase) {
        super(abstractContestPhase);
        contestClient = StaticContestContext.getContestClient();
    }

    public static ContestPhaseWrapper clone(ContestPhaseWrapper originalPhase) {
        ContestPhaseWrapper newPhase = new ContestPhaseWrapper();

        newPhase.setContestId(originalPhase.getContestId());
        newPhase.setPhaseStartDate(originalPhase.getPhaseStartDate());
        newPhase.setPhaseEndDate(originalPhase.getPhaseEndDate());
        newPhase.setContestScheduleId(originalPhase.getContestScheduleId());
        newPhase.setContestPhaseTypeId(originalPhase.getContestPhaseTypeId());
        newPhase.setContestPhaseAutopromote(originalPhase.getContestPhaseAutopromote());
        newPhase.setCreatedAt(new Timestamp(new Date().getTime()));
        newPhase.setUpdatedAt(new Timestamp(new Date().getTime()));
        return newPhase;
    }

    public boolean getPhaseActive() {
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
            ContestWrapper contest = contestClient.getContest(this.getContestId());
            String link = contest.getContestType().getContestBaseUrl();
            link += "/%d/%s/phase/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    this.getId());
        } catch (ContestNotFoundException ignored) {
            return "/contests/";
        }
    }

    public String getContestStatusStr() {
        return getContestPhaseTypeObject().getStatus();
    }

    public IContestPhaseType getContestPhaseTypeObject() {
        return contestClient.getContestPhaseType(this.getContestPhaseTypeId());
    }

    public Date getPhaseStartDateDt() {
        return this.getPhaseStartDate();
    }

    public Instant getPhaseStartDateInstant() {
        return this.getPhaseStartDate().toInstant();
    }

    public Date getPhaseEndDateDt() {
        return this.getPhaseEndDate();
    }

    public Instant getPhaseEndDateInstant() {
        return this.getPhaseEndDate() != null ? this.getPhaseEndDate().toInstant() : null;
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
            status = ContestStatus.valueOf(getContestStatusStr());
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

    public String getDurationTillEnd() {
        return DurationFormatter.forRequestLocale().formatDifferenceAsDays(getPhaseEndDate());
    }

    public String getDurationTillEndFormatted() {
        String duration = getDurationTillEnd();
        //surrounds number with <span> tag for formatting
        return duration.replaceAll("\\b(\\d+)\\b", "<span class='c-Count__number'>$1</span>");
    }

    public String getName() {
        return contestClient.getContestPhaseType(this.getContestPhaseTypeId()).getName();
    }

    public String getFlagText() {
        IContestPhaseType phaseType = getContestPhaseTypeObject();
        String flagText = phaseType.getDefaultFlagText();
        if (StringUtils.isEmpty(flagText)) {
            flagText = phaseType.getName();
        }
        return flagText;
    }

    public boolean isEnded() {
        Date now = new Date();
        return (this.getPhaseEndDate() != null) && this.getPhaseEndDate().before(now);
    }

    public boolean isAlreadyStarted() {
        Date now = new Date();
        final Timestamp phaseStartDate = this.getPhaseStartDate();
        return phaseStartDate != null && phaseStartDate.before(now);
    }

    public ContestPhasePromoteType getContestPhaseAutopromoteEnum() {
        return ContestPhasePromoteType.getPromoteType(getContestPhaseAutopromote());
    }

    public Boolean getProposalVisibility(long proposalId) {
        ProposalPhaseClient proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        IProposalContestPhaseAttribute attr = proposalPhaseClient
                .getProposalContestPhaseAttribute(proposalId, this.getId(),
                        ProposalContestPhaseAttributeKeys.VISIBLE);
        return attr == null || attr.getNumericValue() == 1;

    }

    public boolean setProposalVisibility(long proposalId, boolean visible) {
        ProposalPhaseClientUtil.getClient()
                .setProposalContestPhaseAttribute(proposalId, this.getId(),
                        ProposalContestPhaseAttributeKeys.VISIBLE, 0L, visible ? 1L : 0L, "");
        return true;
    }

    public String getPhaseStatusDescription() {
        return getContestPhaseTypeObject().getDescription();
    }

    public boolean isCompleted() {
        return getStatus() == ContestStatus.COMPLETED;
    }

    public boolean getIsJudged() {
        return getContestPhaseTypeObject().getDefaultPromotionTypeEnum() == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    public boolean isFinalistPhase() {
        List<ContestPhaseWrapper> contestPhases = this.getContest().getVisiblePhases();
        final Predicate<ContestPhaseWrapper> isAfterCurrentPhase =
                phase -> phase.getPhaseStartDateInstant()
                        .isAfter(this.getPhaseStartDateInstant());
        final Predicate<ContestPhaseWrapper> isJudgedPhase =
                phase -> phase.getContestPhaseTypeObject().getDefaultPromotionTypeEnum()
                        == ContestPhasePromoteType.PROMOTE_JUDGED;
        return contestPhases.stream()
                .filter(isAfterCurrentPhase)
                .noneMatch(isJudgedPhase);
    }

    public String getContestPhaseUrl() {
        return this.getContestPhaseLinkUrl();
    }

    public ContestWrapper getContest() {
        return contestClient.getContest(getContestId());
    }

    public boolean getFellowScreeningActive() {
        return getContestPhaseTypeObject().getFellowScreeningActiveDefault();
    }
}
