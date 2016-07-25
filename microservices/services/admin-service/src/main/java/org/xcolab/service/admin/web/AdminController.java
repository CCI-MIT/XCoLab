package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.exceptions.NotFoundException;
import org.xcolab.service.admin.service.AdminService;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ConfigurationAttributeDao configurationAttributeDao;

    @RequestMapping(value = "/attributes/{attributeName}", method = RequestMethod.GET)
    public ConfigurationAttribute getConfigurationAttribute(@PathVariable String attributeName)
            throws NotFoundException {
        final ConfigurationAttribute configurationAttribute = configurationAttributeDao
            .getConfigurationAttribute(attributeName);
        if (configurationAttribute == null) {
            throw new NotFoundException("Configuration attribute " + attributeName + " does not exist.");
        }
        return configurationAttribute;
    }
}
