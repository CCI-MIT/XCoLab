package org.xcolab.service.members.domain.membercategory;

import org.xcolab.model.tables.pojos.MemberCategory;

public interface MemberCategoryDao {
    MemberCategory getMemberCategory(Long roleId);
}
