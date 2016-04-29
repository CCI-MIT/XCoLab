package org.xcolab.portlets.wiki.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.portlets.wiki.util.WikiPreferences;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class WikiController {

    @RenderMapping
    public String home() {
        return "wiki";
    }

    @RequestMapping(params = "show=wiki")
    public String showWikiPage(PortletRequest request, PortletResponse response, Model model,
            @RequestParam String pageTitle) throws ContentNotFoundException {
        final WikiPreferences preferences = new WikiPreferences(request);
        final long folderId = Long.parseLong(preferences.getWikiFolderId());
        if (folderId > 0 && StringUtils.isNotBlank(pageTitle)) {
            final ContentArticleVersion contentArticleVersion =
                    ContentsClient.getContentArticleVersion(folderId, pageTitle);
            model.addAttribute("contentArticleVersion", contentArticleVersion);
        }
        return "wiki";
    }

    @RequestMapping(params = "show=resource")
    public String showResourcePage(PortletRequest request, PortletResponse response, Model model,
            @RequestParam String contestUrlName, @RequestParam(required = false) Long contestYear) {

        return "wiki";
    }
}
