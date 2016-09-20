package org.xcolab.service.contest.domain.focusarea;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
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

    @Override
    public List<FocusAreaOntologyTerm> findByGiven(Long focusAreaId, Long ontologyTermId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(FOCUS_AREA_ONTOLOGY_TERM).getQuery();

        if (focusAreaId != null) {
            query.addConditions(FOCUS_AREA_ONTOLOGY_TERM.FOCUS_AREA_ID.eq(focusAreaId));
        }
        if (ontologyTermId != null) {
            query.addConditions(FOCUS_AREA_ONTOLOGY_TERM.ONTOLOGY_TERM_ID.eq(ontologyTermId));
        }
        return query.fetchInto(FocusAreaOntologyTerm.class);
    }

    @Override
    public List<FocusArea> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(FOCUS_AREA).getQuery();


        return query.fetchInto(FocusArea.class);
    }
}
