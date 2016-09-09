package org.xcolab.service.contest.domain.contesttype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContestType;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST_TYPE;

@Repository
public class ContestTypeDaoImpl implements ContestTypeDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestTypeDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public Optional<ContestType> get(Long id_) {
        final Record record = this.dslContext.selectFrom(CONTEST_TYPE)
                .where(CONTEST_TYPE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestType.class));
    }

    @Override
    public List<ContestType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_TYPE).getQuery();

        return query.fetchInto(ContestType.class);
    }

}
