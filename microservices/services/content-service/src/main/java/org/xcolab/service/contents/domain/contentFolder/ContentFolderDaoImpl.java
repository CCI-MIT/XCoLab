package org.xcolab.service.contents.domain.contentFolder;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.records.ContentFolderRecord;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.SortColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.xcolab.model.Tables.CONTENT_FOLDER;

@Repository
public class ContentFolderDaoImpl implements ContentFolderDao {

    private final DSLContext dslContext;

    @Autowired
    public ContentFolderDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext is required");
        this.dslContext = dslContext;
    }

    @Override
    public ContentFolder create(ContentFolder contentFolder) {
        ContentFolderRecord ret = this.dslContext.insertInto(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.NAME, contentFolder.getName())
                .set(CONTENT_FOLDER.DESCRIPTION, contentFolder.getDescription())
                .set(CONTENT_FOLDER.PARENT_FOLDER_ID, contentFolder.getParentFolderId())
                .returning(CONTENT_FOLDER.ID)
                .fetchOne();
        if (ret != null) {
            contentFolder.setId(ret.getValue(CONTENT_FOLDER.ID));
            return contentFolder;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(ContentFolder contentFolder) {
         return dslContext.update(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.NAME, contentFolder.getName())
                .set(CONTENT_FOLDER.DESCRIPTION, contentFolder.getDescription())
                .set(CONTENT_FOLDER.PARENT_FOLDER_ID, contentFolder.getParentFolderId())
                .where(CONTENT_FOLDER.ID.eq(contentFolder.getId()))
                .execute() > 0;
    }

    @Override
    public ContentFolder get(Long contentFolderId) {
        return this.dslContext.select()
                .from(CONTENT_FOLDER)
                .where(CONTENT_FOLDER.ID.eq(contentFolderId))
                .fetchOneInto(ContentFolder.class);
    }

    @Override
    public List<ContentFolder> findByGiven(PaginationHelper paginationHelper,
            Long parentFolderId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTENT_FOLDER)
                .getQuery();

        if (parentFolderId != null) {
            query.addConditions(CONTENT_FOLDER.PARENT_FOLDER_ID.eq(parentFolderId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "contentFolderName":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_FOLDER.NAME.asc()
                            : CONTENT_FOLDER.NAME.desc());
                    break;

                case "parentFolderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_FOLDER.PARENT_FOLDER_ID.asc()
                            : CONTENT_FOLDER.PARENT_FOLDER_ID.desc());
                    break;
                default:
                case "contentFolderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_FOLDER.ID.asc()
                            : CONTENT_FOLDER.ID.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(ContentFolder.class);
    }

    @Override
    public List<ContentFolder> findByAncestorFolderId(long ancestorFolderId) {
        final List<ContentFolder> children = findByGiven(PaginationHelper.EVERYTHING, ancestorFolderId);
        if (children.isEmpty()) {
            return Collections.emptyList();
        }
        List<ContentFolder> descendants = new ArrayList<>(children);
        for (ContentFolder child : children) {
            descendants.addAll(findByAncestorFolderId(child.getId()));
        }
        return descendants;
    }
}
