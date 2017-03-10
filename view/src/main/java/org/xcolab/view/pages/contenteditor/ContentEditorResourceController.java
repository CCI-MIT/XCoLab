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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorResourceController extends BaseContentEditor {

    @GetMapping("/content-editor/resourcePagesEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletRequest response,
            Model model) {
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


        JSONArray responseArray = new JSONArray();
        Map<String, String> yearFolders = new LinkedHashMap<>();


        if (node == null || node.isEmpty()) {//root
            List<Contest> allContests = ContestClientUtil.getAllContests();
            for (Contest c : allContests) {
                yearFolders.put(c.getContestYear().toString(), "");

            }
            List<String> yearsList = new ArrayList<>(yearFolders.keySet());
            Collections.sort(yearsList);


            for (int i = yearsList.size() - 1; i >= 0; i--) {
                String year = yearsList.get(i);
                responseArray.put(folderNode(year, year));
            }
        } else {//year
            Integer year = 2016;
            year = Integer.parseInt(node);//should be the year
            List<Contest> contestsInYear = ContestClientUtil.getAllContestsInYear(year);
            for (Contest c : contestsInYear) {
                if (c.getResourceArticleId() != null && c.getResourceArticleId() != 0l) {
                    responseArray
                            .put(articleNode(c.getContestShortName(),
                                    c.getResourceArticleId()));
                }
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());

    }


}
