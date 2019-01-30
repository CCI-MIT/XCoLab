package org.xcolab.service.members.domain.staffmember;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.StaffUserWrapper;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import static org.xcolab.model.Tables.STAFF_MEMBER;

@Repository
public class StaffMemberDaoImpl implements StaffMemberDao {

    private final DSLContext dslContext;

    @Autowired
    public StaffMemberDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<StaffUserWrapper> findByGiven(PaginationHelper paginationHelper, Long categoryId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(STAFF_MEMBER)
                .getQuery();

        if (categoryId != null) {
            query.addConditions(STAFF_MEMBER.CATEGORY_ID.eq(categoryId));
        }

        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(StaffUserWrapper.class);
    }
}
