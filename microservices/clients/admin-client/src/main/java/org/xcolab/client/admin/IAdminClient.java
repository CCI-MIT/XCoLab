package org.xcolab.client.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.INotification;

import java.util.List;
import java.util.Optional;

@FeignClient("xcolab-admin-service")
public interface IAdminClient {

    @GetMapping("/notifications")
    List<INotification> getNotifications();

    default INotification getFirstNotification() {
        return getNotifications().stream().findFirst().orElse(null);
    }

    @PostMapping("/notifications")
    void createNotification(@RequestBody INotification notification);

    @DeleteMapping("/notifications/{notificationId}")
    boolean deleteNotifications(@PathVariable("notificationId") Long notificationId);

    @GetMapping("/attributes/{name}")
    Optional<IConfigurationAttribute> getConfigurationAttribute(@PathVariable("name") String name,
            @RequestParam(value = "locale", required = false) String locale);

    @PostMapping("/attributes")
    IConfigurationAttribute createConfigurationAttribute(
            @RequestBody IConfigurationAttribute configurationAttribute);

    @PutMapping("/attributes")
    boolean updateConfigurationAttribute(
            @RequestBody IConfigurationAttribute configurationAttribute);
}
