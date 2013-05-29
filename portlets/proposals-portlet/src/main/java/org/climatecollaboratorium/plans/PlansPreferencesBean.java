package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.climatecollaboratorium.plans.wrappers.ContestPreferenceWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlansPreferencesBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long[] questionsArray = new Long[0];
    private final static String DEFAULT_DESCRIPTION_PREFERENCE = "DEFAULT_DESCRIPTION";
    private final static String CONTEST_MAX_PROPOSALS_PREFERENCE_PREFIX = "CONTEST_MAX_PROPOSALS";
    private final static int DEFAULT_MAX_PROPOSALS = 10;
    private String defaultDescription;
    private List<ContestPreferenceWrapper> contestPreferences;
    private Map<Long, Integer> maxProposalsPerContest = new HashMap<Long, Integer>();

    public PlansPreferencesBean() throws SystemException {
        PortletPreferences prefs = Helper.getPortletPrefs();
        defaultDescription = prefs.getValue(DEFAULT_DESCRIPTION_PREFERENCE, "");
        contestPreferences = new ArrayList<ContestPreferenceWrapper>();
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            int maxProposals = DEFAULT_MAX_PROPOSALS;
            String maxProposalsStr = prefs.getValue(CONTEST_MAX_PROPOSALS_PREFERENCE_PREFIX + contest.getContestPK(),
                    String.valueOf(DEFAULT_MAX_PROPOSALS));

            try {
                maxProposals = Integer.parseInt(maxProposalsStr);
            } catch (NumberFormatException e) {
                // ignore
            }
            contestPreferences.add(new ContestPreferenceWrapper(contest, maxProposals));
            maxProposalsPerContest.put(contest.getContestPK(), maxProposals);
        }
    }

    public String submit() throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        PortletPreferences prefs = Helper.getPortletPrefs();
        FacesMessage fm = new FacesMessage();

        for (ContestPreferenceWrapper contestPref: contestPreferences) {
            prefs.setValue(CONTEST_MAX_PROPOSALS_PREFERENCE_PREFIX + contestPref.getContest().getContestPK(), String.valueOf(contestPref.getMaxProposalsCount()));
        }
        prefs.store();
        
        fm.setSummary("Settings saved successfully");
        
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        return "";
    }

    public void setQuestions(Long[] questions) {
        questionsArray = questions;
    }

    public Long[] getQuestions() {
        return questionsArray;
    }

    public List<ContestPreferenceWrapper> getContestPreferences() throws SystemException {
        return contestPreferences;
    }


    private static Long[] convertStringsToLongs(String[] arrayStr) {
        Long[] arrayLong = new Long[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            arrayLong[i] = Long.parseLong(arrayStr[i]);
        }
        return arrayLong;
    }

    private static String[] convertLongsToStrings(Long[] arrayLong) {
        String[] arrayStr = new String[arrayLong.length];
        for (int i = 0; i < arrayLong.length; i++) {
            arrayStr[i] = arrayLong[i].toString();
        }
        return arrayStr;
    }

    public void setDefaultDescription(String defaultDescription) throws ReadOnlyException, ValidatorException,
            IOException {
        this.defaultDescription = defaultDescription;

        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValue(DEFAULT_DESCRIPTION_PREFERENCE, defaultDescription);
        prefs.store();

        FacesMessage fm = new FacesMessage();

        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
    }

    public String getDefaultDescription() {
        return defaultDescription;
    }
    
    public Map<Long, Integer> getMaxProposalsPerContest() {
        return maxProposalsPerContest;
    }

}