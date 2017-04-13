package org.xcolab.service.admin.web;

import com.ctc.wstx.util.StringUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.control.Alert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

@RestController
public class AdminController {

    private final ConfigurationAttributeDao configurationAttributeDao;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdminController.class);

    public static ArrayList<Notification> notificationsList;

    @DeleteMapping("/notifications/{notificationId}")
    public boolean deleteNotifications(@PathVariable long notificationId) throws NotFoundException {


        try {
            if (notificationsList == null || notificationsList.isEmpty()) {
                return false;
            }

            //iterator searches for list items and delete
            for (Iterator<Notification> iter = notificationsList.listIterator(); iter.hasNext(); ) {
                Notification noti = iter.next();
                if (noti.getNotificationId() == notificationId) {
                    iter.remove();
                }
            }

            log.warn("Inside Admin Service- AdminController.java deleteNotifications()");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @GetMapping("/notifications")
    public ArrayList<Notification> getNotifications() {

        try {
            if(notificationsList== null || notificationsList.isEmpty()) {
                notificationsList = new ArrayList<>();
            }

            purify();

            log.warn("Inside Admin Service- AdminController.java getNotifications()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notificationsList;
    }

    private void purify() {
        try {
            if(notificationsList== null || notificationsList.isEmpty()) {
                return;
            }

            for (Iterator<Notification> iter = notificationsList.listIterator(); iter.hasNext(); ) {
                Notification noti = iter.next();
                if (noti.isExpired()) {
                    iter.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/notifications")
    public void setNotifications(@RequestBody Notification message) {
        try {
            log.debug("Inside Admin Service- AdminController.java setNotifications()");
            if(message.getEndTime().before(message.getBeginTime()))
                log.warn("Begin time for notification cannot be after expire time");
            else
                notificationsList.add(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
