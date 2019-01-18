package org.xcolab.service.contest.domain.focusarea;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.model.tables.records.FocusAreaRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.FOCUS_AREA;

@Repository
public class FocusAreaDaoImpl implements FocusAreaDao {

    private final DSLContext dslContext;

    @Autowired
    public FocusAreaDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    @Override
    public FocusAreaWrapper get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(FOCUS_AREA)
                .where(FOCUS_AREA.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("FocusArea with id " + id + " does not exist");
        }
        return record.into(FocusAreaWrapper.class);
    }

    @Override
    public FocusAreaWrapper create(FocusAreaWrapper focusArea) {
        FocusAreaRecord ret = this.dslContext.insertInto(FOCUS_AREA)
                .set(FOCUS_AREA.NAME, focusArea.getName())
                .set(FOCUS_AREA.SORT_ORDER, focusArea.getSortOrder())
                .returning(FOCUS_AREA.ID)
                .fetchOne();
        if (ret != null) {
            focusArea.setId(ret.getValue(FOCUS_AREA.ID));
            return focusArea;
        } else {
            return null;
        }
    }

    @Override
    public boolean update(FocusAreaWrapper focusArea) {
        return dslContext.update(FOCUS_AREA)
                .set(FOCUS_AREA.NAME, focusArea.getName())
                .set(FOCUS_AREA.SORT_ORDER, focusArea.getSortOrder())
                .where(FOCUS_AREA.ID.eq(focusArea.getId()))
                .execute() > 0;
    }

    @Override
    public List<FocusAreaWrapper> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(FOCUS_AREA).getQuery();

        return query.fetchInto(FocusAreaWrapper.class);
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(FOCUS_AREA)
                .where(FOCUS_AREA.ID.eq(id))
                .execute();
    }
}
