package org.xcolab.service.members.domain.membercategory;

import org.xcolab.client.user.pojo.IMemberCategory;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MemberCategoryDao {
    Optional<IMemberCategory> getMemberCategory(Long roleId);

    List<IMemberCategory> findByGiven(PaginationHelper paginationHelper, String displayName,
            String categoryName, Boolean showInList);
}
