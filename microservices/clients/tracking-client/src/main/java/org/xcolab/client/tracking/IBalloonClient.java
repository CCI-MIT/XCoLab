package org.xcolab.client.tracking;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    boolean updateBalloonLink(@RequestBody IBalloonLink balloonLink, @PathVariable("uuid") String uuid);

    @GetMapping("/balloonLinks/{uuid}")
    IBalloonLink getBalloonLink(@PathVariable("uuid") String uuid);

    @GetMapping("/balloonLinks")
    List<IBalloonLink> listBalloonLinks(@RequestParam(required = false) String memberUuid);

    @PostMapping("/balloonUserTrackings")
    IBalloonUserTracking createBalloonUserTracking(@RequestBody IBalloonUserTracking balloonUserTracking);

    @GetMapping("/balloonUserTrackings/{uuid}")
    IBalloonUserTracking getBalloonUserTracking(@PathVariable String uuid);

    @GetMapping("/balloonUserTrackings")
    List<IBalloonUserTracking> listBalloonUserTrackings(@RequestParam(required = false) String email, @RequestParam(required = false) String context);

    @PutMapping("/balloonUserTrackings/{uuid}")
    boolean updateBalloonUserTracking(@RequestBody IBalloonUserTracking balloonUserTracking, @PathVariable String uuid);

    @DeleteMapping("/balloonUserTrackings/{uuid}")
    boolean deleteBalloonUserTracking(@PathVariable String uuid);

    @GetMapping("/balloonTexts/{id}")
    IBalloonText getBalloonText(@PathVariable("id") Long id);

    @PutMapping("/balloonTexts/{id}")
    boolean updateBalloonText(@RequestBody IBalloonText balloonText, @PathVariable Long id);

    @GetMapping("/balloonTexts")
    List<IBalloonText> getAllEnabledBalloonLinks();

    @PostMapping("/balloonTexts")
    IBalloonText createBalloonText(@RequestBody IBalloonText balloonText);

    @DeleteMapping("/balloonTexts/{id}")
    boolean deleteBalloonText(@PathVariable("id") Long id);

    @GetMapping("/balloonLinks/{butUuid}")
    IBalloonLink getLinkByBalloonUserTrackingUuid(@PathVariable("butUuid") String butUuid);
}
