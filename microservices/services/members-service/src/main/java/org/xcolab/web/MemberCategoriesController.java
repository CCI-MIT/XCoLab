package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.MemberCategory;
import org.xcolab.service.membercategory.MemberCategoryService;

@RestController
public class MemberCategoriesController {

    @Autowired
    private MemberCategoryService memberCategoryService;

    @RequestMapping(value = "/membercategories/{roleId}", method = RequestMethod.GET)
    public MemberCategory getMemberCategory(@PathVariable("roleId") Long roleId) {
        return this.memberCategoryService.getMemberCategory(roleId);
    }
}
