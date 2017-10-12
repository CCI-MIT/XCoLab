package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.view.pages.contestmanagement.controller.manager.PlatformTeam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean.CREATE_CONTEST_PHASE_PK;


public class PlatformTeamBean {
    private PlatformTeam team;

    private Long teamId;
    private String teamName;
    private List<Member> teamMembers;

    public PlatformTeamBean() { }

    public PlatformTeamBean(PlatformTeam team) {
        this.team = team;
    }

    public Long getTeamId() {
        return team == null ? teamId : team.getId_();
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
        return team == null ? teamMembers : team.getMembers();
    }
}
