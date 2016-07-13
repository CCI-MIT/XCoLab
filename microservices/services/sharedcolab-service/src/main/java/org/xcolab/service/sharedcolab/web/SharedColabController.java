package org.xcolab.service.sharedcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.SharedMember;
import org.xcolab.service.sharedcolab.domain.sharedMember.SharedMemberDao;

import java.sql.Timestamp;
import java.util.Date;


@RestController
public class SharedColabController {


    @Autowired
    private SharedMemberDao sharedMemberDao;


    @RequestMapping(value = "/members/isUsed", method = RequestMethod.GET)
    public boolean isUsed(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String email) {
        boolean ret = false;
        if (screenName != null) {
            ret = sharedMemberDao.isScreenNameTaken(screenName);
        }
        if (email != null) {
            ret = ret || sharedMemberDao.isEmailUsed(email);
        }
        return ret;
    }

    @RequestMapping(value = "/members/retrieveSharedId", method = RequestMethod.POST)
    public Long retrieveSharedId(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String colabOrigin) {
        SharedMember member = sharedMemberDao.getByScreenNameAndEmail(screenName, email);
        if (member != null) {
            return member.getSharedMemberId();
        } else {
            Long ret = sharedMemberDao.create(screenName, email,colabOrigin,  new Timestamp(new Date().getTime()));
            return ret;
        }
    }
}
