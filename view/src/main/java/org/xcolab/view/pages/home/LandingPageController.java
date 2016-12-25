package org.xcolab.view.pages.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.RealMember;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LandingPageController {

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request, Member member, @RealMember Member realMember) {
        return "home";
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
