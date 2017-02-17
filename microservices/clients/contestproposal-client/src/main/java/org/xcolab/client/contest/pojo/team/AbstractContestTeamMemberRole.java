package org.xcolab.client.contest.pojo.team;

import java.io.Serializable;

public class  AbstractContestTeamMemberRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private String role;
    private Integer sort;

    public AbstractContestTeamMemberRole() {}

    public AbstractContestTeamMemberRole(AbstractContestTeamMemberRole value) {
        this.id_ = value.id_;
        this.role = value.role;
        this.sort = value.sort;
    }

    public AbstractContestTeamMemberRole(Long id_, String role, Integer sort) {
        this.id_ = id_;
        this.role = role;
        this.sort = sort;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
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
        final AbstractContestTeamMemberRole other = (AbstractContestTeamMemberRole) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
            return false;
        }
        if (sort == null) {
            if (other.sort != null) {
                return false;
            }
        } else if (!sort.equals(other.sort)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ContestTeamMemberRole (" + id_ +
                ", " + role +
                ", " + sort +
                ")";
    }
}
