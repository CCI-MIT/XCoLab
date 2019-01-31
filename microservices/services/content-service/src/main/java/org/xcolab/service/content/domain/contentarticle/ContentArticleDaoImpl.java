package org.xcolab.service.content.domain.contentarticle;

import org.apache.commons.lang3.ArrayUtils;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.tables.pojos.ContentArticle;
import org.xcolab.model.tables.records.ContentArticleRecord;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.content.wrappers.ContentArticleWrapper;

import java.util.List;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;
import static org.xcolab.model.Tables.CONTENT_ARTICLE_VERSION;

@Repository
public class ContentArticleDaoImpl implements ContentArticleDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public IContentArticle create(IContentArticle contentArticle) {

        ContentArticleRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_USER_ID, contentArticle.getAuthorUserId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION_ID, contentArticle.getMaxVersionId())
                .set(CONTENT_ARTICLE.FOLDER_ID, contentArticle.getFolderId())
                .set(CONTENT_ARTICLE.CREATED_AT, contentArticle.getCreatedAt())
                .set(CONTENT_ARTICLE.VISIBLE, contentArticle.isVisible())
                .returning(CONTENT_ARTICLE.ID)
                .fetchOne();
        if (ret != null) {
            contentArticle.setId(ret.getValue(CONTENT_ARTICLE.ID));
            return contentArticle;
        } else {
            return null;
        }

    }

    @Override
    public int delete(long contentArticleId) {
        return dslContext.deleteFrom(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.ID.eq(contentArticleId))
                .execute();
    }

    @Override
    public boolean update(IContentArticle contentArticle) {
        return dslContext.update(CONTENT_ARTICLE)
                .set(CONTENT_ARTICLE.AUTHOR_USER_ID, contentArticle.getAuthorUserId())
                .set(CONTENT_ARTICLE.EDIT_ROLE_GROUP_ID, contentArticle.getEditRoleGroupId())
                .set(CONTENT_ARTICLE.VIEW_ROLE_GROUP_ID, contentArticle.getViewRoleGroupId())
                .set(CONTENT_ARTICLE.MAX_VERSION_ID, contentArticle.getMaxVersionId())
                .set(CONTENT_ARTICLE.FOLDER_ID, contentArticle.getFolderId())
                .set(CONTENT_ARTICLE.CREATED_AT, contentArticle.getCreatedAt())
                .set(CONTENT_ARTICLE.VISIBLE, contentArticle.isVisible())
                .where(CONTENT_ARTICLE.ID.eq(contentArticle.getId()))
                .execute() > 0;
    }

    @Override
    public IContentArticle get(Long contentArticleId) throws NotFoundException {
        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE)
                .where(CONTENT_ARTICLE.ID.eq(contentArticleId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticle.class);
    }

    @Override
    public List<ContentArticleWrapper> getArticles() {
        final Field<?>[] fields =
                ArrayUtils.add(CONTENT_ARTICLE.fields(), CONTENT_ARTICLE_VERSION.TITLE);
        return dslContext.select(fields)
                .from(CONTENT_ARTICLE)
                .join(CONTENT_ARTICLE_VERSION).on(CONTENT_ARTICLE_VERSION.ARTICLE_ID
                        .eq(CONTENT_ARTICLE.ID))
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
