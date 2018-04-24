package org.xcolab.client.contest.pojo.phases;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public abstract class AbstractContestPhase implements Serializable, Comparable<AbstractContestPhase> {

    private static final long serialVersionUID = 1L;

    private Long contestphasepk;
    private Long contestpk;
    private Long contestphasetype;
    private Long contestscheduleid;
    private String contestphaseautopromote;
    private Timestamp phasestartdate;
    private Timestamp phaseenddate;
    private Timestamp created;
    private Timestamp updated;

    public AbstractContestPhase() {
    }

    public AbstractContestPhase(AbstractContestPhase value) {
        this.contestphasepk = value.contestphasepk;
        this.contestpk = value.contestpk;
        this.contestphasetype = value.contestphasetype;
        this.contestscheduleid = value.contestscheduleid;
        this.contestphaseautopromote = value.contestphaseautopromote;
        this.phasestartdate = value.phasestartdate;
        this.phaseenddate = value.phaseenddate;
        this.created = value.created;
        this.updated = value.updated;
    }

    public Long getContestPhasePK() {
        return this.contestphasepk;
    }

    public void setContestPhasePK(Long contestphasepk) {
        this.contestphasepk = contestphasepk;
    }

    public Long getContestPK() {
        return this.contestpk;
    }

    public void setContestPK(Long contestpk) {
        this.contestpk = contestpk;
    }

    public Long getContestPhaseType() {
        return this.contestphasetype;
    }

    public void setContestPhaseType(Long contestphasetype) {
        this.contestphasetype = contestphasetype;
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

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
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
        return Objects.equals(contestphasepk, that.contestphasepk)
                && Objects.equals(contestpk, that.contestpk)
                && Objects.equals(contestphasetype, that.contestphasetype)
                && Objects.equals(contestscheduleid, that.contestscheduleid)
                && Objects.equals(contestphaseautopromote, that.contestphaseautopromote)
                && Objects.equals(phasestartdate, that.phasestartdate)
                && Objects.equals(phaseenddate, that.phaseenddate)
                && Objects.equals(getCreated(), that.getCreated())
                && Objects.equals(getUpdated(), that.getUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(contestphasepk, contestpk, contestphasetype, contestscheduleid,
                contestphaseautopromote, phasestartdate, phaseenddate, getCreated(), getUpdated());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contestphasepk", contestphasepk)
                .append("contestpk", contestpk)
                .append("contestphasetype", contestphasetype)
                .append("contestscheduleid", contestscheduleid)
                .append("contestphaseautopromote", contestphaseautopromote)
                .append("phasestartdate", phasestartdate)
                .append("phaseenddate", phaseenddate)
                .append("created", created).append("updated", updated).toString();
    }
}
