package org.xcolab.service.members.domain.staffmember;

import org.xcolab.client.user.pojo.wrapper.StaffUserWrapper;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface StaffMemberDao {

    List<StaffUserWrapper> findByGiven(PaginationHelper paginationHelper, Long categoryId);
}
