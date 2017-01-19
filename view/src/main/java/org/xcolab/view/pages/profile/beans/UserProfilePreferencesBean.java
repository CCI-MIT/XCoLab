package org.xcolab.view.pages.profile.beans;

import org.springframework.stereotype.Component;
import sun.security.validator.ValidatorException;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;


@Component
public class UserProfilePreferencesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserProfilePreferencesBean() { }

    public UserProfilePreferencesBean(HttpServletRequest request) {
        //PortletPreferences prefs = request.getPreferences();
        //I kept this in case any other preferences other than the AccountDetailsEmmaAPI need to be changed
    }

    public void store(HttpServletRequest request) throws ValidatorException, IOException {
        //prefs.store();
        //I kept this in case any other preferences other than the AccountDetailsEmmaAPI need to be changed
    }
}
