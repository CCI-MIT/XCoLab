package org.xcolab.service.members.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.pojo.ISsoClientDetails;
import org.xcolab.service.members.domain.ssoclientdetails.SsoClientDetailsDao;

@RestController
public class SsoClientDetailsController {

    private final SsoClientDetailsDao ssoClientDetailsDao;

    public SsoClientDetailsController(SsoClientDetailsDao ssoClientDetailsDao) {
        this.ssoClientDetailsDao = ssoClientDetailsDao;
    }

    @GetMapping("/ssoClientDetails/{clientId}")
    public ISsoClientDetails get(@PathVariable String clientId) {
        return ssoClientDetailsDao.get(clientId);
    }
}
