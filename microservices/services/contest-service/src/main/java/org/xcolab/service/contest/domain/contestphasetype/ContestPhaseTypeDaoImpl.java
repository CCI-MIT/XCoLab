package org.xcolab.service.contest.domain.contestphasetype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.records.ContestPhaseTypeRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_PHASE_TYPE;

@Repository
public class ContestPhaseTypeDaoImpl implements ContestPhaseTypeDao{

    @Autowired
    private DSLContext dslContext;

    @Override
    public IContestPhaseType create(IContestPhaseType contestPhaseType) {

        ContestPhaseTypeRecord ret = this.dslContext.insertInto(CONTEST_PHASE_TYPE)
                .set(CONTEST_PHASE_TYPE.NAME, contestPhaseType.getName())
                .set(CONTEST_PHASE_TYPE.DESCRIPTION, contestPhaseType.getDescription())
                .set(CONTEST_PHASE_TYPE.STATUS, contestPhaseType.getStatus())
                .set(CONTEST_PHASE_TYPE.FELLOW_SCREENING_ACTIVE_DEFAULT, contestPhaseType.isFellowScreeningActiveDefault())
                .set(CONTEST_PHASE_TYPE.CONTEST_PHASE_AUTOPROMOTE_DEFAULT, contestPhaseType.getContestPhaseAutopromoteDefault())
                .set(CONTEST_PHASE_TYPE.INVISIBLE, contestPhaseType.isInvisible())
                .set(CONTEST_PHASE_TYPE.POINTS_ACCESSIBLE, contestPhaseType.getPointsAccessible())
                .set(CONTEST_PHASE_TYPE.DEFAULT_PROMOTION_TYPE, contestPhaseType.getDefaultPromotionType())
                .set(CONTEST_PHASE_TYPE.DEFAULT_FLAG_TEXT, contestPhaseType.getDefaultFlagText())
                .returning(CONTEST_PHASE_TYPE.ID)
                .fetchOne();
        if (ret != null) {
            contestPhaseType.setId(ret.getValue(CONTEST_PHASE_TYPE.ID));
            return contestPhaseType;
        } else {
            return null;
        }

    }
    @Override
    public Optional<IContestPhaseType> get(Long id) {

        final Record record =  this.dslContext.selectFrom(CONTEST_PHASE_TYPE)
                .where(CONTEST_PHASE_TYPE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestPhaseType.class));
    }

    @Override
    public boolean update(IContestPhaseType contestPhaseType) {
        return dslContext.update(CONTEST_PHASE_TYPE)
                .set(CONTEST_PHASE_TYPE.NAME, contestPhaseType.getName())
                .set(CONTEST_PHASE_TYPE.DESCRIPTION, contestPhaseType.getDescription())
                .set(CONTEST_PHASE_TYPE.STATUS, contestPhaseType.getStatus())
                .set(CONTEST_PHASE_TYPE.FELLOW_SCREENING_ACTIVE_DEFAULT, contestPhaseType.isFellowScreeningActiveDefault())
                .set(CONTEST_PHASE_TYPE.CONTEST_PHASE_AUTOPROMOTE_DEFAULT, contestPhaseType.getContestPhaseAutopromoteDefault())
                .set(CONTEST_PHASE_TYPE.INVISIBLE, contestPhaseType.isInvisible())
                .set(CONTEST_PHASE_TYPE.POINTS_ACCESSIBLE, contestPhaseType.getPointsAccessible())
                .set(CONTEST_PHASE_TYPE.DEFAULT_PROMOTION_TYPE, contestPhaseType.getDefaultPromotionType())
                .set(CONTEST_PHASE_TYPE.DEFAULT_FLAG_TEXT, contestPhaseType.getDefaultFlagText())
                .where(CONTEST_PHASE_TYPE.ID.eq(contestPhaseType.getId()))
                .execute() > 0;
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(CONTEST_PHASE_TYPE)
                .where(CONTEST_PHASE_TYPE.ID.eq(id))
                .execute();
    }

    @Override
    public List<IContestPhaseType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE_TYPE).getQuery();

        return query.fetchInto(ContestPhaseType.class);
    }
}

