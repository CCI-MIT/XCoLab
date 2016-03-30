package org.xcolab.domain.membercategory;

import org.xcolab.model.tables.pojos.MemberCategory;

public interface MemberCategoryDao {

    public MemberCategory getMemberCategory(Long roleId);
}
