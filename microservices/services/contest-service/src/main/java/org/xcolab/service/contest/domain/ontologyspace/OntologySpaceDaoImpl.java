package org.xcolab.service.contest.domain.ontologyspace;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.model.tables.records.OntologySpaceRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.ONTOLOGY_SPACE;

@Repository
public class OntologySpaceDaoImpl implements OntologySpaceDao {

    private final DSLContext dslContext;

    @Autowired
    public OntologySpaceDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public OntologySpaceWrapper create(OntologySpaceWrapper ontologySpace) {
        OntologySpaceRecord ret = this.dslContext.insertInto(ONTOLOGY_SPACE)
                .set(ONTOLOGY_SPACE.ID, ontologySpace.getId())
                .set(ONTOLOGY_SPACE.NAME, ontologySpace.getName())
                .set(ONTOLOGY_SPACE.DESCRIPTION, ontologySpace.getDescription())
                .set(ONTOLOGY_SPACE.SORT_ORDER, ontologySpace.getSortOrder())
                .returning(ONTOLOGY_SPACE.ID)
                .fetchOne();
        if (ret != null) {
            ontologySpace.setId(ret.getValue(ONTOLOGY_SPACE.ID));
            return ontologySpace;
        } else {
            return null;
        }
    }

    @Override
    public OntologySpaceWrapper get(Long id) throws NotFoundException {
        final Record record =  this.dslContext.selectFrom(ONTOLOGY_SPACE)
                .where(ONTOLOGY_SPACE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("OntologySpace with id " + id + " does not exist");
        }
        return record.into(OntologySpaceWrapper.class);
    }

    @Override
    public boolean update(OntologySpaceWrapper ontologySpace) {
        return dslContext.update(ONTOLOGY_SPACE)
                .set(ONTOLOGY_SPACE.ID, ontologySpace.getId())
                .set(ONTOLOGY_SPACE.NAME, ontologySpace.getName())
                .set(ONTOLOGY_SPACE.DESCRIPTION, ontologySpace.getDescription())
                .set(ONTOLOGY_SPACE.SORT_ORDER, ontologySpace.getSortOrder())
                .where(ONTOLOGY_SPACE.ID.eq(ontologySpace.getId()))
                .execute() > 0;
    }

    @Override
    public List<OntologySpaceWrapper> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(ONTOLOGY_SPACE).getQuery();

        return query.fetchInto(OntologySpaceWrapper.class);
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(ONTOLOGY_SPACE)
                .where(ONTOLOGY_SPACE.ID.eq(id))
                .execute();
    }
}
