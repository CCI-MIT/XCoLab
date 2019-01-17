package org.xcolab.client.contest.pojo;

import java.io.Serializable;

public class AbstractContestTeamMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contestid;
    private Long userid;
    private Long roleid;

    public AbstractContestTeamMember() {}

    public AbstractContestTeamMember(AbstractContestTeamMember value) {
        this.id = value.id;
        this.contestid = value.contestid;
        this.userid = value.userid;
        this.roleid = value.roleid;
    }

    public AbstractContestTeamMember(Long id, Long contestid, Long userid, Long roleid) {
        this.id = id;
        this.contestid = contestid;
        this.userid = userid;
        this.roleid = roleid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContestId() {
        return this.contestid;
    }

    public void setContestId(Long contestid) {
        this.contestid = contestid;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Long getRoleId() {
        return this.roleid;
    }

    public void setRoleId(Long roleid) {
        this.roleid = roleid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((contestid == null) ? 0 : contestid.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
        result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
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
        final AbstractContestTeamMember other = (AbstractContestTeamMember) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (contestid == null) {
            if (other.contestid != null) {
                return false;
            }
        } else if (!contestid.equals(other.contestid)) {
            return false;
        }
        if (userid == null) {
            if (other.userid != null) {
                return false;
            }
        } else if (!userid.equals(other.userid)) {
            return false;
        }
        if (roleid == null) {
            if (other.roleid != null) {
                return false;
            }
        } else if (!roleid.equals(other.roleid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestTeamMember (" + id +
                ", " + contestid +
                ", " + userid +
                ", " + roleid +
                ")";
    }
}
