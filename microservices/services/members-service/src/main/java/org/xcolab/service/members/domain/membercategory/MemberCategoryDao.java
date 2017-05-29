package org.xcolab.service.members.domain.membercategory;

import org.xcolab.model.tables.pojos.MemberCategory;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface MemberCategoryDao {
    Optional<MemberCategory> getMemberCategory(Long roleId);

    List<MemberCategory> findByGiven(PaginationHelper paginationHelper, String displayName,
            String categoryName, Boolean showInList);
}
