package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorResourceController extends BaseContentEditor{

    @GetMapping("/content-editor/resourcePagesEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletRequest response, Model model) {
        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {
            return "contenteditor/resourcePagesEditor";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
    }

    @GetMapping("/content-editor/resourcePagesListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node)
            throws IOException {

        List<Contest> allContests = ContestClientUtil.getAllContests();
        JSONArray responseArray = new JSONArray();
        Map<String, List<String>> yearFolders = new LinkedHashMap<>();
        Long folderId = 1L;

        for(Contest c: allContests){
            if(yearFolders.get(c.getContestYear())==null){
                yearFolders.put(c.getContestYear().toString(),new ArrayList<>());
            }
            List<String> yearFolders.get(c.getContestYear())
        }
        if (node != null && !node.isEmpty()) {
            folderId = Long.parseLong(node);
        }
        List<ContentFolder> contentFolders = ContentsClient.getContentFolders(folderId);

        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                responseArray.put(folderNode(cf.getContentFolderName(), cf.getContentFolderId().toString()));
            }
        }
        List<ContentArticleVersion> contentArticles = ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                responseArray.put(articleNode(ca.getTitle(), ca.getContentArticleId()));
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());

    }
}
