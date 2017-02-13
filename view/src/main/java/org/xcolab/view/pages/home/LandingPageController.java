package org.xcolab.view.pages.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingPageController {

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("homeSpotContentArticleId", ConfigurationAttributeKey.LANDING_PAGE_BANNER_CONTENT_ARTICLE_ID.get());
        model.addAttribute("homeBottomContentArticleId", ConfigurationAttributeKey.LANDING_PAGE_BOTTOM_CONTENT_ARTICLE_ID.get());
        return "home/home";
    }
}
