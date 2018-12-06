package org.xcolab.view.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.content.ContentsClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.util.i18n.I18nUtils;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadContentArticleTag extends BodyTagSupport {

    private long articleId;

    @Autowired
    private ContentsClient contentsClient;

    @Override
    public int doStartTag() throws JspException {
        if (articleId > 0) {
            try {
                final IContentArticle contentArticle = contentsClient.getContentArticle(articleId);

                Locale locale = LocaleContextHolder.getLocale();
                String localeString = "en";
                if (locale.getLanguage() != null) {
                    localeString = locale.getLanguage();
                }

                IContentArticleVersion contentArticleVersion = contentsClient
                        .getLatestVersionByArticleIdAndLanguage(
                                contentArticle.getId(), localeString);
                if (contentArticleVersion == null) {
                    contentArticleVersion = contentsClient.getLatestVersionByArticleIdAndLanguage(
                            contentArticle.getId(),
                            I18nUtils.DEFAULT_LOCALE.getLanguage());
                }

                pageContext.setAttribute("contentArticle", contentArticle);
                pageContext.setAttribute("contentArticleVersion", contentArticleVersion);
            } catch (ContentNotFoundException e) {
                //ignored
            }
        }
        return EVAL_BODY_INCLUDE;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
