package org.xcolab.service.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;

@Service
public class AdminService {

    private final ConfigurationAttributeDao configurationAttributeDao;

    @Autowired
    public AdminService(ConfigurationAttributeDao configurationAttributeDao) {
        this.configurationAttributeDao = configurationAttributeDao;
    }
}
