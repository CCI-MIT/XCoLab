package org.xcolab.service.tracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.service.tracking.domain.balloonlink.BalloonLinkDao;
import org.xcolab.service.tracking.domain.balloontext.BalloonTextDao;
import org.xcolab.service.tracking.domain.balloonusertracking.BalloonUserTrackingDao;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class BalloonsController {

    private final BalloonUserTrackingDao balloonUserTrackingDao;
    private final BalloonLinkDao balloonLinkDao;
    private final BalloonTextDao balloonTextDao;

    @Autowired
    public BalloonsController(BalloonUserTrackingDao balloonUserTrackingDao,
            BalloonLinkDao balloonLinkDao, BalloonTextDao balloonTextDao) {
        this.balloonUserTrackingDao = balloonUserTrackingDao;
        this.balloonLinkDao = balloonLinkDao;
        this.balloonTextDao = balloonTextDao;
    }

    @PostMapping("/balloonLinks")
    public IBalloonLink createBalloonLink(@RequestBody IBalloonLink balloonLink) {
        return this.balloonLinkDao.create(balloonLink);
    }


    @PutMapping("/balloonLinks/{uuid}")
    public boolean updateBalloonLink(@RequestBody IBalloonLink balloonLink,
                                     @PathVariable("uuid") String uuid) throws NotFoundException {

        if (balloonLinkDao.getBalloonLink(uuid) == null) {
            throw new NotFoundException();
        } else {
            return balloonLinkDao.update(balloonLink);
        }
    }

    @GetMapping("/balloonLinks/{uuid}")
    public IBalloonLink getBalloonLink(@PathVariable("uuid") String uuid) throws NotFoundException {
        if (uuid == null) {
            throw new NotFoundException();
        } else {
            return this.balloonLinkDao.getBalloonLink(uuid);
        }
    }

    @GetMapping("/balloonLinks")
    public List<IBalloonLink> listBalloonLinks(@RequestParam(required = false) String memberUuid) {
        return this.balloonLinkDao.findByGiven(PaginationHelper.EVERYTHING, memberUuid);
    }

    @PostMapping("/balloonUserTrackings")
    public IBalloonUserTracking createBalloonUserTracking(
            @RequestBody IBalloonUserTracking balloonUserTracking) {

        return this.balloonUserTrackingDao.create(balloonUserTracking);
    }

    @GetMapping("/balloonUserTrackings/{uuid}")
    public IBalloonUserTracking getBalloonUserTracking(@PathVariable String uuid)
            throws NotFoundException {
        return this.balloonUserTrackingDao.getBalloonUserTrackingByUuid(uuid);
    }

    @GetMapping("/balloonUserTrackings")
    public List<IBalloonUserTracking> listBalloonUserTrackings(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String context) {
        return this.balloonUserTrackingDao.list(email,context );
    }

    @PutMapping("/balloonUserTrackings/{uuid}")
    public boolean updateBalloonUserTracking(@RequestBody IBalloonUserTracking balloonUserTracking,
            @PathVariable String uuid) {
        return balloonUserTrackingDao.update(balloonUserTracking);
    }

    @DeleteMapping("/balloonUserTrackings/{uuid}")
    public boolean deleteBalloonUserTracking(@PathVariable String uuid) {
        return balloonUserTrackingDao.delete(uuid);
    }

    @GetMapping("/balloonTexts/{id}")
    public IBalloonText getBalloonText(@PathVariable("id") Long id) throws NotFoundException {
        if (id == null) {
            throw new NotFoundException();
        } else {
            return this.balloonTextDao.getBalloonText(id);
        }
    }

    @PutMapping("/balloonTexts/{id}")
    public boolean updateBalloonText(@RequestBody IBalloonText balloonText, @PathVariable Long id) {
        return balloonTextDao.update(balloonText);
    }

    @GetMapping("/balloonTexts")
    public List<IBalloonText> getAllEnabledBalloonLinks() {
        return this.balloonTextDao.getEnabledBalloonTexts();
    }

    @PostMapping("/balloonTexts")
    public IBalloonText createBalloonText(@RequestBody IBalloonText balloonText) {
        return this.balloonTextDao.create(balloonText);
    }

    @DeleteMapping(value = "/balloonTexts/{id}")
    public boolean deleteBalloonText(@PathVariable("id") Long id) throws NotFoundException {

        if (balloonTextDao.getBalloonText(id) == null) {
            throw new NotFoundException();
        } else {
            return balloonTextDao.delete(id);
        }
    }
}
