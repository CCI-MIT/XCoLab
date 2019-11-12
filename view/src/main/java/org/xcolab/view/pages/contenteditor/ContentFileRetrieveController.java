package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ContentFileRetrieveController extends BaseContentEditor{

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IPermissionClient permissionClient;

    @Autowired
    private IFileClient fileClient;

    @GetMapping("/content-editor/fileUploaderListFiles")
    public List<IFileEntry> contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {
        try {
            List<IFileEntry> a = fileClient.getNonImageFilesEntry();
            return a;
            //response.getOutputStream().write(a.toString().getBytes());

        }
        catch(Exception e){
            System.out.println("our exception!!!!!!!!");
            System.out.println(e);
        }
        return new ArrayList<IFileEntry>();
    }
}
