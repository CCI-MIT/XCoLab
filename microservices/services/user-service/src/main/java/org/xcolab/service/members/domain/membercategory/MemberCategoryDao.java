package org.xcolab.service.members.domain.membercategory;


import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MemberCategoryDao {
    Optional<MemberCategoryWrapper> getMemberCategory(Long roleId);

    List<MemberCategoryWrapper> findByGiven(PaginationHelper paginationHelper, String displayName,
            String categoryName, Boolean showInList);
}
