package org.xcolab.service.contest.domain.contestphaseribbontype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_PHASE_RIBBON_TYPE;

@Repository
public class ContestPhaseRibbonTypeDaoImpl implements ContestPhaseRibbonTypeDao{

    @Autowired
    private DSLContext dslContext;

    public ContestPhaseRibbonType get(Long id_) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(CONTEST_PHASE_RIBBON_TYPE)
                .where(CONTEST_PHASE_RIBBON_TYPE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestPhaseRibbonType with id " + id_ + " does not exist");
        }
        return record.into(ContestPhaseRibbonType.class);

    }

    @Override
    public List<ContestPhaseRibbonType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE_RIBBON_TYPE).getQuery();

        return query.fetchInto(ContestPhaseRibbonType.class);
    }


}
