package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.service.MemberService;

import java.util.List;

@RestController
public class MembersController {

    @Autowired
    private MemberService memberService;


	@RequestMapping("/members")
    public List<User_> index() {
        return memberService.listAll();
    }

}
