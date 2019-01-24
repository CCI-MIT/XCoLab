package org.xcolab.view.pages.contestmanagement.beans;

import org.springframework.format.annotation.DateTimeFormat;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.contest.ContestPhaseTypeValue;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class ContestPhaseBean implements Serializable {

    public static final Long CREATE_CONTEST_PHASE_PK = -1L;
    public static final Long DEFAULT_CONTEST_SCHEDULE = -1L;

    private Long id;
    private Long contestId;
    private Long contestPhaseTypeId;
    private Long contestPhaseTypeIdOld;
    private Long contestScheduleId;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    @NotNull(message = "Phase start date must not be empty.")
    private Date phaseStartDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date phaseEndDate;

    private boolean contestPhaseDeleted;
    private boolean contestPhaseHasProposalAssociations;

    @SuppressWarnings("unused")
    public ContestPhaseBean() {
    }

    public ContestPhaseBean(ContestPhaseWrapper contestPhase) {
        this.id = contestPhase.getId();
        this.contestId = contestPhase.getContestId();
        this.contestPhaseTypeId = contestPhase.getContestPhaseTypeId();
        if (contestPhase.getContestPhaseTypeId() != null) {
            this.contestPhaseTypeIdOld = contestPhase.getContestPhaseTypeId();
        }
        this.contestScheduleId = contestPhase.getContestScheduleId();
        this.phaseStartDate = contestPhase.getPhaseStartDate();
        this.phaseEndDate = contestPhase.getPhaseEndDate();

        this.contestPhaseHasProposalAssociations = false;
        List<ContestWrapper> contestsUsingThisContestPhase = StaticContestContext.getContestClient()
                .getContestsByContestScheduleId(this.contestScheduleId);
        for (ContestWrapper contest : contestsUsingThisContestPhase) {
            List<ContestPhaseWrapper> contestPhases = StaticContestContext.getContestClient()
                    .getPhasesForContestScheduleIdAndContest(this.contestScheduleId,
                            contest.getId());
            for (ContestPhaseWrapper contestPhase1 : contestPhases) {
                if (Objects
                        .equals(contestPhase1.getContestPhaseTypeId(), this.contestPhaseTypeId)) {
                    List<IProposal2Phase> proposal2PhaseList = StaticProposalContext
                            .getProposalPhaseClient().
                            getProposal2PhaseByContestPhaseId(contestPhase1.getId());
                    if (!proposal2PhaseList.isEmpty()) {
                        this.contestPhaseHasProposalAssociations = true;
                        break;
                    }
                }
            }
        }
    }

    public ContestPhaseBean(ContestPhaseTypeValue contestPhaseTypeId, Date phaseStartDate,
            Date phaseEndDate) {
        this.phaseStartDate = phaseStartDate;
        this.phaseEndDate = phaseEndDate;
        this.contestPhaseTypeId = contestPhaseTypeId.getTypeId();
    }

    public ContestPhaseBean(Long contestPhaseTypeId, Date phaseStartDate) {
        this.contestPhaseTypeId = contestPhaseTypeId;
        this.phaseStartDate = phaseStartDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getContestPhaseTypeId() {
        return contestPhaseTypeId;
    }

    public void setContestPhaseTypeId(Long contestPhaseTypeId) {
        this.contestPhaseTypeId = contestPhaseTypeId;
    }

    public Date getPhaseStartDate() {
        return phaseStartDate;
    }

    public void setPhaseStartDate(Date phaseStartDate) {
        this.phaseStartDate = phaseStartDate;
    }

    public Date getPhaseEndDate() {
        return phaseEndDate;
    }

    public void setPhaseEndDate(Date phaseEndDate) {
        this.phaseEndDate = phaseEndDate;
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
        if (id.equals(CREATE_CONTEST_PHASE_PK)) {
            createNewContestPhase();
        }

        if (contestPhaseDeleted) {
            StaticContestContext.getContestClient().deleteContestPhase(id);
        } else {
            StaticContestContext.getContestClient().updateContestPhase(getContestPhase());
        }

    }

    private void createNewContestPhase() {
        ContestPhaseWrapper contestPhase = new ContestPhaseWrapper();
        contestPhase = StaticContestContext.getContestClient().createContestPhase(contestPhase);
        id = contestPhase.getId();
    }

    //TODO COLAB-2595: improve naming?
    public ContestPhaseWrapper getContestPhase() {
        ContestPhaseWrapper contestPhase;
        if (id != null && id > 0) {
            contestPhase = StaticContestContext.getContestClient().getContestPhase(id);
        } else {
            contestPhase = new ContestPhaseWrapper();
        }
        contestPhase.setContestId(contestId);
        if (contestPhaseTypeId != null) {
            contestPhase.setContestPhaseTypeId(contestPhaseTypeId);
        } else {
            contestPhase.setContestPhaseTypeId(contestPhaseTypeIdOld);
        }
        contestPhase.setContestScheduleId(contestScheduleId);
        if (phaseStartDate != null) {
            contestPhase.setPhaseStartDate(new Timestamp(phaseStartDate.getTime()));
        } else {
            contestPhase.setPhaseStartDate(null);
        }
        if (phaseEndDate != null) {
            contestPhase.setPhaseEndDate(new Timestamp(phaseEndDate.getTime()));
        } else {
            contestPhase.setPhaseEndDate(null);
        }

        if (contestPhaseTypeId != null) {
            IContestPhaseType type = StaticContestContext.getContestClient()
                    .getContestPhaseType(contestPhaseTypeId);
            contestPhase.setContestPhaseAutopromote(type.getDefaultPromotionType());
        } else if (contestPhaseTypeIdOld != null) {
            IContestPhaseType type = StaticContestContext.getContestClient()
                    .getContestPhaseType(contestPhaseTypeIdOld);
            contestPhase.setContestPhaseAutopromote(type.getDefaultPromotionType());
        } else {
            contestPhase.setContestPhaseAutopromote(null);
        }
        return contestPhase;
    }

    public Long getContestPhaseTypeIdOld() {
        return contestPhaseTypeIdOld;
    }

    public void setContestPhaseTypeIdOld(Long contestPhaseTypeIdOld) {
        this.contestPhaseTypeIdOld = contestPhaseTypeIdOld;
    }
}
