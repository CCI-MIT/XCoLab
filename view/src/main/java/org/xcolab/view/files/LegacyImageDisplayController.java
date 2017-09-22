package org.xcolab.view.files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LegacyImageDisplayController {

    private final ImageDisplayService imageDisplayService;
    private final ImageDisplayController imageDisplayController;

    public LegacyImageDisplayController(ImageDisplayService imageDisplayService,
            ImageDisplayController imageDisplayController) {
        this.imageDisplayService = imageDisplayService;
        this.imageDisplayController = imageDisplayController;
    }

    @GetMapping("/image/contest/{imageId}")
    public void serveContestImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, DefaultImage.CONTEST);
    }

    @GetMapping("/image/proposal/{imageId}")
    public void serveProposalImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, DefaultImage.PROPOSAL);
    }

    @GetMapping("/image/member/{imageId}")
    public void serveMemberImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        imageDisplayService.serveImage(request, response, imageId, DefaultImage.MEMBER);
    }

    @GetMapping({"/image/{whatever}", "/image"})
    public void serveImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "img_id", required = false) Long imgId,
            @RequestParam(required = false) Long portraitId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false, defaultValue = "NONE") DefaultImage defaultImage)
            throws IOException {

        if (userId != null) {
            imageDisplayController.serveMemberImage(request, response, userId);
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
            defaultImage = DefaultImage.MEMBER;
        }

        imageDisplayService.serveImage(request, response, imageId, defaultImage);
    }
}
