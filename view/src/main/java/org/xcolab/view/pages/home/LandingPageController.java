package org.xcolab.view.pages.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;

@Controller
public class LandingPageController {

    @RequestMapping(value = "/")
    public String hello(ServletRequest request) {
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login(ServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin(ServletRequest request, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin";
    }
}
