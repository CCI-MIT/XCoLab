package org.xcolab.service.contest.domain.ontologyterm;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.model.tables.records.OntologyTermRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.FOCUS_AREA_ONTOLOGY_TERM;
import static org.xcolab.model.Tables.ONTOLOGY_SPACE;
import static org.xcolab.model.Tables.ONTOLOGY_TERM;

@Repository
public class OntologyTermDaoImpl implements OntologyTermDao {

    private final DSLContext dslContext;

    @Autowired
    public OntologyTermDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public OntologyTermWrapper get(Long id) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(ONTOLOGY_TERM)
                .where(ONTOLOGY_TERM.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("OntologyTerm with id " + id + " does not exist");
        }
        return record.into(OntologyTermWrapper.class);
    }

    @Override
    public List<OntologyTermWrapper> findByGiven(String name, Long parentId, Long ontologySpaceId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(ONTOLOGY_TERM).getQuery();

        if(name != null){
            query.addConditions(ONTOLOGY_TERM.NAME.eq(name));
        }
        if(parentId != null){
            query.addConditions(ONTOLOGY_TERM.PARENT_ID.eq(parentId));
        }
        if(ontologySpaceId != null){
            query.addConditions(ONTOLOGY_TERM.ONTOLOGY_SPACE_ID.eq(ontologySpaceId));
        }
        return query.fetchInto(OntologyTermWrapper.class);
    }

    @Override
    public boolean update(OntologyTermWrapper ontologyTerm) {
        return dslContext.update(ONTOLOGY_TERM)
                .set(ONTOLOGY_TERM.PARENT_ID, ontologyTerm.getParentId())
                .set(ONTOLOGY_TERM.ONTOLOGY_SPACE_ID, ontologyTerm.getOntologySpaceId())
                .set(ONTOLOGY_TERM.NAME, ontologyTerm.getName())
                .set(ONTOLOGY_TERM.DESCRIPTION_URL, ontologyTerm.getDescriptionUrl())
                .set(ONTOLOGY_TERM.SORT_ORDER, ontologyTerm.getSortOrder())
                .where(ONTOLOGY_TERM.ID.eq(ontologyTerm.getId()))
                .execute() > 0;
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(ONTOLOGY_TERM)
                .where(ONTOLOGY_TERM.ID.eq(id))
                .execute();
    }

    @Override
    public OntologyTermWrapper create(OntologyTermWrapper ontologyTerm) {
        OntologyTermRecord ret = this.dslContext.insertInto(ONTOLOGY_TERM)
                .set(ONTOLOGY_TERM.PARENT_ID, ontologyTerm.getParentId())
                .set(ONTOLOGY_TERM.ONTOLOGY_SPACE_ID, ontologyTerm.getOntologySpaceId())
                .set(ONTOLOGY_TERM.NAME, ontologyTerm.getName())
                .set(ONTOLOGY_TERM.DESCRIPTION_URL, ontologyTerm.getDescriptionUrl())
                .set(ONTOLOGY_TERM.SORT_ORDER, ontologyTerm.getSortOrder())
                .returning(ONTOLOGY_TERM.ID)
                .fetchOne();
        if (ret != null) {
            ontologyTerm.setId(ret.getValue(ONTOLOGY_TERM.ID));
            return ontologyTerm;
        } else {
            return null;
        }
    }

    @Override
    public List<OntologyTermWrapper> getOntologyTermByFocusAreaAndOntologySpaceName(Long focusArea,
            String ontologySpaceName) {

        final SelectQuery<Record> query = dslContext.select(ONTOLOGY_TERM.fields())
                .from(FOCUS_AREA_ONTOLOGY_TERM).getQuery();
        query.addJoin(ONTOLOGY_TERM,ONTOLOGY_TERM.ID.eq(FOCUS_AREA_ONTOLOGY_TERM.ONTOLOGY_TERM_ID));

        query.addJoin(ONTOLOGY_SPACE,ONTOLOGY_SPACE.ID.eq(ONTOLOGY_TERM.ONTOLOGY_SPACE_ID));

        query.addConditions(FOCUS_AREA_ONTOLOGY_TERM.FOCUS_AREA_ID.eq(focusArea));
        query.addConditions(ONTOLOGY_SPACE.NAME.eq(ontologySpaceName));

        return query.fetchInto(OntologyTermWrapper.class);
    }
}
