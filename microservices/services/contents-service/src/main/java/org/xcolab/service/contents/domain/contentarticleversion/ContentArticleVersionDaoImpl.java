package org.xcolab.service.contents.domain.contentarticleversion;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.model.tables.records.ContentArticleVersionRecord;

import java.util.List;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;
import static org.xcolab.model.Tables.CONTENT_ARTICLE_VERSION;

@Repository
public class ContentArticleVersionDaoImpl implements ContentArticleVersionDao {

    @Autowired
    private DSLContext dslContext;

    public ContentArticleVersion create(ContentArticleVersion contentArticleVersion) {
        ContentArticleVersionRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_ID, contentArticleVersion.getAuthorId())
                .set(CONTENT_ARTICLE_VERSION.CREATE_DATE, contentArticleVersion.getCreateDate())
                .set(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID, contentArticleVersion.getContentArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .returning(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID)
                .fetchOne();

        if (ret != null) {
            contentArticleVersion.setContentArticleId(ret.getValue(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID));
            return contentArticleVersion;
        } else {
            return null;
        }

    }

    public void update(ContentArticleVersion contentArticleVersion) {
        this.dslContext.update(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_ID, contentArticleVersion.getAuthorId())
                .set(CONTENT_ARTICLE_VERSION.CREATE_DATE, contentArticleVersion.getCreateDate())
                .set(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID, contentArticleVersion.getContentArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .where(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(contentArticleVersion.getContentArticleVersionId()))
                .execute();
    }

    public ContentArticleVersion get(Long contentArticleId) {
        return this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.eq(contentArticleId))
                .fetchOneInto(ContentArticleVersion.class);
    }

    public List<ContentArticleVersion> getByFolderId(Long contentFolderId) {
        if (contentFolderId == null) {
            return this.dslContext.select()
                    .from(CONTENT_ARTICLE_VERSION)
                    .join(CONTENT_ARTICLE).on(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID))
                    .where(CONTENT_ARTICLE_VERSION.FOLDER_ID.isNull())
                    .and(CONTENT_ARTICLE.MAX_VERSION_ID.eq(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID))
                    .fetch()
                    .into(ContentArticleVersion.class);
        }
        else {
            return this.dslContext.select()
                    .from(CONTENT_ARTICLE_VERSION)
                    .join(CONTENT_ARTICLE).on(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID))
                    .where(CONTENT_ARTICLE_VERSION.FOLDER_ID.eq(contentFolderId))
                    .and(CONTENT_ARTICLE.MAX_VERSION_ID.eq(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID))
                    .fetch()
                    .into(ContentArticleVersion.class);
        }
    }

    public ContentArticleVersion getLatestVersionByContentArticleId(Long contentArticleVersionId) {
        return this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .join(CONTENT_ARTICLE).on(CONTENT_ARTICLE.CONTENT_ARTICLE_ID.eq(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID))
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.eq(contentArticleVersionId))
                .and(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.equal(CONTENT_ARTICLE.MAX_VERSION_ID))
                .fetchOneInto(ContentArticleVersion.class);
    }
}
