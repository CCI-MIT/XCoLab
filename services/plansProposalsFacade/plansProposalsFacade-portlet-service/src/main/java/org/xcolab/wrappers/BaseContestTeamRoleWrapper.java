package org.xcolab.wrappers;

import org.xcolab.client.members.pojo.Member;

import java.io.Serializable;
import java.util.List;

public class BaseContestTeamRoleWrapper implements Serializable, Comparable<BaseContestTeamRoleWrapper> {

    private static final long serialVersionUID = 1L;
    private String roleName;
    private List<Member> users;
    private int sort;

    public BaseContestTeamRoleWrapper(String roleName, List<Member> users, int sort) {
        super();
        this.roleName = roleName;
        this.users = users;
        this.sort = sort;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Member> getUsers() {
        return users;
    }

    public void setUsers(List<Member> users) {
        this.users = users;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(BaseContestTeamRoleWrapper bctrw) {
        return this.sort - bctrw.sort;
    }
}
