package org.xcolab.domain.membercategory;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.MemberCategory;

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
}
