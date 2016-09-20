package org.xcolab.service.contest.domain.ontologyspace;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.OntologySpace;
import org.xcolab.model.tables.records.OntologySpaceRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;


import java.util.List;

import static org.xcolab.model.Tables.ONTOLOGY_SPACE;

@Repository
public class OntologySpaceDaoImpl implements OntologySpaceDao {

    @Autowired
    private DSLContext dslContext;


    /*
    OntologySpaceLocalServiceUtil.getOntologySpace(OntologySpaceEnum.WHERE.getSpaceId());
	OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
	OntologySpaceLocalServiceUtil.deleteOntologySpace(spaceId);
	OntologySpaceLocalServiceUtil.createSpace(newSpace, newSpaceDescription);
	LIST
	OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE)
	OntologySpaceLocalServiceUtil.getTopTerms(space)
	
    *
    * */

    public OntologySpace create(OntologySpace ontologySpace) {

        OntologySpaceRecord ret = this.dslContext.insertInto(ONTOLOGY_SPACE)
                .set(ONTOLOGY_SPACE.ID_, ontologySpace.getId_())
                .set(ONTOLOGY_SPACE.NAME, ontologySpace.getName())
                .set(ONTOLOGY_SPACE.DESCRIPTION, ontologySpace.getDescription())
                .set(ONTOLOGY_SPACE.ORDER_, ontologySpace.getOrder_())
                .returning(ONTOLOGY_SPACE.ID_)
                .fetchOne();
        if (ret != null) {
            ontologySpace.setId_(ret.getValue(ONTOLOGY_SPACE.ID_));
            return ontologySpace;
        } else {
            return null;
        }

    }

    public OntologySpace get(Long id_) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(ONTOLOGY_SPACE)
                .where(ONTOLOGY_SPACE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("OntologySpace with id " + id_ + " does not exist");
        }
        return record.into(OntologySpace.class);

    }

    public boolean update(OntologySpace ontologySpace) {
        return dslContext.update(ONTOLOGY_SPACE)
                .set(ONTOLOGY_SPACE.ID_, ontologySpace.getId_())
                .set(ONTOLOGY_SPACE.NAME, ontologySpace.getName())
                .set(ONTOLOGY_SPACE.DESCRIPTION, ontologySpace.getDescription())
                .set(ONTOLOGY_SPACE.ORDER_, ontologySpace.getOrder_())
                .where(ONTOLOGY_SPACE.ID_.eq(ontologySpace.getId_()))
                .execute() > 0;
    }

    @Override
    public List<OntologySpace> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(ONTOLOGY_SPACE).getQuery();


        return query.fetchInto(OntologySpace.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(ONTOLOGY_SPACE)
                .where(ONTOLOGY_SPACE.ID_.eq(id_))
                .execute();
    }
}
