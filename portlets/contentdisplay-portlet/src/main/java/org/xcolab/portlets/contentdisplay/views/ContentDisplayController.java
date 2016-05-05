package org.xcolab.portlets.contentdisplay.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.portlets.contentdisplay.util.ContentDisplayPreferences;

import javax.portlet.RenderRequest;

@Controller
@RequestMapping("view")
public class ContentDisplayController {

    @RenderMapping
    public String contentDisplay(RenderRequest request, Model model) {
        final ContentDisplayPreferences preferences = new ContentDisplayPreferences(request);
        final String contentArticleIdString = preferences.getContentArticleId();
        if (StringUtils.isNotBlank(contentArticleIdString)) {
            final long contentArticleId = Long.parseLong(contentArticleIdString);
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
        }
        return "content";
    }
}
