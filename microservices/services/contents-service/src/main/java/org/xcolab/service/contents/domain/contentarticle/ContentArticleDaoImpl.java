package org.xcolab.service.contents.domain.contentarticle;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.records.ContentArticleRecord;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;

@Repository
public class ContentArticleDaoImpl implements ContentArticleDao {

    @Autowired
    private DSLContext dslContext;

    public ContentArticle create(ContentArticle contentArticle) {
        ContentArticleRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_ID, contentArticle.getAuthorId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION, contentArticle.getMaxVersion())
                .set(CONTENT_ARTICLE.CREATE_DATE, contentArticle.getCreateDate())
                .set(CONTENT_ARTICLE.VISIBLE, contentArticle.getVisible())
                .returning(CONTENT_ARTICLE.CONTENT_ARTICLE_ID)
                .fetchOne();
        if (ret != null) {
            contentArticle.setContentArticleId(ret.getValue(CONTENT_ARTICLE.CONTENT_ARTICLE_ID));
            return contentArticle;
        } else {
            return null;
        }

    }

    public void update(ContentArticle contentArticle) {
        this.dslContext.update(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_ID, contentArticle.getAuthorId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION, contentArticle.getMaxVersion())
                .set(CONTENT_ARTICLE.CREATE_DATE, contentArticle.getCreateDate())
                .set(CONTENT_ARTICLE.VISIBLE, contentArticle.getVisible())
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticle.getContentArticleId()))
                .execute();
    }

    public ContentArticle get(Long contentArticleId) {
        return this.dslContext.select()
                .from(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticleId))
                .fetchOneInto(ContentArticle.class);
    }


}
