package org.xcolab.service.content.domain.contentarticleversion;

import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.tables.pojos.ContentArticleVersion;
import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.records.ContentArticleVersionRecord;
import org.xcolab.service.content.domain.contentfolder.ContentFolderDao;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.stream.Collectors;

import static org.xcolab.model.Tables.CONTENT_ARTICLE;
import static org.xcolab.model.Tables.CONTENT_ARTICLE_VERSION;

@Repository
public class ContentArticleVersionDaoImpl implements ContentArticleVersionDao {

    public static final String DEFAULT_LANGUAGE = "en";
    private final DSLContext dslContext;

    private final ContentFolderDao contentFolderDao;

    @Autowired
    public ContentArticleVersionDaoImpl(DSLContext dslContext, ContentFolderDao contentFolderDao) {
        Assert.notNull(dslContext, "DSLContext is required");
        Assert.notNull(contentFolderDao, "ContentFolderDao is required");
        this.dslContext = dslContext;
        this.contentFolderDao = contentFolderDao;
    }

    @Override
    public IContentArticleVersion create(IContentArticleVersion contentArticleVersion) {
        ContentArticleVersionRecord ret = this.dslContext.insertInto(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_USER_ID, contentArticleVersion.getAuthorUserId())
                .set(CONTENT_ARTICLE_VERSION.CREATED_AT, contentArticleVersion.getCreatedAt())
                .set(CONTENT_ARTICLE_VERSION.ARTICLE_ID, contentArticleVersion.getArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .set(CONTENT_ARTICLE_VERSION.LANG, contentArticleVersion.getLang())
                .returning(CONTENT_ARTICLE_VERSION.ID)
                .fetchOne();

        if (ret != null) {
            contentArticleVersion.setId(ret.getValue(CONTENT_ARTICLE_VERSION.ID));
            return contentArticleVersion;
        } else {
            return null;
        }
    }

    @Override
    public int deleteByArticleId(long contentArticleId) {
        return dslContext.deleteFrom(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.ARTICLE_ID.eq(contentArticleId))
                .execute();
    }

    @Override
    public boolean update(IContentArticleVersion contentArticleVersion) {
        return dslContext.update(CONTENT_ARTICLE_VERSION)
                .set(CONTENT_ARTICLE_VERSION.AUTHOR_USER_ID, contentArticleVersion.getAuthorUserId())
                .set(CONTENT_ARTICLE_VERSION.CREATED_AT, contentArticleVersion.getCreatedAt())
                .set(CONTENT_ARTICLE_VERSION.ARTICLE_ID, contentArticleVersion.getArticleId())
                .set(CONTENT_ARTICLE_VERSION.FOLDER_ID, contentArticleVersion.getFolderId())
                .set(CONTENT_ARTICLE_VERSION.CONTENT, contentArticleVersion.getContent())
                .set(CONTENT_ARTICLE_VERSION.TITLE, contentArticleVersion.getTitle())
                .where(CONTENT_ARTICLE_VERSION.ID.eq(contentArticleVersion.getId()))
                .execute() > 0;
    }

    @Override
    public IContentArticleVersion get(Long id) throws NotFoundException {
        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.ID.eq(id))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticleVersion.class);
    }

    @Override
    public List<IContentArticleVersion> getByFolderId(Long contentFolderId) {
        if (contentFolderId == null) {
            return this.dslContext.select(CONTENT_ARTICLE_VERSION.fields())
                    .from(CONTENT_ARTICLE_VERSION)
                    .join(CONTENT_ARTICLE).on(CONTENT_ARTICLE.ID.eq(CONTENT_ARTICLE_VERSION.ARTICLE_ID))
                    .where(CONTENT_ARTICLE_VERSION.FOLDER_ID.isNull())
                    .and(CONTENT_ARTICLE.MAX_VERSION_ID.eq(CONTENT_ARTICLE_VERSION.ARTICLE_ID))
                    .fetch()
                    .into(ContentArticleVersion.class);
        } else {
            return this.dslContext.select(CONTENT_ARTICLE_VERSION.fields())
                    .from(CONTENT_ARTICLE_VERSION)
                    .join(CONTENT_ARTICLE).on(CONTENT_ARTICLE.ID.eq(CONTENT_ARTICLE_VERSION.ARTICLE_ID))
                    .where(CONTENT_ARTICLE_VERSION.FOLDER_ID.eq(contentFolderId))
                    .and(CONTENT_ARTICLE.MAX_VERSION_ID.eq(CONTENT_ARTICLE_VERSION.ID))
                    .fetch()
                    .into(ContentArticleVersion.class);
        }
    }

    @Override
    public IContentArticleVersion getLatestVersionByArticleIdAndLanguage(Long articleId, String language)
            throws NotFoundException {

        final Record record = this.dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .where(CONTENT_ARTICLE_VERSION.ARTICLE_ID.eq(articleId))
                .and(CONTENT_ARTICLE_VERSION.LANG.eq(language))
                .orderBy(CONTENT_ARTICLE_VERSION.ID.desc())
                .limit(0,1)
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(ContentArticleVersion.class);
    }

    @Override
    public List<IContentArticleVersion> findByGiven(PaginationHelper paginationHelper,
        Long contentArticleId, Long contentArticleVersion, Long folderId, Long ancestorFolderId,
        String title, String lang) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTENT_ARTICLE_VERSION)
                .getQuery();

        if (contentArticleId != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.ARTICLE_ID.eq(contentArticleId));
        }
        if (contentArticleVersion != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.ID.eq(contentArticleVersion));
        }
        if (folderId != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.FOLDER_ID.eq(folderId));
        }
        if (ancestorFolderId != null) {
            final List<Long> ancestorFolderIds =
                contentFolderDao.findByAncestorFolderId(ancestorFolderId).stream()
                    .map(IContentFolder::getId).collect(Collectors.toList());
            query.addConditions(CONTENT_ARTICLE_VERSION.FOLDER_ID.in(ancestorFolderIds));
        }
        if (title != null) {
            query.addConditions(CONTENT_ARTICLE_VERSION.TITLE.eq(title));
        }

        if (lang != null) {
            if (StringUtils.isEmpty(lang) || DEFAULT_LANGUAGE.equalsIgnoreCase(lang)) {
                query.addConditions(CONTENT_ARTICLE_VERSION.LANG.eq(StringUtils.EMPTY)
                        .or(CONTENT_ARTICLE_VERSION.LANG.equalIgnoreCase(DEFAULT_LANGUAGE)));
            } else {
                query.addConditions(CONTENT_ARTICLE_VERSION.LANG.equalIgnoreCase(lang));
            }
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "contentArticleId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.ARTICLE_ID.asc()
                            : CONTENT_ARTICLE_VERSION.ARTICLE_ID.desc());
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
                case "contentArticleVersion":
                default:
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_ARTICLE_VERSION.ID.asc()
                            : CONTENT_ARTICLE_VERSION.ID.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ContentArticleVersion.class);
    }
}
