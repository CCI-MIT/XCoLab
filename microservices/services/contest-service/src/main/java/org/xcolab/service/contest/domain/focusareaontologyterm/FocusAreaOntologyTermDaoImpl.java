package org.xcolab.service.contest.domain.focusareaontologyterm;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.tables.pojos.FocusAreaOntologyTerm;

import java.util.List;

import static org.xcolab.model.Tables.FOCUS_AREA_ONTOLOGY_TERM;

@Repository
public class FocusAreaOntologyTermDaoImpl implements FocusAreaOntologyTermDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<IFocusAreaOntologyTerm> findByGiven(Long focusAreaId, Long ontologyTermId) {
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
    public IFocusAreaOntologyTerm create(IFocusAreaOntologyTerm focusAreaOntologyTerm) {

        this.dslContext.insertInto(FOCUS_AREA_ONTOLOGY_TERM)
                .set(FOCUS_AREA_ONTOLOGY_TERM.FOCUS_AREA_ID, focusAreaOntologyTerm.getFocusAreaId())
                .set(FOCUS_AREA_ONTOLOGY_TERM.ONTOLOGY_TERM_ID, focusAreaOntologyTerm.getOntologyTermId())
                .set(FOCUS_AREA_ONTOLOGY_TERM.SORT_ORDER, focusAreaOntologyTerm.getSortOrder())
                .execute();

            return focusAreaOntologyTerm;
    }

    @Override
    public List<IFocusAreaOntologyTerm> findByOntologyTermIds(List<Long> ontologyTermId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(FOCUS_AREA_ONTOLOGY_TERM).getQuery();


        if (ontologyTermId != null) {
            query.addConditions(FOCUS_AREA_ONTOLOGY_TERM.ONTOLOGY_TERM_ID.in(ontologyTermId));
        }
        return query.fetchInto(FocusAreaOntologyTerm.class);
    }

    @Override
    public int deleteAllFocusAreasOntologyTerms(Long focusAreaId, Long ontologyTermId) {
        if(ontologyTermId!=null){
            return dslContext.deleteFrom(FOCUS_AREA_ONTOLOGY_TERM)
                    .where(FOCUS_AREA_ONTOLOGY_TERM.FOCUS_AREA_ID.eq(focusAreaId))
                    .and(FOCUS_AREA_ONTOLOGY_TERM.ONTOLOGY_TERM_ID.eq(ontologyTermId))
                    .execute();
        }else{
            return dslContext.deleteFrom(FOCUS_AREA_ONTOLOGY_TERM)
                    .where(FOCUS_AREA_ONTOLOGY_TERM.FOCUS_AREA_ID.eq(focusAreaId))
                    .execute();
        }
    }
}
