package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ContestPhase;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.commons.time.DurationFormatter;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContestPhaseWrapper extends ContestPhase implements Serializable {

    public static final long SCHEDULE_TEMPLATE_PHASE_CONTEST_ID = 0L;

    private final IContestClient contestClient;

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

    @JsonIgnore
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

    @JsonIgnore
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

    @JsonIgnore
    public String getContestStatusStr() {
        return getContestPhaseTypeObject().getStatus();
    }

    @JsonIgnore
    public IContestPhaseType getContestPhaseTypeObject() {
        return contestClient.getContestPhaseType(this.getContestPhaseTypeId());
    }

    @JsonIgnore
    public Date getPhaseStartDateDt() {
        return this.getPhaseStartDate();
    }

    @JsonIgnore
    public Instant getPhaseStartDateInstant() {
        return this.getPhaseStartDate().toInstant();
    }

    @JsonIgnore
    public Date getPhaseEndDateDt() {
        return this.getPhaseEndDate();
    }

    @JsonIgnore
    public Instant getPhaseEndDateInstant() {
        return this.getPhaseEndDate() != null ? this.getPhaseEndDate().toInstant() : null;
    }

    @JsonIgnore
    public Date getPhaseReferenceDate() {
        return (this.getPhaseEndDate() == null) ? this.getPhaseStartDateDt()
                : this.getPhaseEndDateDt();
    }

    @JsonIgnore
    public String getPhaseReferenceYear() {
        Date referenceDate = getPhaseReferenceDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        return "" + cal.get(Calendar.YEAR);
    }

    @JsonIgnore
    public ContestStatus getStatus() {
        if (status == null) {
            status = ContestStatus.valueOf(getContestStatusStr());
        }
        return status;
    }

    @JsonIgnore
    public boolean getCanVote() {
        return (getStatus() != null) && getStatus().isCanVote();
    }

    @JsonIgnore
    public boolean getCanEdit() {
        return (getStatus() != null) && getStatus().isCanEdit();
    }

    @JsonIgnore
    public boolean isActive() {
        return this.getPhaseActive();
    }

    public String getDurationTillEnd() {
        return DurationFormatter.forRequestLocale().formatDifferenceAsDays(getPhaseEndDate());
    }

    @JsonIgnore
    public String getDurationTillEndFormatted() {
        String duration = getDurationTillEnd();
        //surrounds number with <span> tag for formatting
        return duration.replaceAll("\\b(\\d+)\\b", "<span class='c-Count__number'>$1</span>");
    }

    @JsonIgnore
    public String getName() {
        return contestClient.getContestPhaseType(this.getContestPhaseTypeId()).getName();
    }

    @JsonIgnore
    public String getFlagText() {
        IContestPhaseType phaseType = getContestPhaseTypeObject();
        String flagText = phaseType.getDefaultFlagText();
        if (StringUtils.isEmpty(flagText)) {
            flagText = phaseType.getName();
        }
        return flagText;
    }

    @JsonIgnore
    public boolean isEnded() {
        Date now = new Date();
        return (this.getPhaseEndDate() != null) && this.getPhaseEndDate().before(now);
    }

    @JsonIgnore
    public boolean isAlreadyStarted() {
        Date now = new Date();
        final Timestamp phaseStartDate = this.getPhaseStartDate();
        return phaseStartDate != null && phaseStartDate.before(now);
    }

    @JsonIgnore
    public ContestPhasePromoteType getContestPhaseAutopromoteEnum() {
        return ContestPhasePromoteType.getPromoteType(getContestPhaseAutopromote());
    }

    @JsonIgnore
    public Boolean getProposalVisibility(long proposalId) {
        IProposalPhaseClient proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
        IProposalContestPhaseAttribute attr = proposalPhaseClient
                .getProposalContestPhaseAttribute( this.getId(),proposalId,
                        ProposalContestPhaseAttributeKeys.VISIBLE);
        return attr == null || attr.getNumericValue() == 1;

    }

    @JsonIgnore
    public boolean setProposalVisibility(long proposalId, boolean visible) {
        StaticProposalContext.getProposalPhaseClient()
                .setProposalContestPhaseAttribute(proposalId, this.getId(),
                        ProposalContestPhaseAttributeKeys.VISIBLE, 0L, visible ? 1L : 0L, "");
        return true;
    }

    @JsonIgnore
    public String getPhaseStatusDescription() {
        return getContestPhaseTypeObject().getDescription();
    }

    @JsonIgnore
    public boolean isCompleted() {
        return getStatus() == ContestStatus.COMPLETED;
    }

    @JsonIgnore
    public boolean getIsJudged() {
        return getContestPhaseTypeObject().getDefaultPromotionTypeEnum()
                == ContestPhasePromoteType.PROMOTE_JUDGED;
    }

    @JsonIgnore
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

    @JsonIgnore
    public String getContestPhaseUrl() {
        return this.getContestPhaseLinkUrl();
    }

    @JsonIgnore
    public ContestWrapper getContest() {
        return contestClient.getContest(getContestId());
    }

    @JsonIgnore
    public boolean getFellowScreeningActive() {
        return getContestPhaseTypeObject().isFellowScreeningActiveDefault();
    }
}
