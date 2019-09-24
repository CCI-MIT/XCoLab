package org.xcolab.view.pages.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.widgets.AbstractWidgetController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(ContactPreferencesController.BASE_URL)
public class ContactPreferencesController extends AbstractWidgetController<ContactPreferences> {

    public static final String BASE_URL = "/feedback";

    protected ContactPreferencesController() {
        super("/feedback", ContactPreferences::new);
    }

    @GetMapping(PREFERENCES_URL_PATH)
    public String showFeed(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member, @RequestParam(required = false) String language) {
        return showPreferencesInternal(response, model, member, "default", language,
                "/feedback/editPreferences");
    }

    @PostMapping(PREFERENCES_URL_PATH)
    public String savePreferences(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @RequestParam(required = false) String language,
            ContactPreferences preferences) {
        return savePreferencesInternal(request, response, member, preferences);
    }
}
