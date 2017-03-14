package org.xcolab.view.pages.contenteditor;

import org.json.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class BaseContentEditor {

    protected static final Integer THRESHOLD_TO_AVOID_NODE_COLLISION = 1000;

    protected void defaultOperationReturnMessage(boolean success, String message, String articleVersionId, HttpServletResponse response) throws IOException {
        JSONObject folderNode = new JSONObject();
        folderNode.put("success", success);
        folderNode.put("articleVersionId", articleVersionId);
        folderNode.put("msg", message);
        response.getOutputStream().write(folderNode.toString().getBytes());
    }

    protected JSONObject treeNode(String label, String id, String kind, boolean loadOnDemand) {
        JSONObject folderNode = new JSONObject();
        folderNode.put("label", substringIfNeeded(label));
        folderNode.put("originalLabel", label);
        folderNode.put("id", id);
        folderNode.put("kind", kind);
        if (loadOnDemand) {
            folderNode.put("load_on_demand", loadOnDemand + "");
        }
        return folderNode;
    }

    protected JSONObject articleNode(String label, Long id) {
        return treeNode(label, (THRESHOLD_TO_AVOID_NODE_COLLISION +id) + "", "article", false);
    }

    protected JSONObject folderNode(String label, String id) {
        return treeNode(label, id, "folder", true);
    }

    private static final Integer SIZE_THRESHOLD = 35;

    protected static String substringIfNeeded(String st) {
        if (st.length() > SIZE_THRESHOLD) {
            return st.substring(0, SIZE_THRESHOLD) + "...";
        } else {
            return st;
        }
    }
}
