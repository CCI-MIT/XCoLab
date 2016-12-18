package org.xcolab.portlets.contentdisplay.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.portlets.contentdisplay.util.ContentDisplayPreferences;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

@Controller
@RequestMapping("edit")
public class ContentDisplayPreferencesController {

    private static final long CONTENT_FOLDER_ID = 2L;

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) {
        model.addAttribute("preferences", new ContentDisplayPreferences(request));

        final List<ContentArticle> contentArticles = ContentsClient
                .getContentArticles(null);
        model.addAttribute("contentArticles", contentArticles);
        return "preferences";
    }

    @RequestMapping(params = "action=save")
    public void savePreferences(ActionRequest request, ActionResponse response, Model model,
            ContentDisplayPreferences preferences)
            throws ReadOnlyException, ValidatorException, IOException {
        //save terms
        preferences.store(request);
    }
}
