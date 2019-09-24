package org.xcolab.client.tracking;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.tracking.exceptions.BalloonLinkNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;

import java.util.List;

@FeignClient("xcolab-tracking-service")
public interface IBalloonClient {

    @PostMapping("/balloonLinks")
    IBalloonLink createBalloonLink(@RequestBody IBalloonLink balloonLink);

    @PutMapping("/balloonLinks/{uuid}")
    boolean updateBalloonLink(@RequestBody IBalloonLink balloonLink,
            @PathVariable("uuid") String uuid) throws BalloonLinkNotFoundException;

    @GetMapping("/balloonLinks/{uuid}")
    IBalloonLink getBalloonLink(@PathVariable("uuid") String uuid) throws BalloonLinkNotFoundException;

    @GetMapping("/balloonLinks")
    List<IBalloonLink> listBalloonLinks(
            @RequestParam(value = "memberUuid", required = false) String memberUuid);

    @PostMapping("/balloonUserTrackings")
    IBalloonUserTracking createBalloonUserTracking(
            @RequestBody IBalloonUserTracking balloonUserTracking);

    @GetMapping("/balloonUserTrackings/{uuid}")
    IBalloonUserTracking getBalloonUserTracking(@PathVariable("uuid") String uuid)
            throws BalloonUserTrackingNotFoundException;

    @GetMapping("/balloonUserTrackings")
    List<IBalloonUserTracking> listBalloonUserTrackings(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "context", required = false) String context);

    @PutMapping("/balloonUserTrackings/{uuid}")
    boolean updateBalloonUserTracking(@RequestBody IBalloonUserTracking balloonUserTracking,
            @PathVariable("uuid") String uuid);

    default boolean updateUserIdAndEmailIfEmpty(IBalloonUserTracking balloonUserTracking,
            Long userId, String email) {
        final boolean isUserIdEmpty = balloonUserTracking.getUserId() == null;
        if (isUserIdEmpty) {
            balloonUserTracking.setUserId(userId);
        }
        final boolean isEmailBlank = StringUtils.isBlank(balloonUserTracking.getEmail());
        if (isEmailBlank) {
            balloonUserTracking.setEmail(email);
        }
        return updateBalloonUserTracking(balloonUserTracking, Long.toString(userId));
    }

    @DeleteMapping("/balloonUserTrackings/{uuid}")
    boolean deleteBalloonUserTracking(@PathVariable("uuid") String uuid);

    @GetMapping("/balloonTexts/{id}")
    IBalloonText getBalloonText(@PathVariable("id") Long id) throws BalloonTextNotFoundException;

    @PutMapping("/balloonTexts/{id}")
    boolean updateBalloonText(@RequestBody IBalloonText balloonText, @PathVariable("id") Long id);

    @GetMapping("/balloonTexts")
    List<IBalloonText> getAllEnabledBalloonLinks();

    @PostMapping("/balloonTexts")
    IBalloonText createBalloonText(@RequestBody IBalloonText balloonText);

    @DeleteMapping("/balloonTexts/{id}")
    boolean deleteBalloonText(@PathVariable("id") Long id) throws BalloonTextNotFoundException;
}
