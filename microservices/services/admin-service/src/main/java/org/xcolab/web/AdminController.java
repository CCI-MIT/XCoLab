package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.service.AdminService;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ConfigurationAttributeDao configurationAttributeDao;

    @RequestMapping(value = "/attributes/{attributeName}")
    public ConfigurationAttribute getConfigurationAttribute(@PathVariable String attributeName) {
        return configurationAttributeDao.getConfigurationAttribute(attributeName);
    }
}
