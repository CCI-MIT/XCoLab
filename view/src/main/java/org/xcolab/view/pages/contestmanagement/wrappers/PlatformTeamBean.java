package org.xcolab.view.pages.contestmanagement.wrappers;

import org.hibernate.validator.constraints.Length;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;

import java.util.List;


public class PlatformTeamBean {
    private PlatformTeamWrapper team;
    private List<UserWrapper> members;

    private Long teamId;
    @Length(max = 35, message = "The team name is limited to 35 characters.")
    private String teamName;

    public PlatformTeamBean() { }

    public PlatformTeamBean(PlatformTeamWrapper team, List<UserWrapper> members) {
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

    public List<UserWrapper> getMembers() {
        return members;
    }
}
