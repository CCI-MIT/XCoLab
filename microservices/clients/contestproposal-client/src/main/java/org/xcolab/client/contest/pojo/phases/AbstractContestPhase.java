package org.xcolab.client.contest.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractContestPhase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long contestphasepk;
    private Long contestpk;
    private Long contestphasetype;
    private Long contestscheduleid;
    private Boolean fellowscreeningactive;
    private String contestphaseautopromote;
    private String contestphasedescriptionoverride;
    private Boolean phaseactiveoverride;
    private Boolean phaseinactiveoverride;
    private Timestamp phasestartdate;
    private Timestamp phaseenddate;
    private Timestamp phasebufferenddated;
    private String nextstatus;
    private Timestamp created;
    private Timestamp updated;
    private Long authorid;

    public AbstractContestPhase() {
    }

    public AbstractContestPhase(AbstractContestPhase value) {
        this.contestphasepk = value.contestphasepk;
        this.contestpk = value.contestpk;
        this.contestphasetype = value.contestphasetype;
        this.contestscheduleid = value.contestscheduleid;
        this.fellowscreeningactive = value.fellowscreeningactive;
        this.contestphaseautopromote = value.contestphaseautopromote;
        this.contestphasedescriptionoverride = value.contestphasedescriptionoverride;
        this.phaseactiveoverride = value.phaseactiveoverride;
        this.phaseinactiveoverride = value.phaseinactiveoverride;
        this.phasestartdate = value.phasestartdate;
        this.phaseenddate = value.phaseenddate;
        this.phasebufferenddated = value.phasebufferenddated;
        this.nextstatus = value.nextstatus;
        this.created = value.created;
        this.updated = value.updated;
        this.authorid = value.authorid;
    }

    public AbstractContestPhase(Long contestphasepk, Long contestpk, Long contestphasetype,
            Long contestscheduleid, Boolean fellowscreeningactive, String contestphaseautopromote,
            String contestphasedescriptionoverride, Boolean phaseactiveoverride,
            Boolean phaseinactiveoverride, Timestamp phasestartdate, Timestamp phaseenddate,
            Timestamp phasebufferenddated, String nextstatus, Timestamp created,
            Timestamp updated, Long authorid) {
        this.contestphasepk = contestphasepk;
        this.contestpk = contestpk;
        this.contestphasetype = contestphasetype;
        this.contestscheduleid = contestscheduleid;
        this.fellowscreeningactive = fellowscreeningactive;
        this.contestphaseautopromote = contestphaseautopromote;
        this.contestphasedescriptionoverride = contestphasedescriptionoverride;
        this.phaseactiveoverride = phaseactiveoverride;
        this.phaseinactiveoverride = phaseinactiveoverride;
        this.phasestartdate = phasestartdate;
        this.phaseenddate = phaseenddate;
        this.phasebufferenddated = phasebufferenddated;
        this.nextstatus = nextstatus;
        this.created = created;
        this.updated = updated;
        this.authorid = authorid;
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

    public Boolean getFellowScreeningActive() {
        return this.fellowscreeningactive;
    }

    public void setFellowScreeningActive(Boolean fellowscreeningactive) {
        this.fellowscreeningactive = fellowscreeningactive;
    }

    public String getContestPhaseAutopromote() {
        return this.contestphaseautopromote;
    }

    public void setContestPhaseAutopromote(String contestphaseautopromote) {
        this.contestphaseautopromote = contestphaseautopromote;
    }

    public String getContestPhaseDescriptionOverride() {
        return this.contestphasedescriptionoverride;
    }

    public void setContestPhaseDescriptionOverride(String contestphasedescriptionoverride) {
        this.contestphasedescriptionoverride = contestphasedescriptionoverride;
    }

    public Boolean getPhaseActiveOverride() {
        return this.phaseactiveoverride;
    }

    public void setPhaseActiveOverride(Boolean phaseactiveoverride) {
        this.phaseactiveoverride = phaseactiveoverride;
    }

    public Boolean getPhaseInactiveOverride() {
        return this.phaseinactiveoverride;
    }

    public void setPhaseInactiveOverride(Boolean phaseinactiveoverride) {
        this.phaseinactiveoverride = phaseinactiveoverride;
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

    public Timestamp getPhaseBufferEndDated() {
        return this.phasebufferenddated;
    }

    public void setPhaseBufferEndDated(Timestamp phasebufferenddated) {
        this.phasebufferenddated = phasebufferenddated;
    }

    public String getNextStatus() {
        return this.nextstatus;
    }

    public void setNextStatus(String nextstatus) {
        this.nextstatus = nextstatus;
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

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractContestPhase other = (AbstractContestPhase) obj;
        if (contestphasepk == null) {
            if (other.contestphasepk != null) {
                return false;
            }
        } else if (!contestphasepk.equals(other.contestphasepk)) {
            return false;
        }
        if (contestpk == null) {
            if (other.contestpk != null) {
                return false;
            }
        } else if (!contestpk.equals(other.contestpk)) {
            return false;
        }
        if (contestphasetype == null) {
            if (other.contestphasetype != null) {
                return false;
            }
        } else if (!contestphasetype.equals(other.contestphasetype)) {
            return false;
        }
        if (contestscheduleid == null) {
            if (other.contestscheduleid != null) {
                return false;
            }
        } else if (!contestscheduleid.equals(other.contestscheduleid)) {
            return false;
        }
        if (fellowscreeningactive == null) {
            if (other.fellowscreeningactive != null) {
                return false;
            }
        } else if (!fellowscreeningactive.equals(other.fellowscreeningactive)) {
            return false;
        }
        if (contestphaseautopromote == null) {
            if (other.contestphaseautopromote != null) {
                return false;
            }
        } else if (!contestphaseautopromote.equals(other.contestphaseautopromote)) {
            return false;
        }
        if (contestphasedescriptionoverride == null) {
            if (other.contestphasedescriptionoverride != null) {
                return false;
            }
        } else if (!contestphasedescriptionoverride.equals(other.contestphasedescriptionoverride)) {
            return false;
        }
        if (phaseactiveoverride == null) {
            if (other.phaseactiveoverride != null) {
                return false;
            }
        } else if (!phaseactiveoverride.equals(other.phaseactiveoverride)) {
            return false;
        }
        if (phaseinactiveoverride == null) {
            if (other.phaseinactiveoverride != null) {
                return false;
            }
        } else if (!phaseinactiveoverride.equals(other.phaseinactiveoverride)) {
            return false;
        }
        if (phasestartdate == null) {
            if (other.phasestartdate != null) {
                return false;
            }
        } else if (!phasestartdate.equals(other.phasestartdate)) {
            return false;
        }
        if (phaseenddate == null) {
            if (other.phaseenddate != null) {
                return false;
            }
        } else if (!phaseenddate.equals(other.phaseenddate)) {
            return false;
        }
        if (phasebufferenddated == null) {
            if (other.phasebufferenddated != null) {
                return false;
            }
        } else if (!phasebufferenddated.equals(other.phasebufferenddated)) {
            return false;
        }
        if (nextstatus == null) {
            if (other.nextstatus != null) {
                return false;
            }
        } else if (!nextstatus.equals(other.nextstatus)) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        } else if (!created.equals(other.created)) {
            return false;
        }
        if (updated == null) {
            if (other.updated != null) {
                return false;
            }
        } else if (!updated.equals(other.updated)) {
            return false;
        }
        if (authorid == null) {
            if (other.authorid != null) {
                return false;
            }
        } else if (!authorid.equals(other.authorid)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contestphasepk == null) ? 0 : contestphasepk.hashCode());
        result = prime * result + ((contestpk == null) ? 0 : contestpk.hashCode());
        result = prime * result + ((contestphasetype == null) ? 0 : contestphasetype.hashCode());
        result = prime * result + ((contestscheduleid == null) ? 0 : contestscheduleid.hashCode());
        result = prime * result + ((fellowscreeningactive == null) ? 0
                : fellowscreeningactive.hashCode());
        result = prime * result + ((contestphaseautopromote == null) ? 0
                : contestphaseautopromote.hashCode());
        result = prime * result + ((contestphasedescriptionoverride == null) ? 0
                : contestphasedescriptionoverride.hashCode());
        result = prime * result + ((phaseactiveoverride == null) ? 0
                : phaseactiveoverride.hashCode());
        result = prime * result + ((phaseinactiveoverride == null) ? 0
                : phaseinactiveoverride.hashCode());
        result = prime * result + ((phasestartdate == null) ? 0 : phasestartdate.hashCode());
        result = prime * result + ((phaseenddate == null) ? 0 : phaseenddate.hashCode());
        result = prime * result + ((phasebufferenddated == null) ? 0
                : phasebufferenddated.hashCode());
        result = prime * result + ((nextstatus == null) ? 0 : nextstatus.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((updated == null) ? 0 : updated.hashCode());
        result = prime * result + ((authorid == null) ? 0 : authorid.hashCode());
        return result;
    }
    @JsonIgnore
    public int compareTo(AbstractContestPhase contestPhase) {

        return getPhaseStartDate().getTime() == contestPhase.getPhaseStartDate().getTime()?0:(getPhaseStartDate().getTime() < contestPhase.getPhaseStartDate().getTime()?-1:1);
    }

    @Override
    public String toString() {

        return "ContestPhase (" + contestphasepk +
                ", " + contestpk +
                ", " + contestphasetype +
                ", " + contestscheduleid +
                ", " + fellowscreeningactive +
                ", " + contestphaseautopromote +
                ", " + contestphasedescriptionoverride +
                ", " + phaseactiveoverride +
                ", " + phaseinactiveoverride +
                ", " + phasestartdate +
                ", " + phaseenddate +
                ", " + phasebufferenddated +
                ", " + nextstatus +
                ", " + created +
                ", " + updated +
                ", " + authorid +
                ")";
    }
}
