package org.xcolab.view.pages.contestmanagement.wrappers;

import org.hibernate.validator.constraints.Length;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.PlatformTeam;

import java.util.List;


public class PlatformTeamBean {
    private PlatformTeam team;
    private List<Member> members;

    private Long teamId;
    @Length(max = 35, message = "The team name is limited to 35 characters.")
    private String teamName;

    public PlatformTeamBean() { }

    public PlatformTeamBean(PlatformTeam team, List<Member> members) {
        this.team = team;
        this.members = members;
    }

    public Long getTeamId() {
        return team == null ? teamId : team.getId();
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return team == null ? teamName : team.getName();
    }

    public void setName(String name) {
        this.teamName = name;
    }

    public List<Member> getMembers() {
        return members;
    }
}
