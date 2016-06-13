package org.xcolab.service.members.service.membercategory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.members.domain.membercategory.MemberCategoryDao;

@Service
public class MemberCategoryService {

    private final MemberCategoryDao memberCategoryDao;

    @Autowired
    public MemberCategoryService(MemberCategoryDao memberCategoryDao) {
        this.memberCategoryDao = memberCategoryDao;
    }
}
