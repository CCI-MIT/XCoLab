package org.xcolab.view.pages.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingPageController {

    private static final long HOME_SPOT_CONTENT_ARTICLE_ID = 1548L;

    @RequestMapping(value = "/")
    public String hello(HttpServletRequest request, HttpServletResponse response, Model model) {
        final long contentArticleId = HOME_SPOT_CONTENT_ARTICLE_ID;
        if (contentArticleId > 0) {

            model.addAttribute("homeSpotContentArticleVersion", getContentArticleVersion(HOME_SPOT_CONTENT_ARTICLE_ID));
        }
        return "home/home";
    }

    private ContentArticleVersion getContentArticleVersion(Long contentArticleId){
        final ContentArticle contentArticle = ContentsClient
                .getContentArticle(contentArticleId);
        final long version = contentArticle.getMaxVersionId();
        final ContentArticleVersion contentArticleVersion = ContentsClient
                .getContentArticleVersion(version);
        return contentArticleVersion;
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
