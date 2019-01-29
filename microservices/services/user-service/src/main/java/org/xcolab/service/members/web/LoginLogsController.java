package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.client.user.pojo.tables.pojos.LoginLog;
import org.xcolab.client.user.ILoginLogClient;
import org.xcolab.service.members.service.member.UserService;

@RestController
public class LoginLogsController implements ILoginLogClient {

    private final UserService memberService;

    @Autowired
    public LoginLogsController(UserService memberService) {
        Assert.notNull(memberService, "UserService bean is required");
        this.memberService = memberService;
    }

    @PostMapping
    public LoginLog createLoginLog(@RequestBody
            LoginLog loginLog) {
        return memberService.createLoginLog(loginLog.getUserId(), loginLog.getIpAddress(),
                loginLog.getEntryUrl());
    }
}
