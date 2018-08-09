package org.xcolab.client.contest.pojo.phases;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public abstract class AbstractContestPhase implements Serializable, Comparable<AbstractContestPhase> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contestId;
    private Long contestPhaseTypeId;
    private Long contestscheduleid;
    private String contestphaseautopromote;
    private Timestamp phasestartdate;
    private Timestamp phaseenddate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AbstractContestPhase() {
    }

    public AbstractContestPhase(AbstractContestPhase value) {
        this.id = value.id;
        this.contestId = value.contestId;
        this.contestPhaseTypeId = value.contestPhaseTypeId;
        this.contestscheduleid = value.contestscheduleid;
        this.contestphaseautopromote = value.contestphaseautopromote;
        this.phasestartdate = value.phasestartdate;
        this.phaseenddate = value.phaseenddate;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long contestPhaseId) {
        this.id = contestPhaseId;
    }

    public Long getContestId() {
        return this.contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getContestPhaseTypeId() {
        return this.contestPhaseTypeId;
    }

    public void setContestPhaseTypeId(Long contestphasetype) {
        this.contestPhaseTypeId = contestphasetype;
    }

    public Long getContestScheduleId() {
        return this.contestscheduleid;
    }

    public void setContestScheduleId(Long contestscheduleid) {
        this.contestscheduleid = contestscheduleid;
    }

    public String getContestPhaseAutopromote() {
        return this.contestphaseautopromote;
    }

    public void setContestPhaseAutopromote(String contestphaseautopromote) {
        this.contestphaseautopromote = contestphaseautopromote;
    }

    public Timestamp getPhaseStartDate() {
        return this.phasestartdate;
    }

    public void setPhaseStartDate(Timestamp phasestartdate) {
        this.phasestartdate = phasestartdate;
    }

    public Timestamp getPhaseEndDate() {
        return this.phaseenddate;
    }

    public void setPhaseEndDate(Timestamp phaseenddate) {
        this.phaseenddate = phaseenddate;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(AbstractContestPhase contestPhase) {
        return Long.compare(getPhaseStartDate().getTime(),
                contestPhase.getPhaseStartDate().getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractContestPhase)) {
            return false;
        }
        AbstractContestPhase that = (AbstractContestPhase) o;
        return Objects.equals(id, that.id)
                && Objects.equals(contestId, that.contestId)
                && Objects.equals(contestPhaseTypeId, that.contestPhaseTypeId)
                && Objects.equals(contestscheduleid, that.contestscheduleid)
                && Objects.equals(contestphaseautopromote, that.contestphaseautopromote)
                && Objects.equals(phasestartdate, that.phasestartdate)
                && Objects.equals(phaseenddate, that.phaseenddate)
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contestId, contestPhaseTypeId, contestscheduleid,
                contestphaseautopromote, phasestartdate, phaseenddate, getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("contestId", contestId)
                .append("contestPhaseTypeId", contestPhaseTypeId)
                .append("contestscheduleid", contestscheduleid)
                .append("contestphaseautopromote", contestphaseautopromote)
                .append("phasestartdate", phasestartdate)
                .append("phaseenddate", phaseenddate)
                .append("created", createdAt).append("updated", updatedAt).toString();
    }
}
