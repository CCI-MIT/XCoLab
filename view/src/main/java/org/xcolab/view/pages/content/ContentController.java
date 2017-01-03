package org.xcolab.view.pages.content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentController {

    @GetMapping("/content/{contentArticleId}")
    public String contentDisplay(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @PathVariable Long contentArticleId) throws IOException {

        if (contentArticleId > 0) {
                try {
                    final ContentArticle contentArticle = ContentsClient
                            .getContentArticle(contentArticleId);

                    if (!contentArticle.canView(member)) {
                        return ErrorText.ACCESS_DENIED.flashAndReturnRedirect(request);
                    }

                    final long version = contentArticle.getMaxVersionId();
                    final ContentArticleVersion contentArticleVersion = ContentsClient
                            .getContentArticleVersion(version);
                    model.addAttribute("contentArticleVersion", contentArticleVersion);
                } catch (ContentNotFoundException e) {
                    return ErrorText.NOT_FOUND.flashAndReturnRedirect(request);
                }
            }
        return "content/contentPage";
    }
}
