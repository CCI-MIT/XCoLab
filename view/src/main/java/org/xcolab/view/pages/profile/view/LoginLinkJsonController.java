package org.xcolab.view.pages.profile.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.LoginToken;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;

@RestController
@RequestMapping("/members/profile/{userId}/api/login")
public class LoginLinkJsonController {

    @PostMapping("generateNewToken")
    public ResponseEntity<Void> generateNewLoginToken(@PathVariable long userId,
            Member loggedInUser) {

        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final boolean editingOwnProfile = userId == loggedInUser.getId();
        if (!editingOwnProfile && !PermissionsClient.canAdminAll(loggedInUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        final Member user = editingOwnProfile ? loggedInUser
                : MembersClient.getMemberUnchecked(userId);

        final LoginToken loginToken = MembersClient.createLoginToken(user.getId());
        new MemberBatchRegistrationNotification(user, loginToken).sendEmailNotification();
        return ResponseEntity.ok(null);
    }
}
