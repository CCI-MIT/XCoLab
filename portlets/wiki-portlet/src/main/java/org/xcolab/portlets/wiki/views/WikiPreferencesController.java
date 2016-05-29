package org.xcolab.portlets.wiki.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.portlets.wiki.util.WikiPreferences;

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
public class WikiPreferencesController {

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model)
            throws SystemException, PortalException {
        model.addAttribute("preferences", new WikiPreferences(request));
        final List<ContentFolder> folders = ContentsClient.getContentFolders();
        model.addAttribute("folders", folders);
        return "preferences";
    }

    @RequestMapping(params = "action=save")
    public void savePreferences(ActionRequest request, ActionResponse response, Model model,
            WikiPreferences preferences)
            throws ReadOnlyException, ValidatorException, IOException, PortalException {
        //save terms
        preferences.store(request);
    }
}
