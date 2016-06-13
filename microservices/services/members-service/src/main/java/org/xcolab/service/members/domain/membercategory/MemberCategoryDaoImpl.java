package org.xcolab.service.members.domain.membercategory;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.MemberCategory;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.MEMBER_CATEGORY;

@Repository
public class MemberCategoryDaoImpl implements MemberCategoryDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public MemberCategory getMemberCategory(Long roleId) {
        return this.dslContext.select()
                .from(MEMBER_CATEGORY)
                .where(MEMBER_CATEGORY.ROLE_ID.equal(roleId)).fetchAny().into(MemberCategory.class);
    }

    @Override
    public List<MemberCategory> findByGiven(PaginationHelper paginationHelper,
            String displayName, String categoryName) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MEMBER_CATEGORY)
                .getQuery();

        if (displayName != null) {
            query.addConditions(MEMBER_CATEGORY.DISPLAY_NAME.eq(displayName));
        }

        if (categoryName != null) {
            query.addConditions(MEMBER_CATEGORY.CATEGORY_NAME.eq(categoryName));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                default:
            }
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
        return query.fetchInto(MemberCategory.class);
    }
}
