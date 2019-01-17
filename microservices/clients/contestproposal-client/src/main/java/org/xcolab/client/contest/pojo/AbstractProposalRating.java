package org.xcolab.client.contest.pojo;

import java.io.Serializable;

class AbstractProposalRating implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalid;
    private Long contestphaseid;
    private Long userid;
    private Long ratingvalueid;
    private String comment;
    private Boolean commentEnabled;
    private String otherdatastring;
    private Boolean onlyforinternalusage;

    public AbstractProposalRating() {}

    public AbstractProposalRating(AbstractProposalRating value) {
        this.id = value.id;
        this.proposalid = value.proposalid;
        this.contestphaseid = value.contestphaseid;
        this.userid = value.userid;
        this.ratingvalueid = value.ratingvalueid;
        this.comment = value.comment;
        this.commentEnabled = value.commentEnabled;
        this.otherdatastring = value.otherdatastring;
        this.onlyforinternalusage = value.onlyforinternalusage;
    }

    public AbstractProposalRating(
            Long id,
            Long proposalid,
            Long contestphaseid,
            Long userid,
            Long ratingvalueid,
            String comment,
            Boolean commentEnabled,
            String otherdatastring,
            Boolean onlyforinternalusage
    ) {
        this.id = id;
        this.proposalid = proposalid;
        this.contestphaseid = contestphaseid;
        this.userid = userid;
        this.ratingvalueid = ratingvalueid;
        this.comment = comment;
        this.commentEnabled = commentEnabled;
        this.otherdatastring = otherdatastring;
        this.onlyforinternalusage = onlyforinternalusage;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getContestPhaseId() {
        return this.contestphaseid;
    }

    public void setContestPhaseId(Long contestphaseid) {
        this.contestphaseid = contestphaseid;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public String getOtherDataString() {
        return this.otherdatastring;
    }

    public void setOtherDataString(String otherdatastring) {
        this.otherdatastring = otherdatastring;
    }

    public Boolean getOnlyForInternalUsage() {
        return this.onlyforinternalusage;
    }

    public void setOnlyForInternalUsage(Boolean onlyforinternalusage) {
        this.onlyforinternalusage = onlyforinternalusage;
    }

    public Long getRatingValueId() {
        return this.ratingvalueid;
    }

    public void setRatingValueId(Long ratingvalueid) {
        this.ratingvalueid = ratingvalueid;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getCommentEnabled() {
        return this.commentEnabled;
    }

    public void setCommentEnabled(Boolean commentEnabled) {
        this.commentEnabled = commentEnabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((contestphaseid == null) ? 0 : contestphaseid.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
        result = prime * result + ((ratingvalueid == null) ? 0 : ratingvalueid.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((commentEnabled == null) ? 0 : commentEnabled.hashCode());
        result = prime * result + ((otherdatastring == null) ? 0 : otherdatastring.hashCode());
        result = prime * result + ((onlyforinternalusage == null) ? 0
                : onlyforinternalusage.hashCode());
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
        final AbstractProposalRating other = (AbstractProposalRating) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (contestphaseid == null) {
            if (other.contestphaseid != null) {
                return false;
            }
        } else if (!contestphaseid.equals(other.contestphaseid)) {
            return false;
        }
        if (userid == null) {
            if (other.userid != null) {
                return false;
            }
        } else if (!userid.equals(other.userid)) {
            return false;
        }
        if (ratingvalueid == null) {
            if (other.ratingvalueid != null) {
                return false;
            }
        } else if (!ratingvalueid.equals(other.ratingvalueid)) {
            return false;
        }
        if (comment == null) {
            if (other.comment != null) {
                return false;
            }
        } else if (!comment.equals(other.comment)) {
            return false;
        }
        if (commentEnabled == null) {
            if (other.commentEnabled != null) {
                return false;
            }
        } else if (!commentEnabled.equals(other.commentEnabled)) {
            return false;
        }
        if (otherdatastring == null) {
            if (other.otherdatastring != null) {
                return false;
            }
        } else if (!otherdatastring.equals(other.otherdatastring)) {
            return false;
        }
        if (onlyforinternalusage == null) {
            if (other.onlyforinternalusage != null) {
                return false;
            }
        } else if (!onlyforinternalusage.equals(other.onlyforinternalusage)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "ProposalRating (" + id + ", " + proposalid + ", " + contestphaseid + ", " + userid
                        + ", " + ratingvalueid + ", " + comment + ", " + commentEnabled + ", "
                        + otherdatastring + ", " + onlyforinternalusage + ")";

        return sb;
    }
}
