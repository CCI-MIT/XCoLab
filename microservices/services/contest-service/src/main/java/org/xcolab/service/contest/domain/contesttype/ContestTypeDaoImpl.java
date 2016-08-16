package org.xcolab.service.contest.domain.contesttype;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_TYPE;

@Repository
public class ContestTypeDaoImpl implements ContestTypeDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public ContestType get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_TYPE)
                .where(CONTEST_TYPE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestType with id " + id_ + " does not exist");
        }
        return record.into(ContestType.class);

    }

    @Override
    public List<ContestType> findByGiven() {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_TYPE).getQuery();

        return query.fetchInto(ContestType.class);
    }


}
