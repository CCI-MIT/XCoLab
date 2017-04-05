package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.exceptions.NotFoundException;
import org.xcolab.service.admin.pojo.Notification;

import java.util.ArrayList;

@RestController
public class AdminController {

    private final ConfigurationAttributeDao configurationAttributeDao;

    public static ArrayList<Notification> notificationsList;

    @GetMapping("/notifications")
    public ArrayList<Notification> getNotifications() {
        notificationsList = new ArrayList<>();

        Notification noti = new Notification();
        noti.setNotificationId(1);
        noti.setNotificationText("zeeshan");

        notificationsList.add(noti);

        return notificationsList;
    }

    @PostMapping("/notifications/{Message}")
    public void setNotifications(@RequestBody Notification message) {
        notificationsList.add(message);
    }

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

    @PutMapping("/attributes/{attributeName}")
    public boolean updateConfigurationAttribute(@RequestBody ConfigurationAttribute pojo,@PathVariable String attributeName)
            throws NotFoundException {
        return configurationAttributeDao.update(pojo);
    }

}
