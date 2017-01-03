package org.xcolab.view.pages.contentdisplay;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;


import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;



import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentDisplayController {

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
        return "contentdisplay/contentDisplay";
    }
}
