package org.xcolab.service.contents.domain.contentarticle;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.records.ContentArticleRecord;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.wrappers.ContentArticleWrapper;

import java.util.List;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;
import static org.xcolab.model.Tables.CONTENT_ARTICLE_VERSION;

@Repository
public class ContentArticleDaoImpl implements ContentArticleDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ContentArticle create(ContentArticle contentArticle) {

        ContentArticleRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_ID, contentArticle.getAuthorId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION_ID, contentArticle.getMaxVersionId())
                .set(CONTENT_ARTICLE.FOLDER_ID, contentArticle.getFolderId())
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

    @Override
    public int delete(long contentArticleId) {
        return dslContext.deleteFrom(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticleId))
                .execute();
    }

    @Override
    public boolean update(ContentArticle contentArticle) {
        return dslContext.update(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_ID, contentArticle.getAuthorId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION_ID, contentArticle.getMaxVersionId())
                .set(CONTENT_ARTICLE.FOLDER_ID, contentArticle.getFolderId())
                .set(CONTENT_ARTICLE.CREATE_DATE, contentArticle.getCreateDate())
                .set(CONTENT_ARTICLE.VISIBLE, contentArticle.getVisible())
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticle.getContentArticleId()))
                .execute() > 0;
    }

    @Override
    public ContentArticle get(Long contentArticleId) throws NotFoundException {
        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticleId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticle.class);
    }

    @Override
    public List<ContentArticleWrapper> getArticles() {
        return dslContext.select()
                .from(CONTENT_ARTICLE)
                .join(CONTENT_ARTICLE_VERSION).on(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID
                        .eq(CONTENT_ARTICLE.CONTENT_ARTICLE_ID))
                .fetchInto(ContentArticleWrapper.class);
    }

    @Override
    public List<ContentArticleWrapper> getArticlesInFolder(long folderId) {
        return dslContext.select()
                .from(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.FOLDER_ID.eq(folderId))
                .fetchInto(ContentArticleWrapper.class);
    }
}
