package org.xcolab.portlets.ontologyportlet.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class FocusAreaEditorController {

    @ModelAttribute("allFocusAreas")
    public List<FocusArea> getAllFocusAreas() {
        return OntologyClientUtil.getAllFocusAreas();
    }

    @ModelAttribute("ontologySpaces")
    public List<OntologySpace> getOntologySpaces() {
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

    private void defaultOperationReturnMessage(boolean success, String message,
            ResourceResponse response) throws IOException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }


    @ResourceMapping("saveFocusArea")
    public void saveFocusArea(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer order,
            @RequestParam(required = false) String name,
            @RequestParam(value = "ontologySpaces[]")String[] ontologySpaces
    ) throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        FocusArea focusArea;
        if (id != null && id != 0l) {
            focusArea = OntologyClientUtil.getFocusArea(id);
            focusArea.setName(name);
            focusArea.setOrder_(order);
            OntologyClientUtil.updateFocusArea(focusArea);
        } else {
            focusArea = new FocusArea();
            focusArea.setOrder_(order);
            focusArea.setName(name);
            focusArea = OntologyClientUtil.createFocusArea(focusArea);
        }
        updateFocusAreaOntologyTerms(focusArea, ontologySpaces);

        defaultOperationReturnMessage(true, "Ontology term updated successfully", response);
    }

    private void updateFocusAreaOntologyTerms(FocusArea focusArea, String[] ontologyTerms) {

        OntologyClientUtil.deleteFocusAreaOntologyTerm(focusArea.getId_(), null);

        if (ontologyTerms != null) {
            for (String ontId : ontologyTerms) {
                OntologyClientUtil.addOntologyTermsToFocusAreaByOntologyTermId(focusArea.getId_(),getOntologyTermId(ontId));
            }
        }

    }

    private Long getOntologyTermId(String node) {

        Long ontologyTermParentId = null;
        if (node != null && !node.isEmpty()) {
            String[] ids = node.split("_");
            ontologyTermParentId = Long.parseLong(ids[1]);
        }
        return ontologyTermParentId;

    }

    @ResourceMapping("focusAreaEditorGetFocusArea")
    public void getFocusArea(ResourceRequest request,
            ResourceResponse response,
            @RequestParam(required = false) Long focusAreaId)
            throws IOException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();

        FocusArea focusArea = OntologyClientUtil.getFocusArea(focusAreaId);
        if (focusArea != null) {
            articleVersion.put("id", focusArea.getId());
            articleVersion.put("order", focusArea.getOrder_());
            articleVersion.put("name", focusArea.getName());
            List<OntologyTerm> allTerms =
                    OntologyClientUtil.getOntologyTermsForFocusArea(focusArea);
            JSONArray array = JSONFactoryUtil.createJSONArray();
            if (allTerms != null) {
                for (OntologyTerm ot : allTerms) {
                    array.put(ot.getOntologySpaceId() + "_" + ot.getId());
                }
            }

            articleVersion.put("ontologySpaces", array);

        }

        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }
}
