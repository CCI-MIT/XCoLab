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

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.IContestTypeAttribute;
import org.xcolab.client.admin.pojo.INotification;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.domain.contesttypeattribute.ContestTypeAttributeDao;
import org.xcolab.service.admin.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    private final ConfigurationAttributeDao configurationAttributeDao;
    private final ContestTypeAttributeDao contestTypeAttributeDao;

    //TODO COLAB-2046: this should be in the database
    private final ArrayList<INotification> notificationsList = new ArrayList<>();

    //TODO COLAB-2046: moved from the view, remove when moving list to database
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
    public ArrayList<INotification> getNotifications() {
        notificationsList.removeIf(INotification::isExpired);
        return notificationsList;
    }

    @PostMapping("/notifications")
    public void createNotification(@RequestBody INotification message) {
        if (message.getEndTime().before(message.getBeginTime())) {
            throw new IllegalArgumentException("Begin time cannot be after end time.");
        } else {
            message.setNotificationId(++notificationCounter);
            notificationsList.add(message);
        }
    }

    @GetMapping("/attributes/{attributeName}")
    public IConfigurationAttribute getConfigurationAttribute(@PathVariable String attributeName,
            @RequestParam(required = false) String locale)
            throws NotFoundException {
        return configurationAttributeDao.getConfigurationAttribute(attributeName, locale)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/attributes")
    public IConfigurationAttribute createConfigurationAttribute(
            @RequestBody IConfigurationAttribute pojo) {
        return configurationAttributeDao.create(pojo);
    }

    @PutMapping("/attributes/{attributeName}")
    public boolean updateConfigurationAttribute(@RequestBody IConfigurationAttribute pojo,
            @PathVariable String attributeName)
            throws NotFoundException {
        Optional<IConfigurationAttribute> configurationAttribute =
                configurationAttributeDao.getConfigurationAttribute(attributeName, null);
        if (!configurationAttribute.isPresent()) {
            configurationAttributeDao.create(pojo);
        }
        return configurationAttributeDao.update(pojo);
    }

    @GetMapping("/contestTypeAttributes")
    public List<IContestTypeAttribute> listContestTypeAttributes() {
        return contestTypeAttributeDao.list();
    }

    @GetMapping("/contestTypeAttributes/{attributeName}")
    public IContestTypeAttribute getContestTypeAttribute(@PathVariable String attributeName,
            @RequestParam long additionalId,
            @RequestParam(required = false) String locale)
            throws NotFoundException {
        return contestTypeAttributeDao.get(attributeName, additionalId, locale)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/contestTypeAttributes")
    public IContestTypeAttribute createContestTypeAttribute(
            @RequestBody IContestTypeAttribute pojo) {
        return contestTypeAttributeDao.create(pojo);
    }

    @PutMapping("/contestTypeAttributes/{attributeName}")
    public boolean updateContestTypeAttribute(@RequestBody IConfigurationAttribute pojo,
            @PathVariable String attributeName)
            throws NotFoundException {
        return configurationAttributeDao.update(pojo);
    }
}
