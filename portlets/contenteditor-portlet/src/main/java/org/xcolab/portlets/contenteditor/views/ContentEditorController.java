package org.xcolab.portlets.contenteditor.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.client.contents.ContentsClient;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class ContentEditorController {
/*
    @RequestMapping
    public String contentEditor() {
        return "editor";
    }
*/

    @RequestMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        return "editor"; }



    @ResourceMapping("contentEditorListFolder")
    public void contentEditorListFolder(ResourceRequest request, ResourceResponse response,
                                 @RequestParam(required = false) String node)
            throws IOException , SystemException, PortalException {

        JSONArray responseArray = JSONFactoryUtil.createJSONArray();

        ContentsClient.getContentArticle()
        responseArray.put(folderNode("WebContent", "1"));
        responseArray.put(folderNode("Wiki", "2"));
        responseArray.put(folderNode("Blog", "3"));

        response.getPortletOutputStream().write(responseArray.toString().getBytes());

    }
    private JSONObject folderNode(String label, String id){
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
        folderNode.put("label", label);
        folderNode.put("id" ,"id");
        folderNode.put("load_on_demand" ,"true");
        return folderNode;
    }
}
