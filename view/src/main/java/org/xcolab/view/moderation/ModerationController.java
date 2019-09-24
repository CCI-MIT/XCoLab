package org.xcolab.view.moderation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.moderation.TargetType;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ModerationController {

    @Autowired
    private IModerationClient moderationClient;

    @PostMapping("/flagging/report")
    public ResponseJson report(HttpServletRequest request, HttpServletResponse response,
            @RequestParam TargetType targetType, @RequestParam long targetAdditionalId,
            @RequestParam String reason, @RequestParam String comment, @RequestParam long targetId,
            UserWrapper loggedInMember) throws UnsupportedEncodingException {

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
