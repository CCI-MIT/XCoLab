package org.xcolab.service.sharedcolab.web;

import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.model.tables.pojos.SharedContest;
import org.xcolab.model.tables.pojos.SharedMember;
import org.xcolab.service.sharedcolab.domain.sharedContest.SharedContestDao;
import org.xcolab.service.sharedcolab.domain.sharedMember.SharedMemberDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class SharedColabController {

    private final SharedMemberDao sharedMemberDao;

    private final SharedContestDao sharedContestDao;

    @Autowired
    public SharedColabController(SharedMemberDao sharedMemberDao, SharedContestDao sharedContestDao) {
        Assert.notNull(sharedMemberDao, "SharedMemberDao bean is required");
        Assert.notNull(sharedContestDao, "SharedContestDao bean is required");
        this.sharedMemberDao = sharedMemberDao;
        this.sharedContestDao = sharedContestDao;
    }

    @GetMapping(value = "/members/isUsed")
    public boolean isUsed(
            @RequestParam(required = false) String screenName,
            @RequestParam(required = false) String email) {
        checkSharedCoLabServiceActive();
        boolean ret = false;
        if (screenName != null) {
            ret = sharedMemberDao.isScreenNameTaken(screenName);
        }
        if (email != null) {
            ret = ret || sharedMemberDao.isEmailUsed(email);
        }
        return ret;
    }

    @PostMapping(value = "/members/retrieveSharedId")
    public Long retrieveSharedId(@RequestParam String screenName, @RequestParam String email,
            @RequestParam String colabOrigin) {
        checkSharedCoLabServiceActive();
        return sharedMemberDao.getByScreenNameAndEmail(screenName, email)
                .map(SharedMember::getSharedMemberId)
                .orElseGet(() -> sharedMemberDao.create(screenName, email, colabOrigin));
    }

    private void checkSharedCoLabServiceActive() {
        if (!ConfigurationAttributeKey.SHARED_COLAB_LOCATION.get().equals("localhost")) {
            throw new SharedColabInactiveException(
                    String.format("sharedcolab-service at localhost is inactive - use %s:%s",
                            ConfigurationAttributeKey.SHARED_COLAB_LOCATION.get(),
                            ConfigurationAttributeKey.SHARED_COLAB_PORT.get()));
        }
    }


    @RequestMapping(value = "/contests/retrieveSharedId", method = RequestMethod.POST)
    public Long retrieveSharedIdForContests(@RequestParam("sharedContestName") String sharedContestName,
            @RequestParam("colabOrigin") String colabOrigin) throws NotFoundException {
        checkSharedCoLabServiceActive();
        if (sharedContestName == null || sharedContestName == "") {
            throw new NotFoundException("No sharedContestName given");
        } else {
            SharedContest sc = new SharedContest();
            sc.setCreateDate(new Timestamp(new Date().getTime()));
            sc.setColabOrigin(colabOrigin);
            sc.setContestName(sharedContestName);
            sc = sharedContestDao.create(sc);
            return sc.getSharedContestId();
        }
    }

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    public List<SharedContest> retrieveSharedContestsFromForeingColab(
            @RequestParam("colabOrigin") String colabOrigin) throws NotFoundException {

        return sharedContestDao.findByGiven(colabOrigin);
    }

    @RequestMapping(value = "/contests/{sharedContestId}/updateSharedContestName", method = RequestMethod.PUT)
    public boolean updateSharedContestName(@PathVariable("sharedContestId") Long sharedContestId,
            @RequestParam String sharedContestName) throws NotFoundException {
        checkSharedCoLabServiceActive();
        SharedContest sc =  sharedContestDao.get(sharedContestId);
        if (sharedContestId == null || sharedContestId == 0 || sc == null) {
            throw new NotFoundException("No SharedContest with id " + sharedContestId);
        } else {
            sc.setContestName(sharedContestName);
            return sharedContestDao.update(sc);
        }
    }

    private static class SharedColabInactiveException extends IllegalStateException {
        public SharedColabInactiveException(String message) {
            super(message);
        }
    }

}
