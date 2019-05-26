package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.IContestTypeAttribute;
import org.xcolab.client.admin.pojo.INotification;
import org.xcolab.service.admin.cache.CacheConfig;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.domain.contesttypeattribute.ContestTypeAttributeDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController implements IContestTypeClient, IAdminClient {

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

    @Override
    @DeleteMapping("/notifications/{notificationId}")
    public boolean deleteNotifications(@PathVariable Long notificationId) {
        return notificationsList
                .removeIf(notification -> notification.getNotificationId() == notificationId);
    }

    @Override
    @GetMapping("/notifications")
    public ArrayList<INotification> getNotifications() {
        notificationsList.removeIf(INotification::isExpired);
        return notificationsList;
    }

    @Override
    @PostMapping("/notifications")
    public void createNotification(@RequestBody INotification notification) {
        if (notification.getEndTime().before(notification.getBeginTime())) {
            throw new IllegalArgumentException("Begin time cannot be after end time.");
        } else {
            notification.setNotificationId(++notificationCounter);
            notificationsList.add(notification);
        }
    }

    @Override
    @Cacheable(CacheConfig.CONFIG_ATTRIBUTE_CACHE)
    @GetMapping("/attributes/{name}")
    public Optional<IConfigurationAttribute> getConfigurationAttribute(@PathVariable String name,
            @RequestParam(required = false) String locale) {
        return configurationAttributeDao.getConfigurationAttribute(name, locale);
    }

    @Override
    @PostMapping("/attributes")
    public IConfigurationAttribute createConfigurationAttribute(
            @RequestBody IConfigurationAttribute configurationAttribute) {
        return configurationAttributeDao.create(configurationAttribute);
    }

    @Override
    @PutMapping("/attributes")
    public boolean updateConfigurationAttribute(
            @RequestBody IConfigurationAttribute configurationAttribute) {
        Optional<IConfigurationAttribute> result =
                configurationAttributeDao
                        .getConfigurationAttribute(configurationAttribute.getName(), null);
        if (!result.isPresent()) {
            configurationAttributeDao.create(configurationAttribute);
        }
        return configurationAttributeDao.update(configurationAttribute);
    }

    @Override
    @GetMapping("/contestTypeAttributes")
    public List<IContestTypeAttribute> listContestTypeAttributes() {
        return contestTypeAttributeDao.list();
    }

    @Override
    @Cacheable(CacheConfig.CONTEST_TYPE_ATTRIBUTE_CACHE)
    @GetMapping("/contestTypeAttributes/{attributeName}")
    public Optional<IContestTypeAttribute> getContestTypeAttribute(@PathVariable String attributeName,
            @RequestParam Long additionalId, @RequestParam(required = false) String locale) {
        return contestTypeAttributeDao.get(attributeName, additionalId, locale);
    }

    @Override
    @PostMapping("/contestTypeAttributes")
    public IContestTypeAttribute createContestTypeAttribute(
            @RequestBody IContestTypeAttribute contestTypeAttribute) {
        return contestTypeAttributeDao.create(contestTypeAttribute);
    }

    @Override
    @PutMapping("/contestTypeAttributes")
    public boolean updateContestTypeAttribute(
            @RequestBody IContestTypeAttribute contestTypeAttribute) {
        return contestTypeAttributeDao.update(contestTypeAttribute);
    }
}
