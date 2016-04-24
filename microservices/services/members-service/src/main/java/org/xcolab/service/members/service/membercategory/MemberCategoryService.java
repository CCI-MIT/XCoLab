package org.xcolab.service.members.service.membercategory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.service.members.domain.membercategory.MemberCategoryDao;
import org.xcolab.model.tables.pojos.MemberCategory;

@Service
public class MemberCategoryService {

    private final MemberCategoryDao memberCategoryDao;

    @Autowired
    public MemberCategoryService(MemberCategoryDao memberCategoryDao) {
        this.memberCategoryDao = memberCategoryDao;
    }

    public MemberCategory getMemberCategory(Long roleId) {
        return this.memberCategoryDao.getMemberCategory(roleId);
    }
}
