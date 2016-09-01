package org.xcolab.service.tracking.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;
import org.xcolab.service.tracking.service.iptranslation.Location;

import java.util.Collections;
import java.util.List;

@RestController
public class IpTranslationController {

    private static final Logger log = LoggerFactory.getLogger(IpTranslationController.class);

    @Autowired
    private IpTranslationService ipTranslationService;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public List<Location> getLocationForIp(@RequestParam String ipAddress) {
        try {
            return ipTranslationService.getLocationForIp(ipAddress)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } catch (IpTranslationService.IpFormatException e) {
            log.warn("Could not process ip address {}: {}", ipAddress, e.toString());
            return Collections.emptyList();
        }
    }
}
