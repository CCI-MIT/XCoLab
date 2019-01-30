package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.user.pojo.IAnalyticsUserEvent;
import org.xcolab.client.user.pojo.wrapper.AnalyticsUserEventWrapper;

@FeignClient("xcolab-user-service")
@RequestMapping("/members/{userId}/analyticsEvents")
public interface IAnalyticsClient {


    @GetMapping("/{idString}/exists")
    boolean exists(@PathVariable long userId, @PathVariable String idString);

    @PostMapping
    IAnalyticsUserEvent create(@PathVariable long userId,
            @RequestBody IAnalyticsUserEvent analyticsUserEvent);

    default IAnalyticsUserEvent create(long userId, String idString, String category,
            String action, String label, int value) {
        IAnalyticsUserEvent analyticsUserEvent = new AnalyticsUserEventWrapper();
        analyticsUserEvent.setUserId(userId);
        analyticsUserEvent.setIdString(idString);
        analyticsUserEvent.setCategory(category);
        analyticsUserEvent.setAction(action);
        analyticsUserEvent.setLabel(label);
        analyticsUserEvent.setValue(value);
        return create(userId, analyticsUserEvent);
    }
}
