package org.xcolab.portlets.ontologyportlet.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.members.PermissionsClient;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class FocusAreaEditorController {

    @ModelAttribute("allFocusAreas")
    public List<FocusArea> getAllFocusAreas(){
        return OntologyClientUtil.getAllFocusAreas();
    }

    @ModelAttribute("ontologySpaces")
    public List<OntologySpace> getOntologySpaces(){
        return OntologyClientUtil.getAllOntologySpaces();
    }

    @RequestMapping(params = "focusAreaEditor=true")
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (PermissionsClient.canAdminAll(themeDisplay.getUserId())) {
            return "focusAreaEditor";
        } else {
            return "notAllowed";
        }
    }
}
