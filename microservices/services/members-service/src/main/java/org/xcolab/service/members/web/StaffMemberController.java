package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.StaffMember;
import org.xcolab.service.members.domain.staffmember.StaffMemberDao;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class StaffMemberController {

    private final StaffMemberDao staffUserDao;

    @Autowired
    public StaffMemberController(StaffMemberDao staffUserDao) {
        this.staffUserDao = staffUserDao;
    }

    @RequestMapping(value = "staffUsers", method = RequestMethod.GET)
    public List<StaffMember> listStaffMembers(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long categoryId) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return staffUserDao.findByGiven(paginationHelper, categoryId);
    }
}
