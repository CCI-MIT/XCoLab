package org.xcolab.service.proposal.domain.points;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Points;

import java.util.List;

import static org.xcolab.model.Tables.POINTS;

@Repository
public class PointsDaoImpl implements PointsDao {

    private final DSLContext dslContext;

    @Autowired
    public PointsDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Points> findByGiven(Long userId, Long proposalId) {
        final SelectQuery<Record> query = dslContext.select().from(POINTS).getQuery();

        if (userId != null) {
            query.addConditions(POINTS.USER_ID.eq(userId));
        }
        if (proposalId != null) {
            query.addConditions(POINTS.PROPOSAL_ID.eq(proposalId));
        }

        return query.fetchInto(Points.class);
    }
}
