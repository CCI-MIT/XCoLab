package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.client.content.pojo.tables.pojos.ContentArticleVersion;
import org.xcolab.client.content.pojo.tables.pojos.ContentFolder;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorController extends BaseContentEditor {

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IPermissionClient permissionClient;

    @GetMapping("/content-editor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper loggedInMember) {
        if (permissionClient.canAdminAll(loggedInMember)) {
            if (ConfigurationAttributeKey.IS_I18N_ACTIVE.get()) {
                model.addAttribute("i18nOptions", I18nUtils.getSelectList());
            }
            model.addAttribute("i18nActive", ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
            return "contenteditor/editor";
        } else {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }
    }

    @GetMapping("/content-editor/contentEditorListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {

        JSONArray responseArray = new JSONArray();
        Long folderId = 1L;
        if (node != null && !node.isEmpty()) {
            folderId = Long.parseLong(node);
        }
        List<IContentFolder> contentFolders = contentClient.getContentFolders(folderId);

        if (contentFolders != null) {
            for (IContentFolder cf : contentFolders) {
                if (cf.getId() != IContentFolder.RESOURCE_FOLDER_ID) {
                    responseArray.put(folderNode(cf.getName(),
                            cf.getId().toString()));
                }
            }
        }
        List<IContentArticleVersion> contentArticles =
                contentClient.getContentFolderArticleVersions(folderId);
        if (contentArticles != null) {
            for (IContentArticleVersion ca : contentArticles) {
                IContentArticle contentArticle = null;
                try {
                    contentArticle = contentClient.getContentArticle(ca.getArticleId());
                    if (contentArticle.isVisible()) {
                        responseArray.put(articleNode(ca.getTitle(), ca.getArticleId()));
                    }
                } catch (ContentNotFoundException e) {
                    // continue
                }
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());
    }


    @GetMapping("/content-editor/contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(HttpServletRequest request,
            HttpServletResponse response, @RequestParam(required = false) Long articleId,
            @RequestParam(required = false) String encoding)
            throws IOException, ContentNotFoundException {
        String defaultEncoding = I18nUtils.DEFAULT_LOCALE.getLanguage();
        if (encoding == null || encoding.isEmpty()) {
            encoding = defaultEncoding;
        }
        IContentArticleVersion contentArticleVersion =
                contentClient.getLatestVersionByArticleIdAndLanguage(articleId, encoding);
        if (contentArticleVersion == null) {
            //if there is no content for the encoding passed, get the default from the database
            contentArticleVersion = contentClient
                    .getLatestVersionByArticleIdAndLanguage(articleId, defaultEncoding);
            contentArticleVersion.setId(0L);
            contentArticleVersion.setLang(encoding);
        }

        JSONObject articleVersion = getContentArticleVersion(articleId, contentArticleVersion);

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    @GetMapping("/content-editor/contentEditorGetArticleVersion")
    public void contentEditorGetArticleVersion(HttpServletRequest request,
            HttpServletResponse response, @RequestParam(required = false) Long articleVersionId)
            throws IOException, ContentNotFoundException {

        IContentArticleVersion contentArticleVersion =
                contentClient.getContentArticleVersion(articleVersionId);

        JSONObject articleVersion =
                getContentArticleVersion(contentArticleVersion.getArticleId(),
                        contentArticleVersion);

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject getContentArticleVersion(@RequestParam(required = false) Long articleId,
            IContentArticleVersion contentArticleVersion) {

        JSONArray versions = new JSONArray();
        List<IContentArticleVersion> cavs = contentClient
                .getContentArticleVersions(0, Integer.MAX_VALUE, null, articleId, null, null,
                        contentArticleVersion.getLang());

        JSONObject articleVersion;
        for (IContentArticleVersion cav : cavs) {
            articleVersion = new JSONObject();
            articleVersion.put("createdAt", cav.getCreatedAt());
            articleVersion.put("contentArticleVersionId", cav.getId());
            versions.put(articleVersion);
        }
        articleVersion = new JSONObject();
        try {
            IContentPage cp = contentClient
                    .getContentPageByContentArticleId(contentArticleVersion.getArticleId());
            articleVersion.put("contentUrl", cp.getTitle());
        } catch (ContentNotFoundException e) {}
        articleVersion.put("title", contentArticleVersion.getTitle());
        articleVersion.put("folderId", contentArticleVersion.getFolderId());
        articleVersion.put("articleId", contentArticleVersion.getArticleId());
        articleVersion.put("content", contentArticleVersion.getContent());
        articleVersion.put("lang", contentArticleVersion.getLang());
        articleVersion.put("createdAt", contentArticleVersion.getCreatedAt());
        articleVersion.put("versions", versions);

        return articleVersion;
    }

    @PostMapping("/content-editor/archiveContentArticle")
    public void archiveContentArticle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long articleId) throws IOException {
        try {
            IContentArticle ca = contentClient.getContentArticle(articleId);
            ca.setVisible(false);
            contentClient.updateContentArticle(ca);
            defaultOperationReturnMessage(true, "Article archived successfully", "", response);
        } catch (ContentNotFoundException e){
            defaultOperationReturnMessage(false, "Article could not be found with id " + articleId, "", response);
        }
    }

    @PostMapping("/content-editor/createArticleFolder")
    public void createArticleFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String folderName,
            @RequestParam(required = false) Long parentFolderId) throws IOException {
        IContentFolder contentFolder = new ContentFolder();
        contentFolder.setName(folderName);
        contentFolder.setParentFolderId(parentFolderId);

        contentClient.createContentFolder(contentFolder);

        defaultOperationReturnMessage(true, "Folder created successfully", "", response);
    }

    @PostMapping("/content-editor/moveArticleVersion")
    public void moveArticleVersion(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long articleId,
            @RequestParam(required = false) Long folderId)
            throws IOException, ContentNotFoundException {
        long userId = MemberAuthUtil.getUserId();

        IContentArticleVersion contentArticleVersion =
                contentClient.getLatestContentArticleVersion(articleId);
        IContentArticleVersion newContentArticleVersion = new ContentArticleVersion();
        newContentArticleVersion.setTitle(contentArticleVersion.getTitle());
        newContentArticleVersion.setContent(contentArticleVersion.getContent());
        newContentArticleVersion.setArticleId(contentArticleVersion.getArticleId());

        newContentArticleVersion.setAuthorUserId(userId);
        newContentArticleVersion.setFolderId(folderId);
        contentClient.createContentArticleVersion(newContentArticleVersion);

        defaultOperationReturnMessage(true, "Article moved successfully", "", response);
    }

    @PostMapping("/content-editor/previewArticle")
    public String previewArticle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String content, Model model, UserWrapper member) {
        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        model.addAttribute("content", content);
        return "contenteditor/previewArticleContent";
    }

    @PostMapping("/content-editor/saveContentArticleVersion")
    public void saveContentArticleVersion(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @RequestParam(required = false) Long articleId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long folderId,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String lang) throws IOException {

        if (!permissionClient.canAdminAll(member)) {
            defaultOperationReturnMessage(false, "Not allowed to save article", "", response);
        }

        if (lang == null) {
            lang = I18nUtils.DEFAULT_LOCALE.getLanguage();
        }
        IContentArticleVersion contentArticleVersion = new ContentArticleVersion();

        contentArticleVersion.setArticleId(articleId);
        contentArticleVersion.setLang(lang);

        contentArticleVersion.setAuthorUserId(member.getId());
        contentArticleVersion.setFolderId((folderId));
        contentArticleVersion.setTitle(title);
        contentArticleVersion.setContent(content);
        contentArticleVersion = contentClient.createContentArticleVersion(contentArticleVersion);

        defaultOperationReturnMessage(true, "Article version created successfully",
                contentArticleVersion.getArticleId().toString(), response);
    }
}
