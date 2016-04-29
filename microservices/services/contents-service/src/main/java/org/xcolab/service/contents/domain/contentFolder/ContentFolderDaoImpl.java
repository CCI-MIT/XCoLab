package org.xcolab.service.contents.domain.contentFolder;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.records.ContentFolderRecord;

import java.util.List;

import static org.xcolab.model.Tables.CONTENT_FOLDER;

@Repository
public class ContentFolderDaoImpl implements ContentFolderDao {
    @Autowired
    private DSLContext dslContext;

    public ContentFolder create(ContentFolder contentFolder) {
        ContentFolderRecord ret = this.dslContext.insertInto(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.CONTENT_FOLDER_NAME, contentFolder.getContentFolderName())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_DESCRIPTION, contentFolder.getContentFolderDescription())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_ID, contentFolder.getParentFolderId())
                .returning(CONTENT_FOLDER.CONTENT_FOLDER_ID)
                .fetchOne();
        if (ret != null) {
            contentFolder.setContentFolderId(ret.getValue(CONTENT_FOLDER.CONTENT_FOLDER_ID));
            return contentFolder;
        } else {
            return null;
        }

    }

    public void update(ContentFolder contentFolder) {
        this.dslContext.update(CONTENT_FOLDER)
                .set(CONTENT_FOLDER.CONTENT_FOLDER_NAME, contentFolder.getContentFolderName())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_DESCRIPTION, contentFolder.getContentFolderDescription())
                .set(CONTENT_FOLDER.CONTENT_FOLDER_ID, contentFolder.getParentFolderId())
                .where(CONTENT_FOLDER.CONTENT_FOLDER_ID.eq(contentFolder.getContentFolderId()))
                .execute();
    }

    public ContentFolder get(Long contentFolderId) {
        return this.dslContext.select()
                .from(CONTENT_FOLDER)
                .where(CONTENT_FOLDER.CONTENT_FOLDER_ID.eq(contentFolderId))
                .fetchOneInto(ContentFolder.class);
    }

    public List<ContentFolder> getChildFolders(Long contentFolderId) {
        if (contentFolderId == null) {
            return this.dslContext.select()
                    .from(CONTENT_FOLDER)
                    .where(CONTENT_FOLDER.PARENT_FOLDER_ID.isNull())
                    .fetch().into(ContentFolder.class);
        } else {
            return this.dslContext.select()
                    .from(CONTENT_FOLDER)
                    .where(CONTENT_FOLDER.PARENT_FOLDER_ID.eq(contentFolderId))
                    .fetch().into(ContentFolder.class);
        }
    }
}
