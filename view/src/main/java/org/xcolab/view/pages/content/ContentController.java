package org.xcolab.view.pages.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentPage;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentController {

    @GetMapping("/content/{pageTitle}")
    public String contentDisplay(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @PathVariable String pageTitle) throws IOException {

        try {
            final ContentPage contentPage = ContentsClient.getContentPage(pageTitle);
            final ContentArticle contentArticle = ContentsClient
                    .getContentArticle(contentPage.getContentArticleId());

            if (!contentArticle.canView(member)) {
                return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
            }

            model.addAttribute("contentArticleId", contentPage.getContentArticleId());

            if (contentPage.getMenuArticleId() != null) {
                model.addAttribute("menuArticleId", contentPage.getMenuArticleId());
            }
        } catch (ContentNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
        return "content/contentPage";
    }

    @GetMapping("/contentwidget")
    public String contentDisplay(HttpServletRequest request, Model model, @RequestParam Long contentArticleId) {
        model.addAttribute("articleId", contentArticleId);
        return "content/widget";
    }
}
