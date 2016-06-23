package org.xcolab.service.contest.domain.contest;

import static org.xcolab.model.Tables.CONTEST;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@Repository
public class ContestDaoImpl implements ContestDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public Contest get(Long contestId) throws NotFoundException {
        final Record record = dslContext.select()
                .from(CONTEST)
                .where(CONTEST.CONTEST_PK.eq(contestId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException("Contest with id " + contestId + " was not found");
        }
        return record.into(Contest.class);
    }

    @Override
    public List<Contest> findByGiven(String contestUrlName, Long contestYear) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST).getQuery();

        if (contestUrlName != null) {
            query.addConditions(CONTEST.CONTEST_URL_NAME.eq(contestUrlName));
        }
        if (contestYear != null) {
            query.addConditions(CONTEST.CONTEST_YEAR.eq(contestYear));
        }
        query.addOrderBy(CONTEST.CREATED.desc());
        return query.fetchInto(Contest.class);
    }
}
