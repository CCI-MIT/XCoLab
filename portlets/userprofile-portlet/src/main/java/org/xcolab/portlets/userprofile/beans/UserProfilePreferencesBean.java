package org.xcolab.portlets.userprofile.beans;

import org.springframework.stereotype.Component;

import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.io.Serializable;

@Component
public class UserProfilePreferencesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserProfilePreferencesBean() { }

    public UserProfilePreferencesBean(PortletRequest request) {
        //PortletPreferences prefs = request.getPreferences();
        //I kept this in case any other preferences other than the AccountDetailsEmmaAPI need to be changed
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        //prefs.store();
        //I kept this in case any other preferences other than the AccountDetailsEmmaAPI need to be changed
    }
}
