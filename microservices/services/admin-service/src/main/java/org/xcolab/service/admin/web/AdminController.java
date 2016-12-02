package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.exceptions.NotFoundException;

@RestController
public class AdminController {

    private final ConfigurationAttributeDao configurationAttributeDao;

    @Autowired
    public AdminController(ConfigurationAttributeDao configurationAttributeDao) {
        this.configurationAttributeDao = configurationAttributeDao;
    }

    @GetMapping("/attributes/{attributeName}")
    public ConfigurationAttribute getConfigurationAttribute(@PathVariable String attributeName)
            throws NotFoundException {
        return configurationAttributeDao.getConfigurationAttribute(attributeName)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/attributes")
    public ConfigurationAttribute createConfigurationAttribute(@RequestBody ConfigurationAttribute pojo) {
        return configurationAttributeDao.create(pojo);
    }

}
