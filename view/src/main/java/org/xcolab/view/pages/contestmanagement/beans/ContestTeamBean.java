package org.xcolab.view.pages.contestmanagement.beans;

import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.IdListUtil;

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
    private Contest contest;
    private HttpServletRequest request;


    public ContestTeamBean(HttpServletRequest request, Contest contest)
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

    public ContestTeamBean(Contest contest) {
        if (contest != null) {
            this.contest = contest;
            populateJudges();
            populateFellows();
            populateAdvisors();
            poupulateAIF();
        }
    }

    private void populateJudges() {
        for (Long judge : ContestTeamMemberClientUtil.getJudgesForContest(contest.getContestPK())) {
            userIdsJudges.add(judge);
        }
    }

    private void poupulateAIF() {
        for (Long fellow : ContestTeamMemberClientUtil
                .getIAFellowsForContest(contest.getContestPK())) {
            userIdsIAFellows.add(fellow);
        }

    }

    private void populateFellows() {
        for (Long fellow : ContestTeamMemberClientUtil
                .getFellowsForContest(contest.getContestPK())) {
            userIdsFellows.add(fellow);
        }
    }

    private void populateAdvisors() {
        for (Long advisor : ContestTeamMemberClientUtil
                .getAdvisorsForContest(contest.getContestPK())) {
            userIdsAdvisors.add(advisor);
        }
    }

    private void populateContestManagers() {

        for (Long contestManager : ContestTeamMemberClientUtil
                .getContestManagersForContest(contest.getContestPK())) {
            userIdsContestManagers.add(contestManager);
        }

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
        return contest.getContestPK();
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
