package org.xcolab.service.members.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.SsoClientDetails;
import org.xcolab.service.members.domain.ssoclientdetails.SsoClientDetailsDao;

@RestController
public class SsoClientDetailsController {

    private final SsoClientDetailsDao ssoClientDetailsDao;

    public SsoClientDetailsController(SsoClientDetailsDao ssoClientDetailsDao) {
        this.ssoClientDetailsDao = ssoClientDetailsDao;
    }

    @GetMapping("/ssoClientDetails/{clientId}")
    public SsoClientDetails get(@PathVariable String clientId) {
        return ssoClientDetailsDao.get(clientId);
    }
}
