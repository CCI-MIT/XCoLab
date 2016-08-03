package org.xcolab.service.contest.domain.ontologyterm;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.exceptions.NotFoundException;

import static org.xcolab.model.Tables.ONTOLOGY_TERM;

@Repository
public class OntologyTermDaoImpl implements OntologyTermDao {

    @Autowired
    private DSLContext dslContext;

    public OntologyTerm get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(ONTOLOGY_TERM)
                .where(ONTOLOGY_TERM.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("OntologyTerm with id " + id_ + " does not exist");
        }
        return record.into(OntologyTerm.class);

    }
}
