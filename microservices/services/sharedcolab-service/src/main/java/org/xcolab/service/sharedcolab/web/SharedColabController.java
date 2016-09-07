package org.xcolab.service.sharedcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.model.tables.pojos.SharedMember;
import org.xcolab.service.sharedcolab.domain.sharedMember.SharedMemberDao;

@RestController
public class SharedColabController {

    private final SharedMemberDao sharedMemberDao;

    @Autowired
    public SharedColabController(SharedMemberDao sharedMemberDao) {
        Assert.notNull(sharedMemberDao, "SharedMemberDao bean is required");
        this.sharedMemberDao = sharedMemberDao;
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
                            ConfigurationAttributeKey.SHARED_COLAB_LOCATION,
                            ConfigurationAttributeKey.SHARED_COLAB_PORT));
        }
    }

    private static class SharedColabInactiveException extends IllegalStateException {
        public SharedColabInactiveException(String message) {
            super(message);
        }
    }
}
