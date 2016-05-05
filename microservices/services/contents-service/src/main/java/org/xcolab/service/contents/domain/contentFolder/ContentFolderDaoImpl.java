package org.xcolab.service.contents.domain.contentFolder;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.records.ContentFolderRecord;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.CONTENT_FOLDER;

@Repository
public class ContentFolderDaoImpl implements ContentFolderDao {
    @Autowired
    private DSLContext dslContext;

    @Override
    public ContentFolder create(ContentFolder contentFolder) {
        ContentFolderRecord ret = this.dslContext.insertInto(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.CONTENT_FOLDER_NAME, contentFolder.getContentFolderName())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_DESCRIPTION, contentFolder.getContentFolderDescription())
                .set(CONTENT_FOLDER.PARENT_FOLDER_ID, contentFolder.getParentFolderId())
                .returning(CONTENT_FOLDER.CONTENT_FOLDER_ID)
                .fetchOne();
        if (ret != null) {
            contentFolder.setContentFolderId(ret.getValue(CONTENT_FOLDER.CONTENT_FOLDER_ID));
            return contentFolder;
        } else {
            return null;
        }

    }

    @Override
    public void update(ContentFolder contentFolder) {
        this.dslContext.update(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.CONTENT_FOLDER_NAME, contentFolder.getContentFolderName())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_DESCRIPTION, contentFolder.getContentFolderDescription())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_ID, contentFolder.getParentFolderId())
                .where(CONTENT_FOLDER.CONTENT_FOLDER_ID.eq(contentFolder.getContentFolderId()))
                .execute();
    }

    @Override
    public ContentFolder get(Long contentFolderId) {
        return this.dslContext.select()
                .from(CONTENT_FOLDER)
                .where(CONTENT_FOLDER.CONTENT_FOLDER_ID.eq(contentFolderId))
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
                            ? CONTENT_FOLDER.CONTENT_FOLDER_NAME.asc()
                            : CONTENT_FOLDER.CONTENT_FOLDER_NAME.desc());
                    break;

                case "parentFolderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_FOLDER.PARENT_FOLDER_ID.asc()
                            : CONTENT_FOLDER.PARENT_FOLDER_ID.desc());
                    break;
                default:
                case "contentFolderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? CONTENT_FOLDER.CONTENT_FOLDER_ID.asc()
                            : CONTENT_FOLDER.CONTENT_FOLDER_ID.desc());
                    break;
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
        return query.fetchInto(ContentFolder.class);
    }
}
