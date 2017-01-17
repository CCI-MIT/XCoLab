package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.validator.ValidatorException;

import org.xcolab.view.pages.profile.beans.UserProfilePreferencesBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("edit")
public class UserProfilePreferencesController {

    @RequestMapping
    public String showPreferences(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("UserProfilePreferencesBean", new UserProfilePreferencesBean(request));
        return "editUserProfilePreferences";
    }

    @RequestMapping(params = {"action=savePreferences"})
    public void savePreferences(HttpServletRequest request, HttpServletRequest response, Model model,
            UserProfilePreferencesBean preferences)
            throws ValidatorException, IOException {
        preferences.store(request);
    }

}
