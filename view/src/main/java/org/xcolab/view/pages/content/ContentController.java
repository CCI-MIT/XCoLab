package org.xcolab.view.pages.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
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

            final ContentArticleVersion contentArticleVersion = getLatestArticleVersion(contentArticle);
            model.addAttribute("contentArticleVersion", contentArticleVersion);

            if (contentPage.getMenuArticleId() != null) {
                final ContentArticle menuArticle = ContentsClient.getContentArticle(contentPage.getMenuArticleId());
                final ContentArticleVersion menuArticleVersion = getLatestArticleVersion(menuArticle);
                model.addAttribute("menuArticleVersion", menuArticleVersion);
            }
        } catch (ContentNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
        return "content/contentPage";
    }

    @GetMapping("/contentdisplay")
    public String contentDisplay(HttpServletRequest request, Model model, @RequestParam Long contentArticleId) {

        final Long contentArticleIdString = contentArticleId;

        if (contentArticleId > 0) {
            try {
                final ContentArticle contentArticle = ContentsClient
                        .getContentArticle(contentArticleId);
                final long version = contentArticle.getMaxVersionId();
                final ContentArticleVersion contentArticleVersion = ContentsClient
                        .getContentArticleVersion(version);
                model.addAttribute("contentArticleVersion", contentArticleVersion);
            } catch (ContentNotFoundException e) {
                //TODO: logging
            }
        }
        return "content/contentDisplay";
    }
    private ContentArticleVersion getLatestArticleVersion(ContentArticle article) {
        final long contentVersion = article.getMaxVersionId();
        return ContentsClient.getContentArticleVersion(contentVersion);
    }
}
