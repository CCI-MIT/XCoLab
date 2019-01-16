package org.xcolab.view.moderation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.moderation.TargetType;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ModerationController {

    private static IModerationClient moderationClient;

    public static void setModerationClient(IModerationClient moderationClient) {
        ModerationController.moderationClient = moderationClient;
    }

    @PostMapping("/flagging/report")
    public ResponseJson report(HttpServletRequest request, HttpServletResponse response,
            @RequestParam TargetType targetType, @RequestParam long targetAdditionalId,
            @RequestParam String reason, @RequestParam String comment, @RequestParam long targetId,
            Member loggedInMember) throws UnsupportedEncodingException {

        request.setCharacterEncoding("UTF-8");

        moderationClient
                .report(loggedInMember, targetId, targetAdditionalId, targetType, reason, comment);

        return new ResponseJson(true);
    }

    private static class ResponseJson {

        private final boolean success;

        private ResponseJson(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
