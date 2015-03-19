package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.contestmanagement.utils.RequestParameterParser;

import javax.portlet.PortletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Thomas on 2/10/2015.
 */
public class ContestTeamBean implements Serializable{
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

        if(contest != null) {
            try {
                this.contest = contest;
                populateJudges();
                populateFellows();
                populateAdvisors();
                //populateContestManagers();
            } catch (Exception e){

            }
        }
    }

    private void parseAndPopulateMember() throws NumberFormatException{
        RequestParameterParser requestParameterParser = new RequestParameterParser();
        userIdsJudges = requestParameterParser.parseStringParameterFromRequestToLongList(request, "userIdsJudges");
        userIdsFellows = requestParameterParser.parseStringParameterFromRequestToLongList(request, "userIdsFellows");
        userIdsAdvisors = requestParameterParser.parseStringParameterFromRequestToLongList(request, "userIdsAdvisors");
        //userIdsContestManagers = requestParameterParser.parseStringParameterFromRequestToLongList(request, "userIdsManagers");
    }

    private void populateJudges() throws SystemException, PortalException{
        for (User judge : ContestLocalServiceUtil.getJudgesForContest(contest)) {
            userIdsJudges.add(judge.getUserId());
        }
    }

    private void populateFellows() throws SystemException, PortalException{
        for (User fellow : ContestLocalServiceUtil.getFellowsForContest(contest)) {
            userIdsFellows.add(fellow.getUserId());
        }
    }

    private void populateAdvisors() throws SystemException, PortalException{
        for (User advisor : ContestLocalServiceUtil.getAdvisorsForContest(contest)) {
            userIdsAdvisors.add(advisor.getUserId());
        }
    }

    private void populateContestManagers() throws SystemException, PortalException{
        for (User contestManager : ContestLocalServiceUtil.getContestManagersForContest(contest)) {
            userIdsContestManagers.add(contestManager.getUserId());
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
