package org.xcolab.client.contest.pojo.phases;

import java.io.Serializable;

abstract class AbstractContestPhaseType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private String name;
    private String description;
    private String status;
    private Boolean fellowscreeningactivedefault;
    private String contestphaseautopromotedefault;
    private Boolean invisible;
    private Integer pointsaccessible;
    private String defaultpromotiontype;
    private String defaultflagtext;

    public AbstractContestPhaseType() {}

    public AbstractContestPhaseType(AbstractContestPhaseType value) {
        this.id_ = value.id_;
        this.name = value.name;
        this.description = value.description;
        this.status = value.status;
        this.fellowscreeningactivedefault = value.fellowscreeningactivedefault;
        this.contestphaseautopromotedefault = value.contestphaseautopromotedefault;
        this.invisible = value.invisible;
        this.pointsaccessible = value.pointsaccessible;
        this.defaultpromotiontype = value.defaultpromotiontype;
        this.defaultflagtext = value.defaultflagtext;
    }

    public AbstractContestPhaseType(Long id_, String name, String description,
            String status, Boolean fellowscreeningactivedefault,
            String contestphaseautopromotedefault, Boolean invisible,
            Integer pointsaccessible, String defaultpromotiontype, String defaultflagtext) {
        this.id_ = id_;
        this.name = name;
        this.description = description;
        this.status = status;
        this.fellowscreeningactivedefault = fellowscreeningactivedefault;
        this.contestphaseautopromotedefault = contestphaseautopromotedefault;
        this.invisible = invisible;
        this.pointsaccessible = pointsaccessible;
        this.defaultpromotiontype = defaultpromotiontype;
        this.defaultflagtext = defaultflagtext;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getFellowScreeningActiveDefault() {
        return this.fellowscreeningactivedefault;
    }

    public void setFellowScreeningActiveDefault(Boolean fellowscreeningactivedefault) {
        this.fellowscreeningactivedefault = fellowscreeningactivedefault;
    }

    public String getContestPhaseAutopromoteDefault() {
        return this.contestphaseautopromotedefault;
    }

    public void setContestPhaseAutopromoteDefault(String contestphaseautopromotedefault) {
        this.contestphaseautopromotedefault = contestphaseautopromotedefault;
    }

    public Boolean getInvisible() {
        return this.invisible;
    }

    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    public Integer getPointsAccessible() {
        return this.pointsaccessible;
    }

    public void setPointsAccessible(Integer pointsaccessible) {
        this.pointsaccessible = pointsaccessible;
    }

    public String getDefaultPromotionType() {
        return this.defaultpromotiontype;
    }

    public void setDefaultPromotionType(String defaultpromotiontype) {
        this.defaultpromotiontype = defaultpromotiontype;
    }

    public String getDefaultFlagText() {
        return this.defaultflagtext;
    }

    public void setDefaultFlagText(String defaultflagtext) {
        this.defaultflagtext = defaultflagtext;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((fellowscreeningactivedefault == null) ? 0
                : fellowscreeningactivedefault.hashCode());
        result = prime * result + ((contestphaseautopromotedefault == null) ? 0
                : contestphaseautopromotedefault.hashCode());
        result = prime * result + ((invisible == null) ? 0 : invisible.hashCode());
        result = prime * result + ((pointsaccessible == null) ? 0 : pointsaccessible.hashCode());
        result = prime * result + ((defaultpromotiontype == null) ? 0
                : defaultpromotiontype.hashCode());
        result = prime * result + ((defaultflagtext == null) ? 0 : defaultflagtext.hashCode());
        return result;
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
        final AbstractContestPhaseType other = (AbstractContestPhaseType) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        } else if (!status.equals(other.status)) {
            return false;
        }
        if (fellowscreeningactivedefault == null) {
            if (other.fellowscreeningactivedefault != null) {
                return false;
            }
        } else if (!fellowscreeningactivedefault.equals(other.fellowscreeningactivedefault)) {
            return false;
        }
        if (contestphaseautopromotedefault == null) {
            if (other.contestphaseautopromotedefault != null) {
                return false;
            }
        } else if (!contestphaseautopromotedefault.equals(other.contestphaseautopromotedefault)) {
            return false;
        }
        if (invisible == null) {
            if (other.invisible != null) {
                return false;
            }
        } else if (!invisible.equals(other.invisible)) {
            return false;
        }
        if (pointsaccessible == null) {
            if (other.pointsaccessible != null) {
                return false;
            }
        } else if (!pointsaccessible.equals(other.pointsaccessible)) {
            return false;
        }
        if (defaultpromotiontype == null) {
            if (other.defaultpromotiontype != null) {
                return false;
            }
        } else if (!defaultpromotiontype.equals(other.defaultpromotiontype)) {
            return false;
        }
        if (defaultflagtext == null) {
            if (other.defaultflagtext != null) {
                return false;
            }
        } else if (!defaultflagtext.equals(other.defaultflagtext)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestPhaseType (" + id_ +
                ", " + name +
                ", " + description +
                ", " + status +
                ", " + fellowscreeningactivedefault +
                ", " + contestphaseautopromotedefault +
                ", " + invisible +
                ", " + pointsaccessible +
                ", " + defaultpromotiontype +
                ", " + defaultflagtext +
                ")";
    }
}
