package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposalTeamMembershipRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalId;
    private Long userid;
    private Timestamp createdAt;
    private String comments;
    private String replycomments;
    private Timestamp replydate;
    private Long replieruserid;
    private Integer statusid;

    public AbstractProposalTeamMembershipRequest() {}

    public AbstractProposalTeamMembershipRequest(AbstractProposalTeamMembershipRequest value) {
        this.id = value.id;
        this.proposalId = value.proposalId;
        this.userid = value.userid;
        this.createdAt = value.createdAt;
        this.comments = value.comments;
        this.replycomments = value.replycomments;
        this.replydate = value.replydate;
        this.replieruserid = value.replieruserid;
        this.statusid = value.statusid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long membershiprequestid) {
        this.id = membershiprequestid;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReplyComments() {
        return this.replycomments;
    }

    public void setReplyComments(String replycomments) {
        this.replycomments = replycomments;
    }

    public Timestamp getReplyDate() {
        return this.replydate;
    }

    public void setReplyDate(Timestamp replydate) {
        this.replydate = replydate;
    }

    public Long getReplierUserId() {
        return this.replieruserid;
    }

    public void setReplierUserId(Long replieruserid) {
        this.replieruserid = replieruserid;
    }

    public Integer getStatusId() {
        return this.statusid;
    }

    public void setStatusId(Integer statusid) {
        this.statusid = statusid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((replycomments == null) ? 0 : replycomments.hashCode());
        result = prime * result + ((replydate == null) ? 0 : replydate.hashCode());
        result = prime * result + ((replieruserid == null) ? 0 : replieruserid.hashCode());
        result = prime * result + ((statusid == null) ? 0 : statusid.hashCode());
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
        final AbstractProposalTeamMembershipRequest other = (AbstractProposalTeamMembershipRequest) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (proposalId == null) {
            if (other.proposalId != null) {
                return false;
            }
        } else if (!proposalId.equals(other.proposalId)) {
            return false;
        }
        if (userid == null) {
            if (other.userid != null) {
                return false;
            }
        } else if (!userid.equals(other.userid)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (replycomments == null) {
            if (other.replycomments != null) {
                return false;
            }
        } else if (!replycomments.equals(other.replycomments)) {
            return false;
        }
        if (replydate == null) {
            if (other.replydate != null) {
                return false;
            }
        } else if (!replydate.equals(other.replydate)) {
            return false;
        }
        if (replieruserid == null) {
            if (other.replieruserid != null) {
                return false;
            }
        } else if (!replieruserid.equals(other.replieruserid)) {
            return false;
        }
        if (statusid == null) {
            if (other.statusid != null) {
                return false;
            }
        } else if (!statusid.equals(other.statusid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "MembershipRequest (" + id + ", " + proposalId + ", " + userid
                + ", " + createdAt + ", " + comments + ", " + replycomments + ", "
                + replydate + ", " + replieruserid + ", " + statusid + ")";

        return sb;
    }
}
