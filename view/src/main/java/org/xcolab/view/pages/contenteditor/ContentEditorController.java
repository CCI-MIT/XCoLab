package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.client.contents.pojo.ContentPage;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.i18n.I18nUtils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorController extends BaseContentEditor{


    @GetMapping("/content-editor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletRequest response, Model model) {
        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {
            if(ConfigurationAttributeKey.IS_I18N_ACTIVE.get()) {
                model.addAttribute("i18nOptions", I18nUtils.getSelectList());
            }
            model.addAttribute("i18nActive", ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
            return "contenteditor/editor";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
    }

    @GetMapping("/content-editor/contentEditorListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false) String node)
            throws IOException {

        JSONArray responseArray = new JSONArray();
        Long folderId = 1L;
        if (node != null && !node.isEmpty()) {
            folderId = Long.parseLong(node);
        }
        List<ContentFolder> contentFolders = ContentsClient.getContentFolders(folderId);

        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                if(cf.getContentFolderId() != ContentFolder.RESOURCE_FOLDER_ID) {
                    responseArray.put(folderNode(cf.getContentFolderName(),
                            cf.getContentFolderId().toString()));
                }
            }
        }
        List<ContentArticleVersion> contentArticles = ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                ContentArticle contentArticle = ContentsClient.getContentArticle(ca.getContentArticleId());
                if(contentArticle.getVisible()) {
                    responseArray.put(articleNode(ca.getTitle(), ca.getContentArticleId()));
                }
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());

    }


    @GetMapping("/content-editor/contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(required = false) Long articleId,
                                                        @RequestParam(required = false) String encoding)
            throws IOException, ContentNotFoundException {
        String defaultEncoding =I18nUtils.DEFAULT_LOCALE.getLanguage();
        if(encoding==null||encoding.isEmpty()){
            encoding = defaultEncoding;
        }
        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestVersionByArticleIdAndLanguage(articleId,encoding);
        if(contentArticleVersion == null){
            //if there is no content for the encoding passed, get the default from the database
            contentArticleVersion = ContentsClient.getLatestVersionByArticleIdAndLanguage(articleId,defaultEncoding);
            contentArticleVersion.setContentArticleVersionId(0L);
            contentArticleVersion.setLang(encoding);
        }

        JSONObject articleVersion = getContentArticleVersion(articleId, contentArticleVersion);

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    @GetMapping("/content-editor/contentEditorGetArticleVersion")
    public void contentEditorGetArticleVersion(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long articleVersionId)
            throws IOException, ContentNotFoundException {

        ContentArticleVersion contentArticleVersion = ContentsClient.getContentArticleVersion(articleVersionId);

        JSONObject articleVersion = getContentArticleVersion(contentArticleVersion.getContentArticleId(), contentArticleVersion);

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject getContentArticleVersion(@RequestParam(required = false) Long articleId,
            ContentArticleVersion contentArticleVersion) {

        JSONArray versions = new JSONArray();
        List<ContentArticleVersion> cavs = ContentsClient
                .getContentArticleVersions(0,Integer.MAX_VALUE,null,articleId,null,null, contentArticleVersion.getLang());

        JSONObject articleVersion;
        for(ContentArticleVersion cav: cavs){
            articleVersion = new JSONObject();
            articleVersion.put("createdDate",cav.getCreateDate());
            articleVersion.put("contentArticleVersionId", cav.getContentArticleVersionId());
            versions.put(articleVersion);
        }
        articleVersion = new JSONObject();
        ContentPage cp = ContentsClient.getContentPageByContentArticleId(contentArticleVersion.getContentArticleId());

        if (cp!=null) {
            articleVersion.put("contentUrl",cp.getTitle());
        }
        if (contentArticleVersion != null) {
            articleVersion.put("title", contentArticleVersion.getTitle());
            articleVersion.put("folderId", contentArticleVersion.getFolderId());
            articleVersion.put("articleId", contentArticleVersion.getContentArticleId());
            articleVersion.put("content", contentArticleVersion.getContent());
            articleVersion.put("lang", contentArticleVersion.getLang());
            articleVersion.put("createdDate",contentArticleVersion.getCreateDate());
            articleVersion.put("versions",versions);


        }
        return articleVersion;
    }

    @PostMapping("/content-editor/archiveContentArticle")
    public void archiveContentArticle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long articleId) throws IOException{
        ContentArticle ca = ContentsClient.getContentArticle(articleId);
        ca.setVisible(false);
        ContentsClient.updateContentArticle(ca);
        defaultOperationReturnMessage(true, "Article archived successfully","", response);
    }

    @PostMapping("/content-editor/createArticleFolder")
    public void createArticleFolder(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) String folderName,
                                   @RequestParam(required = false) Long parentFolderId) throws IOException{
        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setContentFolderName(folderName);
        contentFolder.setParentFolderId(parentFolderId);

        ContentsClient.createContentFolder(contentFolder);

        defaultOperationReturnMessage(true, "Folder created successfully","", response);
    }

    @PostMapping("/content-editor/moveArticleVersion")
    public void moveArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) Long articleId,
                                   @RequestParam(required = false) Long folderId)
            throws IOException, ContentNotFoundException {
        long userId = MemberAuthUtil.getMemberId(request);

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersion(articleId);
        ContentArticleVersion newContentArticleVersion = new ContentArticleVersion();
        newContentArticleVersion.setTitle(contentArticleVersion.getTitle());
        newContentArticleVersion.setContent(contentArticleVersion.getContent());
        newContentArticleVersion.setContentArticleId(contentArticleVersion.getContentArticleId());

        newContentArticleVersion.setAuthorId(userId);
        newContentArticleVersion.setFolderId(folderId);
        ContentsClient.createContentArticleVersion(newContentArticleVersion);

        defaultOperationReturnMessage(true, "Article moved successfully","", response);
    }




    @PostMapping("/content-editor/previewArticle")
    public String previewArticle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String content, Model model
    ) throws IOException {
        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {

            model.addAttribute("content", content);
            return "contenteditor/previewArticleContent";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

    }
    @PostMapping("/content-editor/saveContentArticleVersion")
    public void saveContentArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(required = false) Long articleId,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Long folderId,
                                          @RequestParam(required = false) String content,
                                          @RequestParam(required = false) String lang
    ) throws IOException {
        long userId = MemberAuthUtil.getMemberId(request);

        if(lang == null) {
            lang = I18nUtils.DEFAULT_LOCALE.getLanguage();
        }
        ContentArticleVersion contentArticleVersion = new ContentArticleVersion();

        contentArticleVersion.setContentArticleId(articleId);
        contentArticleVersion.setLang(lang);

        contentArticleVersion.setAuthorId(userId);
        contentArticleVersion.setFolderId((folderId));
        contentArticleVersion.setTitle(title);
        contentArticleVersion.setContent(content);
        contentArticleVersion =ContentsClient.createContentArticleVersion(contentArticleVersion);


        defaultOperationReturnMessage(true, "Article version created successfully", contentArticleVersion.getContentArticleId().toString(), response);
    }


}
