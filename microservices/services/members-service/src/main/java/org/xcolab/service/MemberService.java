package org.xcolab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.MemberDao;
import org.xcolab.model.tables.pojos.User_;

import java.util.List;

@Service
public class MemberService {


    private final MemberDao memberDao;

    @Autowired
    public MemberService(MemberDao memberDao){

        this.memberDao = memberDao;
    }


    public List<User_> listAll(){
        return this.memberDao.listAllMembersSortByRoleName(0,10,"",false);
        //hard coded for testing
    }

}
