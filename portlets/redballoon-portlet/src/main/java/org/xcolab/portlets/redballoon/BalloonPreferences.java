package org.xcolab.portlets.redballoon;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

@ViewScoped
@ManagedBean(name="preferences")
public class BalloonPreferences {

    private String firstPage;
    private String secondPage;

    private final static String FIRST_PAGE_PREF = "FIRST_PAGE";
    private final static String SECOND_PAGE_PREF = "SECOND_PAGE";

    public BalloonPreferences() {
        PortletPreferences prefs = Helper.getPortletPrefs();
        firstPage = prefs.getValue(FIRST_PAGE_PREF, "");
        secondPage = prefs.getValue(SECOND_PAGE_PREF, "");        
        
    }

    
    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getSecondPage() {
        return secondPage;
    }

    public void setSecondPage(String secondPage) {
        this.secondPage = secondPage;
    }

    public String submit() throws ReadOnlyException, ValidatorException, IOException {
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValue(FIRST_PAGE_PREF, firstPage);
        prefs.setValue(SECOND_PAGE_PREF, secondPage);

        prefs.store();
        
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        return null;
    }
    

}
