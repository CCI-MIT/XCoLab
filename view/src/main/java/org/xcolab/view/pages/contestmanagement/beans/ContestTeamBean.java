package org.xcolab.view.pages.contestmanagement.beans;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.commons.IdListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ContestTeamBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> userIdsJudges = new ArrayList<>();
    private List<Long> userIdsAdvisors = new ArrayList<>();
    private List<Long> userIdsFellows = new ArrayList<>();
    private List<Long> userIdsContestManagers = new ArrayList<>();
    private List<Long> userIdsIAFellows = new ArrayList<>();
    private ContestWrapper contest;
    private HttpServletRequest request;


    public ContestTeamBean(HttpServletRequest request, ContestWrapper contest)
            throws NumberFormatException {
        this.request = request;
        this.contest = contest;
        parseAndPopulateMember();
    }

    private void parseAndPopulateMember() throws NumberFormatException {
        userIdsJudges = IdListUtil.getIdsFromString(request.getParameter("userIdsJudges"));
        userIdsFellows = IdListUtil.getIdsFromString(request.getParameter("userIdsFellows"));
        userIdsAdvisors = IdListUtil.getIdsFromString(request.getParameter("userIdsAdvisors"));
        userIdsIAFellows = IdListUtil.getIdsFromString(request.getParameter("userIdsIAFellows"));
        //userIdsContestManagers = IdListUtil.getIdsFromString(request.getParameter
        // ("userIdsManagers"));
    }

    public ContestTeamBean(ContestWrapper contest) {
        if (contest != null) {
            this.contest = contest;
            populateJudges();
            populateFellows();
            populateAdvisors();
            poupulateAIF();
        }
    }

    private void populateJudges() {
        userIdsJudges.addAll(StaticContestContext.getContestTeamMemberClient()
                        .getJudgesForContest(contest.getId()));
    }

    private void poupulateAIF() {
        userIdsIAFellows.addAll(StaticContestContext.getContestTeamMemberClient()
                        .getIAFellowsForContest(contest.getId()));

    }

    private void populateFellows() {
        userIdsFellows.addAll(StaticContestContext.getContestTeamMemberClient()
                .getFellowsForContest(contest.getId()));
    }

    private void populateAdvisors() {
        userIdsAdvisors.addAll(StaticContestContext.getContestTeamMemberClient()
                        .getAdvisorsForContest(contest.getId()));
    }

    private void populateContestManagers() {
        userIdsContestManagers.addAll(StaticContestContext.getContestTeamMemberClient()
                .getContestManagersForContest(contest.getId()));
    }

    public List<Long> getUserIdsJudges() {
        return userIdsJudges;
    }

    public void setUserIdsJudges(ArrayList<Long> userIdsJudges) {
        this.userIdsJudges = userIdsJudges;
    }

    public List<Long> getUserIdsAdvisors() {
        return userIdsAdvisors;
    }

    public void setUserIdsAdvisors(ArrayList<Long> userIdsAdvisors) {
        this.userIdsAdvisors = userIdsAdvisors;
    }

    public List<Long> getUserIdsFellows() {
        return userIdsFellows;
    }

    public void setUserIdsFellows(ArrayList<Long> userIdsFellows) {
        this.userIdsFellows = userIdsFellows;
    }

    public Long getContestId() {
        return contest.getId();
    }

    public List<Long> getUserIdsContestManagers() {
        return userIdsContestManagers;
    }

    public void setUserIdsContestManagers(List<Long> userIdsContestManagers) {
        this.userIdsContestManagers = userIdsContestManagers;
    }

    public List<Long> getUserIdsIAFellows() {
        return userIdsIAFellows;
    }
}
