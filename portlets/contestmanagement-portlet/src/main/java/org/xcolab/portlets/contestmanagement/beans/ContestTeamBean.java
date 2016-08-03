package org.xcolab.portlets.contestmanagement.beans;


import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.utils.IdListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

public class ContestTeamBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> userIdsJudges = new ArrayList<>();
    private List<Long> userIdsAdvisors = new ArrayList<>();
    private List<Long> userIdsFellows = new ArrayList<>();
    private List<Long> userIdsContestManagers = new ArrayList<>();
    private Contest contest;
    private PortletRequest request;


    public ContestTeamBean(PortletRequest request, Contest contest)
            throws NumberFormatException {
        this.request = request;
        this.contest = contest;
        parseAndPopulateMember();
    }

    public ContestTeamBean(Contest contest) {
        if (contest != null) {
            this.contest = contest;
            populateJudges();
            populateFellows();
            populateAdvisors();
        }
    }

    private void parseAndPopulateMember() throws NumberFormatException {
        userIdsJudges = IdListUtil.getIdsFromString(request.getParameter("userIdsJudges"));
        userIdsFellows = IdListUtil.getIdsFromString(request.getParameter("userIdsFellows"));
        userIdsAdvisors = IdListUtil.getIdsFromString(request.getParameter("userIdsAdvisors"));
        //userIdsContestManagers = IdListUtil.getIdsFromString(request.getParameter("userIdsManagers"));
    }

    private void populateJudges() {
        try {
            for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
                userIdsJudges.add(judge.getUserId());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalStateException("Error while populating judges", e);
        }
    }

    private void populateFellows() {
        try {
            for (User fellow : ContestLocalServiceUtil.getFellowsForContest(contest)) {
                userIdsFellows.add(fellow.getUserId());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalStateException("Error while populating fellows", e);
        }
    }

    private void populateAdvisors() {
        try {
            for (User advisor : ContestLocalServiceUtil.getAdvisorsForContest(contest)) {
                userIdsAdvisors.add(advisor.getUserId());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalStateException("Error while populating advisors", e);
        }
    }

    private void populateContestManagers() {
        try {
            for (User contestManager : ContestLocalServiceUtil
                    .getContestManagersForContest(contest)) {
                userIdsContestManagers.add(contestManager.getUserId());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalStateException("Error while populating managers", e);
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
}
