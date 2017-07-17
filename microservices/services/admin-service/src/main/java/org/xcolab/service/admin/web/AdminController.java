package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.model.tables.pojos.ContestTypeAttribute;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.domain.contesttypeattribute.ContestTypeAttributeDao;
import org.xcolab.service.admin.exceptions.NotFoundException;
import org.xcolab.service.admin.pojo.Notification;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    private final ConfigurationAttributeDao configurationAttributeDao;
    private final ContestTypeAttributeDao contestTypeAttributeDao;

    //TODO: this should be in the database
    private final ArrayList<Notification> notificationsList = new ArrayList<>();

    //TODO: moved from the view, remove when moving list to database
    private long notificationCounter;

    @Autowired
    public AdminController(ConfigurationAttributeDao configurationAttributeDao,
            ContestTypeAttributeDao contestTypeAttributeDao) {
        this.configurationAttributeDao = configurationAttributeDao;
        this.contestTypeAttributeDao = contestTypeAttributeDao;
    }

    @DeleteMapping("/notifications/{notificationId}")
    public boolean deleteNotifications(@PathVariable long notificationId) throws NotFoundException {
        return notificationsList.removeIf(notification -> notification.getNotificationId() == notificationId);
    }

    @GetMapping("/notifications")
    public ArrayList<Notification> getNotifications() {
        notificationsList.removeIf(Notification::isExpired);
        return notificationsList;
    }

    @PostMapping("/notifications")
    public void createNotification(@RequestBody Notification message) {
        if (message.getEndTime().before(message.getBeginTime())) {
            throw new IllegalArgumentException("Begin time cannot be after end time.");
        } else {
            message.setNotificationId(++notificationCounter);
            notificationsList.add(message);
        }
    }

    @GetMapping("/attributes/{attributeName}")
    public ConfigurationAttribute getConfigurationAttribute(@PathVariable String attributeName,
            @RequestParam(required = false) String locale)
            throws NotFoundException {
        return configurationAttributeDao.getConfigurationAttribute(attributeName, locale)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/attributes")
    public ConfigurationAttribute createConfigurationAttribute(
            @RequestBody ConfigurationAttribute pojo) {
        return configurationAttributeDao.create(pojo);
    }

    @PutMapping("/attributes/{attributeName}")
    public boolean updateConfigurationAttribute(@RequestBody ConfigurationAttribute pojo,
            @PathVariable String attributeName)
            throws NotFoundException {
        return configurationAttributeDao.update(pojo);
    }

    @GetMapping("/contestTypeAttributes")
    public List<ContestTypeAttribute> listContestTypeAttributes() {
        return contestTypeAttributeDao.list();
    }

    @GetMapping("/contestTypeAttributes/{attributeName}")
    public ContestTypeAttribute getContestTypeAttribute(@PathVariable String attributeName,
            @RequestParam long additionalId,
            @RequestParam(required = false) String locale)
            throws NotFoundException {
        return contestTypeAttributeDao.get(attributeName, additionalId, locale)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/contestTypeAttributes")
    public ContestTypeAttribute createContestTypeAttribute(
            @RequestBody ContestTypeAttribute pojo) {
        return contestTypeAttributeDao.create(pojo);
    }

    @PutMapping("/contestTypeAttributes/{attributeName}")
    public boolean updateContestTypeAttribute(@RequestBody ConfigurationAttribute pojo,
            @PathVariable String attributeName)
            throws NotFoundException {
        return configurationAttributeDao.update(pojo);
    }

}
