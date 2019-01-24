package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.pojo.IStaffMember;
import org.xcolab.service.members.domain.staffmember.StaffMemberDao;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class StaffMemberController {

    private final StaffMemberDao staffMemberDao;

    @Autowired
    public StaffMemberController(StaffMemberDao staffMemberDao) {
        this.staffMemberDao = staffMemberDao;
    }

    @GetMapping("staffMembers")
    public List<IStaffMember> listStaffMembers(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long categoryId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return staffMemberDao.findByGiven(paginationHelper, categoryId);
    }
}
