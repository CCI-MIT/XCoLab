package org.xcolab.view.pages.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingPageController {

    private static final long HOME_SPOT_CONTENT_ARTICLE_ID = 1548L;
    private static final long HOME_BOTTOM_CONTENT_ARTICLE_ID = 1615L;

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("homeSpotContentArticleId", HOME_SPOT_CONTENT_ARTICLE_ID);
        model.addAttribute("homeBottomContentArticleId", HOME_BOTTOM_CONTENT_ARTICLE_ID);
        return "home/home";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin(HttpServletRequest request, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin";
    }
}
