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

import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.exceptions.BalloonLinkNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonLink;
import org.xcolab.service.tracking.domain.balloonlink.BalloonLinkDao;
import org.xcolab.service.tracking.domain.balloontext.BalloonTextDao;
import org.xcolab.service.tracking.domain.balloonusertracking.BalloonUserTrackingDao;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class BalloonController implements IBalloonClient {

    private final BalloonUserTrackingDao balloonUserTrackingDao;
    private final BalloonLinkDao balloonLinkDao;
    private final BalloonTextDao balloonTextDao;

    @Autowired
    public BalloonController(BalloonUserTrackingDao balloonUserTrackingDao,
            BalloonLinkDao balloonLinkDao, BalloonTextDao balloonTextDao) {
        this.balloonUserTrackingDao = balloonUserTrackingDao;
        this.balloonLinkDao = balloonLinkDao;
        this.balloonTextDao = balloonTextDao;
    }

    @Override
    @PostMapping("/balloonLinks")
    public IBalloonLink createBalloonLink(@RequestBody IBalloonLink balloonLink) {
        return this.balloonLinkDao.create(balloonLink);
    }

    @Override
    @PutMapping("/balloonLinks/{uuid}")
    public boolean updateBalloonLink(@RequestBody IBalloonLink balloonLink,
            @PathVariable String uuid) throws BalloonLinkNotFoundException {
        try {
            balloonLinkDao.getBalloonLink(uuid);
            return balloonLinkDao.update(balloonLink);
        } catch (NotFoundException e) {
            throw new BalloonLinkNotFoundException(
                    "BalloonLink " + uuid + " does not exist");
        }
    }

    @Override
    @GetMapping("/balloonLinks/{uuid}")
    public IBalloonLink getBalloonLink(@PathVariable String uuid)
            throws BalloonLinkNotFoundException {
        try {
            return this.balloonLinkDao.getBalloonLink(uuid);
        } catch (NotFoundException e) {
            return new BalloonLink();
//            throw new BalloonLinkNotFoundException(
//                    "BalloonLink " + uuid + " does not exist");
        }
    }

    @Override
    @GetMapping("/balloonLinks")
    public List<IBalloonLink> listBalloonLinks(@RequestParam String memberUuid) {
        return this.balloonLinkDao.findByGiven(PaginationHelper.EVERYTHING, memberUuid);
    }

    @Override
    @PostMapping("/balloonUserTrackings")
    public IBalloonUserTracking createBalloonUserTracking(
            @RequestBody IBalloonUserTracking balloonUserTracking) {

        return this.balloonUserTrackingDao.create(balloonUserTracking);
    }

    @Override
    @GetMapping("/balloonUserTrackings/{uuid}")
    public IBalloonUserTracking getBalloonUserTracking(@PathVariable String uuid)
            throws BalloonUserTrackingNotFoundException {
        try {
            return this.balloonUserTrackingDao.getBalloonUserTrackingByUuid(uuid);
        } catch (NotFoundException e) {
            throw new BalloonUserTrackingNotFoundException(
                    "BalloonUserTracking " + uuid + " does not exist");
        }
    }

    @Override
    @GetMapping("/balloonUserTrackings")
    public List<IBalloonUserTracking> listBalloonUserTrackings(
            @RequestParam String email,
            @RequestParam(value = "context", required = false) String context) {
        return this.balloonUserTrackingDao.list(email, context);
    }

    @Override
    @PutMapping("/balloonUserTrackings/{uuid}")
    public boolean updateBalloonUserTracking(@RequestBody IBalloonUserTracking balloonUserTracking,
            @PathVariable String uuid) {
        return balloonUserTrackingDao.update(balloonUserTracking);
    }

    @Override
    @DeleteMapping("/balloonUserTrackings/{uuid}")
    public boolean deleteBalloonUserTracking(@PathVariable String uuid) {
        return balloonUserTrackingDao.delete(uuid);
    }

    @Override
    @GetMapping("/balloonTexts/{id}")
    public IBalloonText getBalloonText(@PathVariable Long id) throws BalloonTextNotFoundException {
        try {
            return this.balloonTextDao.getBalloonText(id);
        } catch (NotFoundException e) {
            throw new BalloonTextNotFoundException(id);
        }
    }

    @Override
    @PutMapping("/balloonTexts/{id}")
    public boolean updateBalloonText(@RequestBody IBalloonText balloonText, @PathVariable Long id) {
        return balloonTextDao.update(balloonText);
    }

    @Override
    @GetMapping("/balloonTexts")
    public List<IBalloonText> getAllEnabledBalloonLinks() {
        return this.balloonTextDao.getEnabledBalloonTexts();
    }

    @Override
    @PostMapping("/balloonTexts")
    public IBalloonText createBalloonText(@RequestBody IBalloonText balloonText) {
        return this.balloonTextDao.create(balloonText);
    }

    @Override
    @DeleteMapping(value = "/balloonTexts/{id}")
    public boolean deleteBalloonText(@PathVariable Long id) throws BalloonTextNotFoundException {
        try {
            balloonTextDao.getBalloonText(id);
            return balloonTextDao.delete(id);
        } catch (NotFoundException e) {
            throw new BalloonTextNotFoundException(id);
        }
    }
}
