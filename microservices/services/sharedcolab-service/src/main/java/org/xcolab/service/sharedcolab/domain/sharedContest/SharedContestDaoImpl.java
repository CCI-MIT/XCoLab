package org.xcolab.service.sharedcolab.domain.sharedContest;

import com.sun.jersey.api.NotFoundException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.SharedContest;
import org.xcolab.model.tables.records.SharedContestRecord;

import java.util.List;

import static org.xcolab.model.Tables.SHARED_CONTEST;

@Repository
public class SharedContestDaoImpl implements SharedContestDao {

    private final DSLContext dslContext;

    @Autowired
    public SharedContestDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }


    public SharedContest create(SharedContest sharedContest) {

        SharedContestRecord ret = this.dslContext.insertInto(SHARED_CONTEST)
                .set(SHARED_CONTEST.SHARED_CONTEST_ID, sharedContest.getSharedContestId())
                .set(SHARED_CONTEST.CREATE_DATE, sharedContest.getCreateDate())
                .set(SHARED_CONTEST.CONTEST_NAME, sharedContest.getContestName())
                .set(SHARED_CONTEST.COLAB_ORIGIN, sharedContest.getColabOrigin())
                .returning(SHARED_CONTEST.SHARED_CONTEST_ID)
                .fetchOne();
        if (ret != null) {
            sharedContest.setSharedContestId(ret.getValue(SHARED_CONTEST.SHARED_CONTEST_ID));
            return sharedContest;
        } else {
            return null;
        }

    }

    @Override
    public List<SharedContest> findByGiven(String colabOrigin) {
        final SelectQuery<Record> query = dslContext.select()
                .from(SHARED_CONTEST).getQuery();

        if (colabOrigin != null) {
            query.addConditions(SHARED_CONTEST.COLAB_ORIGIN.ne(colabOrigin));
        }
        return query.fetchInto(SharedContest.class);
    }


    public boolean update(SharedContest sharedContest) {
        return dslContext.update(SHARED_CONTEST)
                .set(SHARED_CONTEST.CONTEST_NAME, sharedContest.getContestName())
                .where(SHARED_CONTEST.SHARED_CONTEST_ID.eq(sharedContest.getSharedContestId()))
                .execute() > 0;
    }

    public SharedContest get(Long sharedContestId) throws NotFoundException {

        final Record record =  this.dslContext.selectFrom(SHARED_CONTEST)
                .where(SHARED_CONTEST.SHARED_CONTEST_ID.eq(sharedContestId))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("SharedContest with id " + sharedContestId + " does not exist");
        }
        return record.into(SharedContest.class);

    }
}
