package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IUserCategoryClient;
import org.xcolab.client.user.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.user.pojo.MemberCategory;
import org.xcolab.service.members.domain.membercategory.MemberCategoryDao;
import org.xcolab.service.utils.PaginationHelper;


import java.util.List;

@RestController
public class MemberCategoriesController implements IUserCategoryClient {

    private final MemberCategoryDao memberCategoryDao;

    @Autowired
    public MemberCategoriesController(MemberCategoryDao memberCategoryDao) {
        this.memberCategoryDao = memberCategoryDao;
    }


    @RequestMapping(value = "/membercategories/{roleId}", method = RequestMethod.GET)
    public MemberCategory getMemberCategory(@PathVariable long roleId) throws MemberCategoryNotFoundException {
        return memberCategoryDao.getMemberCategory(roleId).orElseThrow(
                MemberCategoryNotFoundException::new);
    }

    @RequestMapping(value = "/membercategories", method = RequestMethod.GET)
    public List<MemberCategory> getMemberCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean showInList) {
        PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord, sort);
        return memberCategoryDao.findByGiven(paginationHelper, displayName, categoryName, showInList);
    }
}
