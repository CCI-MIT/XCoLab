package org.xcolab.service.contest.domain.focusareaontologyterm;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;

import java.util.List;

import static org.xcolab.model.Tables.FOCUS_AREA_ONTOLOGY_TERM;

@Repository
public class FocusAreaOntologyTermDaoImpl implements FocusAreaOntologyTermDao {

    @Autowired
    private DSLContext dslContext;

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
}
