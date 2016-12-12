package org.xcolab.service.contest.domain.focusarea;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.model.tables.records.FocusAreaRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.FOCUS_AREA;
import static org.xcolab.model.Tables.FOCUS_AREA_ONTOLOGY_TERM;

@Repository
public class FocusAreaDaoImpl implements FocusAreaDao {

    @Autowired
    private DSLContext dslContext;

    public FocusArea get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(FOCUS_AREA)
                .where(FOCUS_AREA.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("FocusArea with id " + id_ + " does not exist");
        }
        return record.into(FocusArea.class);

    }


    public FocusArea create(FocusArea focusArea) {

        FocusAreaRecord ret = this.dslContext.insertInto(FOCUS_AREA)
                .set(FOCUS_AREA.NAME, focusArea.getName())
                .set(FOCUS_AREA.ORDER_, focusArea.getOrder_())
                .returning(FOCUS_AREA.ID_)
                .fetchOne();
        if (ret != null) {
            focusArea.setId_(ret.getValue(FOCUS_AREA.ID_));
            return focusArea;
        } else {
            return null;
        }

    }

    public boolean update(FocusArea focusArea) {
        return dslContext.update(FOCUS_AREA)
                .set(FOCUS_AREA.NAME, focusArea.getName())
                .set(FOCUS_AREA.ORDER_, focusArea.getOrder_())
                .where(FOCUS_AREA.ID_.eq(focusArea.getId_()))
                .execute() > 0;
    }

    @Override
    public List<FocusArea> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(FOCUS_AREA).getQuery();


        return query.fetchInto(FocusArea.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(FOCUS_AREA)
                .where(FOCUS_AREA.ID_.eq(id_))
                .execute();
    }


}
