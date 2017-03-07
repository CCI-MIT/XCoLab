package org.xcolab.client.contest.pojo.phases;

import java.io.Serializable;

abstract class AbstractContestPhaseRibbonType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Integer ribbon;
    private String title;
    private String hovertext;
    private Boolean showText;
    private String description;
    private Boolean copyonpromote;
    private Integer sortorder;

    public AbstractContestPhaseRibbonType() {}

    public AbstractContestPhaseRibbonType(AbstractContestPhaseRibbonType value) {
        this.id_ = value.id_;
        this.ribbon = value.ribbon;
        this.title = value.title;
        this.hovertext = value.hovertext;
        this.showText = value.showText;
        this.description = value.description;
        this.copyonpromote = value.copyonpromote;
        this.sortorder = value.sortorder;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public Integer getRibbon() {
        return this.ribbon;
    }

    public void setRibbon(Integer ribbon) {
        this.ribbon = ribbon;
    }

    public String getHoverText() {
        return this.hovertext;
    }

    public void setHoverText(String hovertext) {
        this.hovertext = hovertext;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCopyOnPromote() {
        return this.copyonpromote;
    }

    public void setCopyOnPromote(Boolean copyonpromote) {
        this.copyonpromote = copyonpromote;
    }

    public Integer getSortOrder() {
        return this.sortorder;
    }

    public void setSortOrder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShowText() {
        return showText;
    }

    public void setShowText(Boolean showText) {
        this.showText = showText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((ribbon == null) ? 0 : ribbon.hashCode());
        result = prime * result + ((hovertext == null) ? 0 : hovertext.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((copyonpromote == null) ? 0 : copyonpromote.hashCode());
        result = prime * result + ((sortorder == null) ? 0 : sortorder.hashCode());
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
        final AbstractContestPhaseRibbonType other = (AbstractContestPhaseRibbonType) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (ribbon == null) {
            if (other.ribbon != null) {
                return false;
            }
        } else if (!ribbon.equals(other.ribbon)) {
            return false;
        }
        if (hovertext == null) {
            if (other.hovertext != null) {
                return false;
            }
        } else if (!hovertext.equals(other.hovertext)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (copyonpromote == null) {
            if (other.copyonpromote != null) {
                return false;
            }
        } else if (!copyonpromote.equals(other.copyonpromote)) {
            return false;
        }
        if (sortorder == null) {
            if (other.sortorder != null) {
                return false;
            }
        } else if (!sortorder.equals(other.sortorder)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestPhaseRibbonType (" + id_ +
                ", " + ribbon +
                ", " + hovertext +
                ", " + description +
                ", " + copyonpromote +
                ", " + sortorder +
                ")";
    }
}
