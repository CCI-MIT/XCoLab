package org.xcolab.service.members.domain.staffmember;

import org.xcolab.model.tables.pojos.StaffMember;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface StaffMemberDao {

    List<StaffMember> findByGiven(PaginationHelper paginationHelper, Long categoryId);
}
