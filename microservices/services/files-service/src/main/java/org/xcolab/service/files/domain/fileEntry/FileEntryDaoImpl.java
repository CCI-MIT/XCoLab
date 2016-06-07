package org.xcolab.service.files.domain.fileEntry;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.model.tables.records.FileEntryRecord;
import org.xcolab.service.files.exceptions.NotFoundException;

import static org.xcolab.model.Tables.FILE_ENTRY;

@Repository
public class FileEntryDaoImpl implements FileEntryDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public FileEntry create(FileEntry fileEntry) {

        FileEntryRecord ret = this.dslContext.insertInto(FILE_ENTRY)
                .set(FILE_ENTRY.FILE_ENTRY_EXTENSION, fileEntry.getFileEntryExtension())
                .set(FILE_ENTRY.CREATE_DATE, fileEntry.getCreateDate())
                .set(FILE_ENTRY.FILE_ENTRY_SIZE, fileEntry.getFileEntrySize())
                .set(FILE_ENTRY.FILE_ENTRY_NAME, fileEntry.getFileEntryName())
                .returning(FILE_ENTRY.FILE_ENTRY_ID)
                .fetchOne();
        if (ret != null) {
            fileEntry.setFileEntryId(ret.getValue(FILE_ENTRY.FILE_ENTRY_ID));
            return fileEntry;
        } else {
            return null;
        }

    }

    @Override
    public FileEntry get(Long fileEntryid) throws NotFoundException {
        final Record record = this.dslContext.select()
                .from(FILE_ENTRY)
                .where(FILE_ENTRY.FILE_ENTRY_ID.eq(fileEntryid))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException();
        }
        return record.into(FileEntry.class);
    }
}
