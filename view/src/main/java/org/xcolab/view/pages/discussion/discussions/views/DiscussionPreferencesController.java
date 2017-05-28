package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.view.pages.discussion.discussions.DiscussionPreferences;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DiscussionPreferencesController {

    @GetMapping("/discussion/editPreferences")
    public String showPreferences(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("preferences", new DiscussionPreferences());

        return "/discussion/editPreferences";
    }

    @PostMapping("/discussion/savePreferences")
    public void savePreferences(HttpServletRequest request, HttpServletResponse response, Model model, DiscussionPreferences preferences)
            throws  IOException {
        preferences.submit();
    }
}
