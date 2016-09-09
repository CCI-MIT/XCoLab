package org.xcolab.service.contest.domain.contestphasetype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.ContestPhaseType;
import org.xcolab.model.tables.records.ContestPhaseTypeRecord;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_PHASE_TYPE;

@Repository
public class ContestPhaseTypeDaoImpl implements ContestPhaseTypeDao{

    @Autowired
    private DSLContext dslContext;

    @Override
    public ContestPhaseType create(ContestPhaseType contestPhaseType) {

        ContestPhaseTypeRecord ret = this.dslContext.insertInto(CONTEST_PHASE_TYPE)
                .set(CONTEST_PHASE_TYPE.NAME, contestPhaseType.getName())
                .set(CONTEST_PHASE_TYPE.DESCRIPTION, contestPhaseType.getDescription())
                .set(CONTEST_PHASE_TYPE.STATUS, contestPhaseType.getStatus())
                .set(CONTEST_PHASE_TYPE.FELLOW_SCREENING_ACTIVE_DEFAULT, contestPhaseType.getFellowScreeningActiveDefault())
                .set(CONTEST_PHASE_TYPE.CONTEST_PHASE_AUTOPROMOTE_DEFAULT, contestPhaseType.getContestPhaseAutopromoteDefault())
                .set(CONTEST_PHASE_TYPE.INVISIBLE, contestPhaseType.getInvisible())
                .set(CONTEST_PHASE_TYPE.POINTS_ACCESSIBLE, contestPhaseType.getPointsAccessible())
                .set(CONTEST_PHASE_TYPE.DEFAULT_PROMOTION_TYPE, contestPhaseType.getDefaultPromotionType())
                .returning(CONTEST_PHASE_TYPE.ID_)
                .fetchOne();
        if (ret != null) {
            contestPhaseType.setId_(ret.getValue(CONTEST_PHASE_TYPE.ID_));
            return contestPhaseType;
        } else {
            return null;
        }

    }
    @Override
    public Optional<ContestPhaseType> get(Long id_) {

        final Record record =  this.dslContext.selectFrom(CONTEST_PHASE_TYPE)
                .where(CONTEST_PHASE_TYPE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestPhaseType.class));
    }

    @Override
    public boolean update(ContestPhaseType contestPhaseType) {
        return dslContext.update(CONTEST_PHASE_TYPE)
                .set(CONTEST_PHASE_TYPE.NAME, contestPhaseType.getName())
                .set(CONTEST_PHASE_TYPE.DESCRIPTION, contestPhaseType.getDescription())
                .set(CONTEST_PHASE_TYPE.STATUS, contestPhaseType.getStatus())
                .set(CONTEST_PHASE_TYPE.FELLOW_SCREENING_ACTIVE_DEFAULT, contestPhaseType.getFellowScreeningActiveDefault())
                .set(CONTEST_PHASE_TYPE.CONTEST_PHASE_AUTOPROMOTE_DEFAULT, contestPhaseType.getContestPhaseAutopromoteDefault())
                .set(CONTEST_PHASE_TYPE.INVISIBLE, contestPhaseType.getInvisible())
                .set(CONTEST_PHASE_TYPE.POINTS_ACCESSIBLE, contestPhaseType.getPointsAccessible())
                .set(CONTEST_PHASE_TYPE.DEFAULT_PROMOTION_TYPE, contestPhaseType.getDefaultPromotionType())
                .where(CONTEST_PHASE_TYPE.ID_.eq(contestPhaseType.getId_()))
                .execute() > 0;
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(CONTEST_PHASE_TYPE)
                .where(CONTEST_PHASE_TYPE.ID_.eq(id_))
                .execute();
    }

    @Override
    public List<ContestPhaseType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_PHASE_TYPE).getQuery();

        return query.fetchInto(ContestPhaseType.class);
    }
}

