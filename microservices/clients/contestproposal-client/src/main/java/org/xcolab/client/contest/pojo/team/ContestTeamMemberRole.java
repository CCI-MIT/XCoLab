package org.xcolab.client.contest.pojo.team;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class ContestTeamMemberRole extends AbstractContestTeamMemberRole implements Comparable<ContestTeamMemberRole>{

    private String roleName;
    private List<Member> users;


    public ContestTeamMemberRole() {}

    public ContestTeamMemberRole(ContestTeamMemberRole value) {
        super(value);
    }

    public ContestTeamMemberRole(Long id_, String role, Integer sort) {
        super(id_, role, sort);
    }

    public ContestTeamMemberRole(AbstractContestTeamMemberRole abstractContestTeamMemberRole,
            RestService restService) {
        super(abstractContestTeamMemberRole);
    }
    public ContestTeamMemberRole(String roleName, List<Member> users, int sort) {
        super();
        this.roleName = roleName;
        this.users = users;
        this.setSort(sort);
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



    @Override
    public int compareTo(ContestTeamMemberRole bctrw) {
        return this.getSort() - bctrw.getSort();
    }

}
