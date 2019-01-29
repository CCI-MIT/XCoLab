package org.xcolab.view.files;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LegacyImageDisplayController {

    private final ImageDisplayService imageDisplayService;

    private final IUserClient userClient;

    public LegacyImageDisplayController(ImageDisplayService imageDisplayService, IUserClient userClient) {
        this.imageDisplayService = imageDisplayService;
        this.userClient = userClient;
    }

    @GetMapping("/image/contest/{imageId}")
    public void serveContestImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, ImageType.CONTEST);
    }

    @GetMapping("/image/proposal/{imageId}")
    public void serveProposalImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, ImageType.PROPOSAL);
    }

    @GetMapping("/image/member/{imageId}")
    public void serveMemberImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, ImageType.MEMBER);
    }

    @GetMapping({"/image/{whatever}", "/image"})
    public void serveImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "img_id", required = false) Long imgId,
            @RequestParam(required = false) Long portraitId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false, defaultValue = "UNKNOWN") ImageType defaultImage)
            throws IOException {

        if (userId != null) {
            try {
                UserWrapper member = userClient.getMember(userId);
                imageDisplayService.serveImage(request, response,
                        member.getPortraitId(), ImageType.PROPOSAL);
            } catch (MemberNotFoundException e) {
                response.sendError(HttpStatus.NOT_FOUND.value(), "Member not found.");
            }
            return;
        }

        final Long imageId;
        if (imgId != null) {
            imageId = imgId;
        } else if (portraitId != null) {
            imageId = portraitId;
        } else {
            imageId = 0L;
        }

        if (request.getRequestURI().contains("user_male_portrait")) {
            defaultImage = ImageType.MEMBER;
        }

        imageDisplayService.serveImage(request, response, imageId, defaultImage);
    }
}
