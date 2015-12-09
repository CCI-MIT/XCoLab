package org.xcolab.portlets.discussions.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.discussions.DiscussionPreferences;

import javax.portlet.ActionRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import java.io.IOException;

/**
 * Created by johannes on 12/1/15.
 */
@Controller
@RequestMapping("edit")
public class DiscussionPreferencesController {

    @RequestMapping
    public String showPreferences(RenderRequest request, RenderResponse response, Model model) {

        model.addAttribute("preferences", new DiscussionPreferences(request));

        return "editPreferences";
    }

    @RequestMapping(params = "action=save")
    public void savePreferences(ActionRequest request, ActionRequest response, Model model, DiscussionPreferences preferences)
            throws ReadOnlyException, ValidatorException, IOException, SystemException, PortalException {
        preferences.submit(request);
    }
}
