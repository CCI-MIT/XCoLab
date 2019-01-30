package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.user.pojo.tables.pojos.LoginLog;



@FeignClient("xcolab-user-service")
@RequestMapping("/loginLogs")
public interface ILoginLogClient {

    @PostMapping
    LoginLog createLoginLog(@RequestBody LoginLog loginLog);


    //TODO COLAB-2594: this shouldn't be done manually
    default LoginLog createLoginLog(long userId, String ipAddress, String redirectUrl) {
        org.xcolab.client.user.pojo.tables.pojos.LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setIpAddress(ipAddress);
        loginLog.setEntryUrl(redirectUrl != null
                ? redirectUrl.substring(0, Math.min(250, redirectUrl.length()))
                : "");

        return createLoginLog(loginLog);
    }
}
