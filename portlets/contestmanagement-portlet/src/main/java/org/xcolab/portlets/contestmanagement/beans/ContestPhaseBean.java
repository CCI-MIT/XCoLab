package org.xcolab.portlets.contestmanagement.beans;


import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.format.annotation.DateTimeFormat;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestPhaseTypeValue;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Thomas on 2/20/2015.
 */
public class ContestPhaseBean implements Serializable {
    public static final Long CREATE_PHASE_CONTEST_PK = -1L;

    private Long contestSchedulePK;
    private Long contestPhasePK;
    private Long contestPK;
    private Long contestPhaseType;
    private Long contestPhaseTypeOld;
    private Long contestScheduleId;
    private final static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

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
    public ContestPhaseBean() { }

    public ContestPhaseBean(ContestPhase contestPhase) {
        this.contestPhasePK = contestPhase.getContestPhasePK();
        this.contestPK = contestPhase.getContestPK();
        this.contestPhaseType = contestPhase.getContestPhaseType();
        this.contestPhaseTypeOld = contestPhase.getContestPhaseType();
        this.contestScheduleId = contestPhase.getContestScheduleId();
        this.phaseStartDate = contestPhase.getPhaseStartDate();
        this.phaseEndDate = contestPhase.getPhaseEndDate();
        this.phaseBufferEndDated = contestPhase.getPhaseBufferEndDated();
        this.fellowScreeningActive = contestPhase.getFellowScreeningActive();
        this.contestPhaseAutopromote = contestPhase.getContestPhaseAutopromote();
        this.contestPhaseDescriptionOverride = contestPhase.getContestPhaseDescriptionOverride();
        this.phaseActiveOverride = contestPhase.getPhaseActiveOverride();
        this.phaseInactiveOverride = contestPhase.getPhaseInactiveOverride();
        this.nextStatus = contestPhase.getNextStatus();

        this.contestPhaseTypeObj = ContestClient.getContestPhaseType(contestPhaseType);


        try {
            this.contestPhaseHasProposalAssociations = false;
            List<Contest> contestsUsingThisContestPhase = ContestClient.getContestsByContestScheduleId(this.contestScheduleId);
            for (Contest contest : contestsUsingThisContestPhase) {
                List<ContestPhase> contestPhases = ContestClient.getPhasesForContestScheduleIdAndContest(this.contestScheduleId, contest.getContestPK());
                for (ContestPhase contestPhase1 : contestPhases) {
                    if (contestPhase1.getContestPhaseType() == this.contestPhaseType) {
                        List<Proposal2Phase> proposal2PhaseList = Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhase1.getContestPhasePK());
                        if (!proposal2PhaseList.isEmpty()) {
                            this.contestPhaseHasProposalAssociations = true;
                            break;
                        }
                    }
                }
            }
        } catch (SystemException ignored) { }
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
            isPhaseBufferEndDateAfterPhaseEndDate = (this.phaseBufferEndDated.after(this.phaseEndDate));
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
        if (contestPhasePK.equals(CREATE_PHASE_CONTEST_PK)) {
            createNewContestPhase();
        }

            if (contestPhaseDeleted) {
                ContestPhase contestPhase = ContestClient
                        .getContestPhase(contestPhasePK);
                ContestClient.deleteContestPhase(contestPhase.getContestPhasePK());
            } else {
                ContestClient.updateContestPhase(getContestPhase());
            }

    }

    private void createNewContestPhase() {
            ContestPhase contestPhase = new ContestPhase();
            contestPhase = ContestClient.createContestPhase(contestPhase);
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
            ContestPhase contestPhase = ContestClient.getContestPhase(contestPhasePK);
            contestPhase.setContestPK(contestPK);
            contestPhase.setContestPhaseType(contestPhaseType);
            contestPhase.setContestScheduleId(contestScheduleId);
            contestPhase.setPhaseStartDate(new Timestamp(phaseStartDate.getTime()));
            contestPhase.setPhaseEndDate( new Timestamp(phaseEndDate.getTime()));
            contestPhase.setPhaseBufferEndDated( new Timestamp(phaseBufferEndDated.getTime()));
            contestPhase.setFellowScreeningActive(fellowScreeningActive);

            if (contestPhaseAutopromote
                    .equalsIgnoreCase(ContestPhasePromoteType.DEFAULT.getValue())) {
                ContestPhaseType type = ContestClient.getContestPhaseType(contestPhaseType);
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
