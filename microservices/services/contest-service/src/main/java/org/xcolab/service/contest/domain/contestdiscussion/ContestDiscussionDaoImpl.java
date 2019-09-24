package org.xcolab.service.contest.domain.contestdiscussion;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.client.contest.pojo.tables.pojos.ContestDiscussion;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_DISCUSSION;

@Repository
public class ContestDiscussionDaoImpl implements ContestDiscussionDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestDiscussionDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public IContestDiscussion create(IContestDiscussion contest) {
        this.dslContext.insertInto(CONTEST_DISCUSSION)
                .set(CONTEST_DISCUSSION.ID, contest.getId())
                .set(CONTEST_DISCUSSION.CONTEST_ID, contest.getContestId())
                .execute();
        return contest;
    }

    @Override
    public boolean update(IContestDiscussion contest) {
        return dslContext.update(CONTEST_DISCUSSION)
                .set(CONTEST_DISCUSSION.CONTEST_ID, contest.getContestId())
                .set(CONTEST_DISCUSSION.TAB, contest.getTab())
                .where(CONTEST_DISCUSSION.ID.eq(contest.getId()))
                .execute() > 0;
    }

    @Override
    public Optional<IContestDiscussion> get(Long contestDiscussion) throws NotFoundException {
        final Record record = this.dslContext.selectFrom(CONTEST_DISCUSSION)
                .where(CONTEST_DISCUSSION.ID.eq(contestDiscussion))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestDiscussion.class));
    }

    @Override
    public List<IContestDiscussion> findByGiven(PaginationHelper paginationHelper, Long contestId,
            String tab) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_DISCUSSION).getQuery();

        return query.fetchInto(ContestDiscussion.class);
    }
}
