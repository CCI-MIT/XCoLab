package org.xcolab.client.contest.pojo.team;

import java.io.Serializable;

public class  AbstractContestTeamMemberRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String role;
    private Integer sort;

    public AbstractContestTeamMemberRole() {}

    public AbstractContestTeamMemberRole(AbstractContestTeamMemberRole value) {
        this.id = value.id;
        this.role = value.role;
        this.sort = value.sort;
    }

    public AbstractContestTeamMemberRole(Long id, String role, Integer sort) {
        this.id = id;
        this.role = role;
        this.sort = sort;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

        return "ContestTeamMemberRole (" + id +
                ", " + role +
                ", " + sort +
                ")";
    }
}
