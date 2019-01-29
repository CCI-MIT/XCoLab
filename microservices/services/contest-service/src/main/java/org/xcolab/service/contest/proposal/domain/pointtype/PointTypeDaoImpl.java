package org.xcolab.service.contest.proposal.domain.pointtype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.model.tables.records.PointTypeRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.POINT_TYPE;

@Repository
public class PointTypeDaoImpl implements PointTypeDao {

    private final DSLContext dslContext;

    @Autowired
    public PointTypeDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public PointTypeWrapper create(PointTypeWrapper pointType) {
        PointTypeRecord ret = this.dslContext.insertInto(POINT_TYPE)
                .set(POINT_TYPE.ID, pointType.getId())
                .set(POINT_TYPE.PARENT_POINT_TYPE_ID, pointType.getParentPointTypeId())
                .set(POINT_TYPE.PERCENTAGE_OF_PARENT, pointType.getPercentageOfParent())
                .set(POINT_TYPE.DISTRIBUTION_STRATEGY, pointType.getDistributionStrategy())
                .set(POINT_TYPE.RECEIVER_LIMITATION_STRATEGY, pointType.getReceiverLimitationStrategy())
                .set(POINT_TYPE.NAME, pointType.getName())
                .set(POINT_TYPE.SORT_ORDER, pointType.getSortOrder())
                .returning(POINT_TYPE.ID)
                .fetchOne();
        if (ret != null) {
            pointType.setId(ret.getValue(POINT_TYPE.ID));
            return pointType;
        } else {
            return null;
        }

    }

    @Override
    public PointTypeWrapper get(Long id) throws NotFoundException {
        final Record record =  this.dslContext.selectFrom(POINT_TYPE)
                .where(POINT_TYPE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("PointType with id " + id + " does not exist");
        }
        return record.into(PointTypeWrapper.class);
    }

    @Override
    public boolean update(PointTypeWrapper pointType) {
        return dslContext.update(POINT_TYPE)
                .set(POINT_TYPE.ID, pointType.getId())
                .set(POINT_TYPE.PARENT_POINT_TYPE_ID, pointType.getParentPointTypeId())
                .set(POINT_TYPE.PERCENTAGE_OF_PARENT, pointType.getPercentageOfParent())
                .set(POINT_TYPE.DISTRIBUTION_STRATEGY, pointType.getDistributionStrategy())
                .set(POINT_TYPE.RECEIVER_LIMITATION_STRATEGY, pointType.getReceiverLimitationStrategy())
                .set(POINT_TYPE.NAME, pointType.getName())
                .set(POINT_TYPE.SORT_ORDER, pointType.getSortOrder())
                .where(POINT_TYPE.ID.eq(pointType.getId()))
                .execute() > 0;
    }

    @Override
    public List<PointTypeWrapper> findByGiven(Long parentPointTypeId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(POINT_TYPE).getQuery();

        if (parentPointTypeId != null) {
            query.addConditions(POINT_TYPE.PARENT_POINT_TYPE_ID.eq(parentPointTypeId));
        }
        query.addOrderBy(POINT_TYPE.SORT_ORDER.asc());

        return query.fetchInto(PointTypeWrapper.class);
    }
}
