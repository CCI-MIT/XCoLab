package org.xcolab.service.contest.domain.contestphaseribbontype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.tables.pojos.ContestPhaseRibbonType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_PHASE_RIBBON_TYPE;

@Repository
public class ContestPhaseRibbonTypeDaoImpl implements ContestPhaseRibbonTypeDao{

    private final DSLContext dslContext;

    @Autowired
    public ContestPhaseRibbonTypeDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public Optional<IContestPhaseRibbonType> get(Long id) throws NotFoundException {

        final Record record =  dslContext.selectFrom(CONTEST_PHASE_RIBBON_TYPE)
                .where(CONTEST_PHASE_RIBBON_TYPE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestPhaseRibbonType.class));
    }

    @Override
    public List<IContestPhaseRibbonType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE_RIBBON_TYPE).getQuery();

        return query.fetchInto(ContestPhaseRibbonType.class);
    }


}
