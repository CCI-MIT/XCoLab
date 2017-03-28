package org.xcolab.service.flagging.domain.filteredentry;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.FilteredEntry;
import org.xcolab.model.tables.records.FilteredEntryRecord;
import org.xcolab.service.flagging.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.FILTERED_ENTRY;

@Repository
public class FilteredEntryDaoImpl implements FilteredEntryDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public FilteredEntry create(FilteredEntry filteredEntry) {

        FilteredEntryRecord ret = this.dslContext.insertInto(FILTERED_ENTRY)
                .set(FILTERED_ENTRY.SOURCE, filteredEntry.getSource())
                .set(FILTERED_ENTRY.SOURCE_ID, filteredEntry.getSourceId())
                .set(FILTERED_ENTRY.AUTHOR_ID, filteredEntry.getAuthorId())
                .set(FILTERED_ENTRY.FULL_TEXT, filteredEntry.getFullText())
                .set(FILTERED_ENTRY.STATUS, filteredEntry.getStatus())
                .set(FILTERED_ENTRY.CREATED_AT, filteredEntry.getCreatedAt())
                .set(FILTERED_ENTRY.ANSWERED_AT, filteredEntry.getAnsweredAt())
                .set(FILTERED_ENTRY.UUID, filteredEntry.getUuid())
                .set(FILTERED_ENTRY.RESPONSE_FULL_TEXT, filteredEntry.getResponseFullText())
                .returning(FILTERED_ENTRY.FILTERED_ENTRY_ID)
                .fetchOne();
        if (ret != null) {
            filteredEntry.setFilteredEntryId(ret.getValue(FILTERED_ENTRY.FILTERED_ENTRY_ID));
            return filteredEntry;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(FilteredEntry filteredEntry) {
        return dslContext.update(FILTERED_ENTRY)
                .set(FILTERED_ENTRY.SOURCE, filteredEntry.getSource())
                .set(FILTERED_ENTRY.SOURCE_ID, filteredEntry.getSourceId())
                .set(FILTERED_ENTRY.AUTHOR_ID, filteredEntry.getAuthorId())
                .set(FILTERED_ENTRY.FULL_TEXT, filteredEntry.getFullText())
                .set(FILTERED_ENTRY.STATUS, filteredEntry.getStatus())
                .set(FILTERED_ENTRY.CREATED_AT, filteredEntry.getCreatedAt())
                .set(FILTERED_ENTRY.ANSWERED_AT, filteredEntry.getAnsweredAt())
                .set(FILTERED_ENTRY.RESPONSE_FULL_TEXT, filteredEntry.getResponseFullText())
                .where(FILTERED_ENTRY.FILTERED_ENTRY_ID.eq(filteredEntry.getFilteredEntryId()))
                .execute() > 0;
    }

    @Override
    public FilteredEntry get(Long filteredEntryId) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(FILTERED_ENTRY)
                .where(FILTERED_ENTRY.FILTERED_ENTRY_ID.eq(filteredEntryId)).fetchOne();

        if (record == null) {
            throw new NotFoundException("FilteredEntry with id " + filteredEntryId + " does not exist");
        }
        return record.into(FilteredEntry.class);

    }

    @Override
    public FilteredEntry getByUuid(String uuid) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(FILTERED_ENTRY)
                .where(FILTERED_ENTRY.UUID.eq(uuid)).fetchOne();

        if (record == null) {
            throw new NotFoundException("FilteredEntry with uuid " + uuid + " does not exist");
        }
        return record.into(FilteredEntry.class);

    }


    @Override
    public FilteredEntry getByAuthorAndSourceAndSourceId(Long authorId, Long sourceId, Long source) {

        final Record record = this.dslContext.selectFrom(FILTERED_ENTRY)
                .where(FILTERED_ENTRY.AUTHOR_ID.eq(authorId))
                .and(FILTERED_ENTRY.SOURCE_ID.eq(sourceId))
                .and(FILTERED_ENTRY.SOURCE.eq(source))
                .limit(0, 1)
                .fetchOne();

        if (record == null) {
            return null;
        }
        return record.into(FilteredEntry.class);
    }


    @Override
    public List<FilteredEntry> getByStatus(Integer statusId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(FILTERED_ENTRY)
                .where(FILTERED_ENTRY.STATUS.eq(statusId)).getQuery();

        return query.fetchInto(FilteredEntry.class);
    }

}
