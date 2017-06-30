package org.xcolab.service.contents.domain.contentarticleversion;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.records.ContentArticleVersionRecord;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;
import java.util.stream.Collectors;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;
import static org.xcolab.model.Tables.CONTENT_ARTICLE_VERSION;

@Repository
public class ContentArticleVersionDaoImpl implements ContentArticleVersionDao {

    private final DSLContext dslContext;

    private final ContentFolderDao contentFolderDao;

    @Autowired
    public ContentArticleVersionDaoImpl(DSLContext dslContext, ContentFolderDao contentFolderDao) {
        Assert.notNull(dslContext);
        Assert.notNull(contentFolderDao);
        this.dslContext = dslContext;
        this.contentFolderDao = contentFolderDao;
    }

    @Override
    public ContentArticleVersion create(ContentArticleVersion contentArticleVersion) {
        ContentArticleVersionRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_ID, contentArticleVersion.getAuthorId())
                .set(CONTENT_ARTICLE_VERSION.CREATE_DATE, contentArticleVersion.getCreateDate())
                .set(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID, contentArticleVersion.getContentArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .set(CONTENT_ARTICLE_VERSION.LANG, contentArticleVersion.getLang())
                .returning(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID)
                .fetchOne();

        if (ret != null) {
            contentArticleVersion.setContentArticleVersionId(ret.getValue(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID));
            return contentArticleVersion;
        } else {
            return null;
        }
    }

    @Override
    public int deleteByArticleId(long contentArticleId) {
        return dslContext.deleteFrom(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.eq(contentArticleId))
                .execute();
    }

    @Override
    public boolean update(ContentArticleVersion contentArticleVersion) {
        return dslContext.update(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_ID, contentArticleVersion.getAuthorId())
                .set(CONTENT_ARTICLE_VERSION.CREATE_DATE, contentArticleVersion.getCreateDate())
                .set(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID, contentArticleVersion.getContentArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.eq(contentArticleVersion.getContentArticleVersionId()))
                .execute() > 0;
    }

    @Override
    public ContentArticleVersion get(Long contentArticleId) throws NotFoundException {
        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.eq(contentArticleId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticleVersion.class);
    }

    @Override
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
    public ContentArticleVersion getByArticleVersionLanguage(Long articleId, String language) throws NotFoundException {

        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.eq(articleId))
                .and(CONTENT_ARTICLE_VERSION.LANG.eq(language))
                .orderBy(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.desc())
                .limit(0,1)
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticleVersion.class);
    }

    @Override
    public List<ContentArticleVersion> findByGiven(PaginationHelper paginationHelper,
        Long contentArticleId, Long contentArticleVersion, Long folderId, Long ancestorFolderId,
        String title) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .getQuery();

        if (contentArticleId != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.eq(contentArticleId));
        }
        if (contentArticleVersion != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.eq(contentArticleVersion));
        }
        if (folderId != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.FOLDER_ID.eq(folderId));
        }
        if (ancestorFolderId != null) {
            final List<Long> ancestorFolderIds =
                contentFolderDao.findByAncestorFolderId(ancestorFolderId).stream()
                    .map(ContentFolder::getContentFolderId).collect(Collectors.toList());
            query.addConditions(CONTENT_ARTICLE_VERSION.FOLDER_ID.in(ancestorFolderIds));
        }
        if (title != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.TITLE.eq(title));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "contentArticleId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.asc()
                            : CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_ID.desc());
                    break;
                case "folderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.FOLDER_ID.asc()
                            : CONTENT_ARTICLE_VERSION.FOLDER_ID.desc());
                    break;
                case "title":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.TITLE.asc()
                            : CONTENT_ARTICLE_VERSION.TITLE.desc());
                    break;
                default:
                case "contentArticleVersion":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.asc()
                            : CONTENT_ARTICLE_VERSION.CONTENT_ARTICLE_VERSION_ID.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ContentArticleVersion.class);
    }
}
