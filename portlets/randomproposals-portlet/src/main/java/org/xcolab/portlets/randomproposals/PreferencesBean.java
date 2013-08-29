package org.xcolab.portlets.randomproposals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PreferencesBean {
    private Long[] selectedPhases;
    private String SELECTED_PHASES_PREFERENCE = "SELECTED_PHASES";
    
    public PreferencesBean() {
        PortletPreferences prefs = Helper.getPortletPrefs();
        selectedPhases = convertStringsToLongs(prefs.getValue(SELECTED_PHASES_PREFERENCE, "").split(","));
        
    }
    
    public String submit() throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        PortletPreferences prefs = Helper.getPortletPrefs();
        FacesMessage fm = new FacesMessage();
        
        prefs.setValue(SELECTED_PHASES_PREFERENCE, StringUtils.join(convertLongsToStrings(selectedPhases), ","));

        prefs.store();
        
        fm.setSummary("Settings saved successfully");
        
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        return "";
    }
    
    public List<SelectItem> getContestPhases() throws SystemException, PortalException {
        List<SelectItem> ret = new ArrayList<>();
        
        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        
        Collections.sort(contests, new Comparator<Contest>() {

            @Override
            public int compare(Contest o1, Contest o2) {
                return (int) (o1.getContestPK() - o2.getContestPK());
            }
            
        });
        
        for (Contest c: contests) {
            for (ContestPhase cp: ContestLocalServiceUtil.getPhases(c)) {
                ret.add(new SelectItem(cp.getContestPhasePK(), 
                        c.getContestPK() + " " + c.getContestShortName() + " - " + 
                        cp.getContestPhasePK() + " " + ContestPhaseLocalServiceUtil.getContestStatusStr(cp)));
            }
        }
        return ret;
    }

    public Long[] getSelectedPhases() {
        return selectedPhases;
    }

    public void setSelectedPhases(Long[] selectedPhases) {
        this.selectedPhases = selectedPhases;
    }
    
    private static Long[] convertStringsToLongs(String[] arrayStr) {
        if (arrayStr.length == 1 && StringUtils.isBlank(arrayStr[0])) { 
            return new Long[] {};
        }
        
        Long[] arrayLong = new Long[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            try {
                arrayLong[i] = Long.parseLong(arrayStr[i]);
            }
            catch (NumberFormatException e) {
                arrayLong[i] = null;
            }
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
}
