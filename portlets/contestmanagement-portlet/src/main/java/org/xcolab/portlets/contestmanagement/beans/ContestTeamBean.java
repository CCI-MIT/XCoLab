package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestWrapper;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.portlet.PortletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContestTeamBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<Long> userIdsJudges = new ArrayList<>();
    private List<Long> userIdsAdvisors = new ArrayList<>();
    private List<Long> userIdsFellows = new ArrayList<>();
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
            } catch (Exception e){

            }
        }
    }

    private void parseAndPopulateMember() throws NumberFormatException{
        userIdsJudges = parseStringParameterToLongList("userIdsJudges");
        userIdsFellows = parseStringParameterToLongList("userIdsFellows");
        userIdsAdvisors = parseStringParameterToLongList("userIdsAdvisors");
    }

    private List<Long> parseStringParameterToLongList(String paramterName) throws NumberFormatException{
        List<String>  stringList = Arrays.asList(request.getParameter(paramterName).split("\\s*,\\s*"));
        return parseStringListToLongList(stringList);
    }

    private static List<Long> parseStringListToLongList(List<String> stringList) throws NumberFormatException{
        List<Long> longList = new ArrayList<>();
        for (String string : stringList) {
            longList.add(Long.parseLong(string));
        }
        return longList;
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
}
