package org.xcolab.service.members.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.ISsoClientDetailsClient;
import org.xcolab.client.user.pojo.ISsoClientDetails;
import org.xcolab.client.user.pojo.SsoClientDetails;
import org.xcolab.service.members.domain.ssoclientdetails.SsoClientDetailsDao;

@RestController
public class SsoClientDetailsController implements ISsoClientDetailsClient {

    private final SsoClientDetailsDao ssoClientDetailsDao;

    public SsoClientDetailsController(SsoClientDetailsDao ssoClientDetailsDao) {
        this.ssoClientDetailsDao = ssoClientDetailsDao;
    }

    @Override
    @GetMapping("/ssoClientDetails/{clientId}")
    public SsoClientDetails getSsoClientDetails(@PathVariable String clientId) {
        return ssoClientDetailsDao.get(clientId);
    }
}
