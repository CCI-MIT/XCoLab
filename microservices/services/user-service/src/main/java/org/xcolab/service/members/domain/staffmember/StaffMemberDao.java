package org.xcolab.service.members.domain.staffmember;

import org.xcolab.client.user.pojo.IStaffMember;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface StaffMemberDao {

    List<IStaffMember> findByGiven(PaginationHelper paginationHelper, Long categoryId);
}
