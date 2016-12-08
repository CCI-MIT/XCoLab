package org.xcolab.portlets.contestmanagement.beans;

import org.springframework.format.annotation.DateTimeFormat;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.enums.ContestPhaseTypeValue;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class ContestPhaseBean implements Serializable {

    public static final Long CREATE_CONTEST_PHASE_PK = -1L;
    public static final Long DEFAULT_CONTEST_SCHEDULE = -1L;

    private final static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    private Long contestSchedulePK;
    private Long contestPhasePK;
    private Long contestPK;
    private Long contestPhaseType;
    private Long contestPhaseTypeOld = 0L;
    private Long contestScheduleId;
    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm")
    @NotNull(message = "Phase start date must not be empty.")
    private Date phaseStartDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm")
    private Date phaseEndDate;
    private Date phaseBufferEndDated;
    private String nextStatus;
    private String contestPhaseDescriptionOverride;
    private String contestPhaseAutopromote;

    private Boolean phaseActiveOverride = false;
    private Boolean phaseInactiveOverride = false;
    private Boolean fellowScreeningActive = false;

    private ContestPhaseType contestPhaseTypeObj;
    private boolean contestPhaseDeleted;
    private boolean contestPhaseHasProposalAssociations;

    @SuppressWarnings("unused")
    public ContestPhaseBean() {
    }

    public ContestPhaseBean(ContestPhase contestPhase) {
        this.contestPhasePK = contestPhase.getContestPhasePK();
        this.contestPK = contestPhase.getContestPK();
        this.contestPhaseType = contestPhase.getContestPhaseType();
        if (contestPhase.getContestPhaseType() != null) {
            this.contestPhaseTypeOld = contestPhase.getContestPhaseType();
        }
        this.contestScheduleId = contestPhase.getContestScheduleId();
        this.phaseStartDate = contestPhase.getPhaseStartDate();
        this.phaseEndDate = contestPhase.getPhaseEndDate();
        this.phaseBufferEndDated = contestPhase.getPhaseBufferEndDated();
        if (contestPhase.getFellowScreeningActive() != null) {
            this.fellowScreeningActive = contestPhase.getFellowScreeningActive();
        }

        this.contestPhaseAutopromote = contestPhase.getContestPhaseAutopromote();

        this.contestPhaseDescriptionOverride = contestPhase.getContestPhaseDescriptionOverride();
        if (contestPhase.getPhaseActiveOverride() != null) {
            this.phaseActiveOverride = contestPhase.getPhaseActiveOverride();
        }
        if (contestPhase.getPhaseInactiveOverride() != null) {
            this.phaseInactiveOverride = contestPhase.getPhaseInactiveOverride();
        }
        this.nextStatus = contestPhase.getNextStatus();
        if (contestPhaseType != null) {
            this.contestPhaseTypeObj = ContestClientUtil.getContestPhaseType(contestPhaseType);
        }

        this.contestPhaseHasProposalAssociations = false;
        List<Contest> contestsUsingThisContestPhase = ContestClientUtil
                .getContestsByContestScheduleId(this.contestScheduleId);
        for (Contest contest : contestsUsingThisContestPhase) {
            List<ContestPhase> contestPhases = ContestClientUtil
                    .getPhasesForContestScheduleIdAndContest(this.contestScheduleId,
                            contest.getContestPK());
            for (ContestPhase contestPhase1 : contestPhases) {
                if (Objects
                        .equals(contestPhase1.getContestPhaseType(), this.contestPhaseType)) {
                    List<Proposal2Phase> proposal2PhaseList = ProposalPhaseClientUtil.
                            getProposal2PhaseByContestPhaseId(contestPhase1.getContestPhasePK());
                    if (!proposal2PhaseList.isEmpty()) {
                        this.contestPhaseHasProposalAssociations = true;
                        break;
                    }
                }
            }
        }
    }

    public ContestPhaseBean(ContestPhaseTypeValue contestPhaseType, Date phaseStartDate,
            Date phaseEndDate, String contestPhaseAutopromote, Boolean fellowScreeningActive) {
        this.phaseStartDate = phaseStartDate;
        this.phaseEndDate = phaseEndDate;
        this.fellowScreeningActive = fellowScreeningActive;
        this.contestPhaseType = contestPhaseType.getTypeId();
    }

    public ContestPhaseBean(Long contestPhaseType, Date phaseStartDate) {
        this.contestPhaseType = contestPhaseType;
        this.phaseStartDate = phaseStartDate;
    }

    public Long getContestSchedulePK() {
        return contestSchedulePK;
    }

    public void setContestSchedulePK(Long contestSchedulePK) {
        this.contestSchedulePK = contestSchedulePK;
    }

    public Long getContestPhasePK() {
        return contestPhasePK;
    }

    public void setContestPhasePK(Long contestPhasePK) {
        this.contestPhasePK = contestPhasePK;
    }

    public Long getContestPK() {
        return contestPK;
    }

    public void setContestPK(Long contestPK) {
        this.contestPK = contestPK;
    }

    public Long getContestPhaseType() {
        return contestPhaseType;
    }

    public void setContestPhaseType(Long contestPhaseType) {
        this.contestPhaseType = contestPhaseType;
    }

    public boolean isFellowScreeningActive() {
        return fellowScreeningActive;
    }

    public void setFellowScreeningActive(boolean fellowScreeningActive) {
        this.fellowScreeningActive = fellowScreeningActive;
    }

    public Date getPhaseStartDate() {
        return phaseStartDate;
    }

    public void setPhaseStartDate(Date phaseStartDate) {
        this.phaseStartDate = phaseStartDate;
    }

    public String getPhaseEndDateFormatted() {
        String phaseEndDateFormatted = "";
        if (phaseEndDate != null) {
            phaseEndDateFormatted = dateFormat.format(phaseEndDate);
        }
        return phaseEndDateFormatted;
    }

    public void setPhaseEndDateFormatted(String phaseEndDateFormatted) {
        if (phaseEndDateFormatted != null) {
            try {
                this.phaseEndDate = dateFormat.parse(phaseEndDateFormatted);
            } catch (ParseException e) {
                this.phaseEndDate = null;
            }
        }
    }

    public Date getPhaseEndDate() {
        return phaseEndDate;
    }

    public void setPhaseEndDate(Date phaseEndDate) {
        this.phaseEndDate = phaseEndDate;
    }

    public Date getPhaseBufferEndDated() {
        return phaseBufferEndDated;
    }

    public void setPhaseBufferEndDated(Date phaseBufferEndDated) {
        this.phaseBufferEndDated = phaseBufferEndDated;
    }

    public boolean getFellowScreeningActive() {
        return fellowScreeningActive;
    }

    public void setFellowScreeningActive(Boolean fellowScreeningActive) {
        this.fellowScreeningActive = fellowScreeningActive;
    }

    public String getContestPhaseAutopromote() {
        return contestPhaseAutopromote;
    }

    public void setContestPhaseAutopromote(String contestPhaseAutopromote) {
        this.contestPhaseAutopromote = contestPhaseAutopromote;
    }

    public String getContestPhaseTypeTitle() {
        String contestPhaseTypeTitle = "";
        if (contestPhaseTypeObj != null) {
            contestPhaseTypeTitle = contestPhaseTypeObj.getName();
        }
        return contestPhaseTypeTitle;
    }

    public boolean getHasBuffer() {
        boolean isPhaseBufferEndDateAfterPhaseEndDate = false;
        if (this.phaseBufferEndDated != null) {
            isPhaseBufferEndDateAfterPhaseEndDate = (this.phaseBufferEndDated
                    .after(this.phaseEndDate));
        }
        return isPhaseBufferEndDateAfterPhaseEndDate;
    }

    public Long getContestScheduleId() {
        return contestScheduleId;
    }

    public void setContestScheduleId(Long contestScheduleId) {
        this.contestScheduleId = contestScheduleId;
    }

    public boolean isContestPhaseDeleted() {
        return contestPhaseDeleted;
    }

    public void setContestPhaseDeleted(boolean contestPhaseDeleted) {
        this.contestPhaseDeleted = contestPhaseDeleted;
    }

    public boolean isContestPhaseHasProposalAssociations() {
        return contestPhaseHasProposalAssociations;
    }

    public void setContestPhaseProposalAssociations(boolean contestPhaseHasProposalAssociations) {
        this.contestPhaseHasProposalAssociations = contestPhaseHasProposalAssociations;
    }

    public void persist() {
        if (contestPhasePK.equals(CREATE_CONTEST_PHASE_PK)) {
            createNewContestPhase();
        }

        if (contestPhaseDeleted) {
            ContestClientUtil.deleteContestPhase(contestPhasePK);
        } else {
            ContestClientUtil.updateContestPhase(getContestPhase());
        }

    }

    private void createNewContestPhase() {
        ContestPhase contestPhase = new ContestPhase();
        contestPhase = ContestClientUtil.createContestPhase(contestPhase);
        contestPhasePK = contestPhase.getContestPhasePK();
    }

    public String getContestPhaseDescriptionOverride() {
        return contestPhaseDescriptionOverride;
    }

    public void setContestPhaseDescriptionOverride(String contestPhaseDescriptionOverride) {
        this.contestPhaseDescriptionOverride = contestPhaseDescriptionOverride;
    }

    public Long getContestPhaseTypeOld() {
        return contestPhaseTypeOld;
    }

    public void setContestPhaseTypeOld(Long contestPhaseTypeOld) {
        this.contestPhaseTypeOld = contestPhaseTypeOld;
    }

    //TODO: improve naming?
    public ContestPhase getContestPhase() {
        ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhasePK);
        contestPhase.setContestPK(contestPK);
        contestPhase.setContestPhaseType(contestPhaseType);
        contestPhase.setContestScheduleId(contestScheduleId);
        contestPhase.setPhaseStartDate(new Timestamp(phaseStartDate.getTime()));
        if (phaseEndDate != null) {
            contestPhase.setPhaseEndDate(new Timestamp(phaseEndDate.getTime()));
        } else {
            contestPhase.setPhaseEndDate(null);
        }
        if (phaseBufferEndDated != null) {
            contestPhase.setPhaseBufferEndDated(new Timestamp(phaseBufferEndDated.getTime()));
        }
        contestPhase.setFellowScreeningActive(fellowScreeningActive);

        if (contestPhaseAutopromote
                .equalsIgnoreCase(ContestPhasePromoteType.DEFAULT.getValue())) {
            ContestPhaseType type = ContestClientUtil.getContestPhaseType(contestPhaseType);
            contestPhase.setContestPhaseAutopromote(type.getDefaultPromotionType());
        } else {
            contestPhase.setContestPhaseAutopromote(contestPhaseAutopromote);
        }

        contestPhase.setContestPhaseDescriptionOverride(contestPhaseDescriptionOverride);
        contestPhase.setPhaseActiveOverride(phaseActiveOverride);
        contestPhase.setPhaseInactiveOverride(phaseInactiveOverride);
        contestPhase.setNextStatus(nextStatus);
        return contestPhase;

    }
}
