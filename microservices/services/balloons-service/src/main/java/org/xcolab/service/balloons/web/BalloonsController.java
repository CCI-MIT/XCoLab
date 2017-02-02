package org.xcolab.service.balloons.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.model.tables.pojos.BalloonText;
import org.xcolab.model.tables.pojos.BalloonUserTracking;
import org.xcolab.service.balloons.domain.balloonlink.BalloonLinkDao;
import org.xcolab.service.balloons.domain.balloontext.BalloonTextDao;
import org.xcolab.service.balloons.domain.balloonusertracking.BalloonUserTrackingDao;
import org.xcolab.service.balloons.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

@RestController
public class BalloonsController {

    @Autowired
    private BalloonUserTrackingDao balloonUserTrackingDao;

    @Autowired
    private BalloonLinkDao balloonLinkDao;

    @Autowired
    private BalloonTextDao balloonTextDao;

    @RequestMapping(value = "/balloonLinks", method = RequestMethod.POST)
    public BalloonLink createBalloonLink(@RequestBody BalloonLink balloonLink) {

        return this.balloonLinkDao.create(balloonLink);
    }


    @RequestMapping(value = "/balloonLinks/{uuid}", method = RequestMethod.PUT)
    public boolean updateBalloonLink(@RequestBody BalloonLink balloonLink,
                                     @PathVariable("uuid") String uuid) throws NotFoundException {

        if (balloonLinkDao.getBalloonLink(uuid) == null) {
            throw new NotFoundException();
        } else {
            return balloonLinkDao.update(balloonLink);
        }
    }

    @RequestMapping(value = "/balloonLinks/{uuid}", method = RequestMethod.GET)
    public BalloonLink getBalloonLink(@PathVariable("uuid") String uuid) throws NotFoundException {
        if (uuid == null) {
            throw new NotFoundException();
        } else {
            return this.balloonLinkDao.getBalloonLink(uuid);
        }
    }

    @RequestMapping(value = "/balloonLinks", method = RequestMethod.GET)
    public List<BalloonLink> listBalloonLinks(@RequestParam(required = false) String memberUuid) {
        return this.balloonLinkDao.findByGiven(PaginationHelper.EVERYTHING, memberUuid);
    }

    /*
    @RequestMapping(value = "/balloonUserTracking/", method = RequestMethod.GET)
    public List<BalloonUserTracking> getAllBalloonUserTracking() {

        return this.balloonUserTrackingDao.getAllBalloonUserTracking();
    }*/

    @RequestMapping(value = "/balloonUserTrackings", method = RequestMethod.POST)
    public BalloonUserTracking createBalloonUserTracking(@RequestBody BalloonUserTracking balloonUserTracking) {

        return this.balloonUserTrackingDao.create(balloonUserTracking);
    }

    @RequestMapping(value = "/balloonUserTrackings/{uuid}", method = RequestMethod.GET)
    public BalloonUserTracking getBalloonUserTracking(@PathVariable("uuid") String uuid) throws NotFoundException {
        if (uuid == null) {
            throw new NotFoundException();
        } else {
            return this.balloonUserTrackingDao.getBallonUserTrackingByUuid(uuid);
        }
    }

    @RequestMapping(value = "/balloonUserTrackings", method = RequestMethod.GET)
    public List<BalloonUserTracking> getBalloonUserTrackingByEmail(@RequestParam(required = false) String email) throws NotFoundException {
        if (email == null) {
            throw new NotFoundException();
        } else {
            return this.balloonUserTrackingDao.getBallonUserTrackingByEmail(email);
        }
    }

    @RequestMapping(value = "/balloonUserTrackings/{uuid}", method = RequestMethod.PUT)
    public boolean updateBalloonUserTracking(@RequestBody BalloonUserTracking balloonUserTracking,
                                             @PathVariable("uuid") String uuid) throws NotFoundException {

        if (balloonUserTrackingDao.getBallonUserTrackingByUuid(uuid) == null) {
            List<BalloonUserTracking> searchByEmail = balloonUserTrackingDao
                    .getBallonUserTrackingByEmail(balloonUserTracking.getEmail());
            if (searchByEmail == null || searchByEmail.isEmpty()) {
                throw new NotFoundException();
            }else{
                balloonUserTracking.setUuid_(searchByEmail.get(0).getUuid_());
                //this is not understood might be legacy compatibility
            }
        }

        return balloonUserTrackingDao.update(balloonUserTracking);

    }

    @RequestMapping(value = "/balloonTexts/{id}", method = RequestMethod.GET)
    public BalloonText getBalloonText(@PathVariable("id") Long id) throws NotFoundException {
        if (id == null) {
            throw new NotFoundException();
        } else {
            return this.balloonTextDao.getBallonText(id);
        }
    }

    @RequestMapping(value = "/balloonTexts/{id}", method = RequestMethod.PUT)
    public boolean updateBalloonText(@RequestBody BalloonText balloonText,
                                     @PathVariable("id") Long id) throws NotFoundException {

        if (balloonTextDao.getBallonText(id) == null) {
            throw new NotFoundException();
        } else {
            return balloonTextDao.update(balloonText);
        }
    }

    @GetMapping("/balloonTexts")
    public List<BalloonText> getAllEnabledBalloonLinks() {
        return this.balloonTextDao.getEnabledBalloonTexts();
    }

    @RequestMapping(value = "/balloonTexts", method = RequestMethod.POST)
    public BalloonText createBalloonText(@RequestBody BalloonText balloonText) {
        return this.balloonTextDao.create(balloonText);
    }

    @RequestMapping(value = "/balloonTexts/{id}", method = RequestMethod.DELETE)
    public boolean deleteBalloonText(@PathVariable("id") Long id) throws NotFoundException {

        if (balloonTextDao.getBallonText(id) == null) {
            throw new NotFoundException();
        } else {
            return balloonTextDao.delete(id);
        }
    }

}
