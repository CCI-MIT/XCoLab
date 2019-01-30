package org.xcolab.service.members.domain.membercategory;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.commons.SortColumn;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MEMBER_CATEGORY;

@Repository
public class MemberCategoryDaoImpl implements MemberCategoryDao {

    private final DSLContext dslContext;

    @Autowired
    public MemberCategoryDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<MemberCategoryWrapper> getMemberCategory(Long roleId) {
        final Record record = this.dslContext.select().from(MEMBER_CATEGORY)
                .where(MEMBER_CATEGORY.ROLE_ID.equal(roleId)).fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(MemberCategoryWrapper.class));
    }

    @Override
    public List<MemberCategoryWrapper> findByGiven(PaginationHelper paginationHelper,
            String displayName, String categoryName, Boolean showInList) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBER_CATEGORY)
                .getQuery();

        if (displayName != null) {
            query.addConditions(MEMBER_CATEGORY.DISPLAY_NAME.eq(displayName));
        }

        if (categoryName != null) {
            query.addConditions(MEMBER_CATEGORY.CATEGORY_NAME.eq(categoryName));
        }

        if (showInList != null) {
            query.addConditions(MEMBER_CATEGORY.SHOW_IN_LIST.eq(showInList));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(MemberCategoryWrapper.class);
    }
}
