package org.xcolab.view.tags;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadContentArticleTag extends BodyTagSupport {

    private long articleId;

    @Override
    public int doStartTag() throws JspException {
        if (articleId > 0) {
            try {
                final ContentArticle contentArticle = ContentsClient
                        .getContentArticle(articleId);
                final long version = contentArticle.getMaxVersionId();
                final ContentArticleVersion contentArticleVersion = ContentsClient
                        .getContentArticleVersion(version);
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
