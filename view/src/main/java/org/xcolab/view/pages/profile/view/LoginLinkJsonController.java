package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.ILoginTokenClient;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.LoginTokenWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;

@RestController
@RequestMapping("/members/profile/{userId}/api/login")
public class LoginLinkJsonController {

    @Autowired
    private IUserClient userClient;

    @Autowired
    private ILoginTokenClient loginTokenClient;

    @Autowired
    private IPermissionClient permissionClient;

    @PostMapping("generateNewToken")
    public ResponseEntity<Void> generateNewLoginToken(@PathVariable long userId,
            UserWrapper loggedInUser) {

        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final boolean editingOwnProfile = userId == loggedInUser.getId();
        if (!editingOwnProfile && !permissionClient.canAdminAll(loggedInUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        final UserWrapper user = editingOwnProfile ? loggedInUser
                : userClient.getMemberUnchecked(userId);

        final LoginTokenWrapper loginToken = loginTokenClient.createLoginToken(user.getId());
        new MemberBatchRegistrationNotification(user, loginToken).sendEmailNotification();
        return ResponseEntity.ok(null);
    }
}
