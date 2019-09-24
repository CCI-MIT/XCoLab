package org.xcolab.service.contest.domain.impactiteration;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IImpactIteration;
import org.xcolab.client.contest.pojo.tables.pojos.ImpactIteration;

import java.util.List;

import static org.xcolab.model.Tables.IMPACT_ITERATION;

@Repository
public class ImpactIterationDaoImpl implements ImpactIterationDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<IImpactIteration> findByGiven(Long iterationId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(IMPACT_ITERATION).getQuery();

        if (iterationId != null) {
            query.addConditions(IMPACT_ITERATION.ITERATION_ID.eq(iterationId));
        }
        return query.fetchInto(ImpactIteration.class);
    }
}
