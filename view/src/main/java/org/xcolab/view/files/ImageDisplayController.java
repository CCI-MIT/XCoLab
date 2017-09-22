package org.xcolab.view.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ImageDisplayController {

    private static final Logger log = LoggerFactory.getLogger(ImageDisplayController.class);

    private final boolean isProduction;
    private final String basePath = PlatformAttributeKey.FILES_UPLOAD_DIR.get();

    public ImageDisplayController() {
        final ServerEnvironment serverEnvironment =
                PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        isProduction = serverEnvironment == ServerEnvironment.PRODUCTION;
    }

    @GetMapping("/image/contests/{contestId}")
    public void serveContestImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long contestId) throws IOException {
        Contest contest = ContestClientUtil.getContest(contestId);
        serveImage(request, response, contest.getContestLogoId(), DefaultImage.CONTEST);
    }

    @GetMapping("/image/proposals/{proposalId}")
    public void serveProposalImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long proposalId) throws IOException {
        Proposal proposal = ProposalClientUtil.getProposal(proposalId);
        serveImage(request, response, proposal.getImageId(), DefaultImage.PROPOSAL);
    }

    @GetMapping("/image/members/{memberId}")
    public void serveMemberImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long memberId) throws IOException {
        Member member = MembersClient.getMemberUnchecked(memberId);
        serveImage(request, response, member.getPortraitId(), DefaultImage.PROPOSAL);
    }

    @GetMapping("/image/uploaded/{imageId}")
    public void serveImage(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long imageId,
            @RequestParam(defaultValue = "NONE") DefaultImage defaultImage) throws IOException {

        if (imageId > 0) {
            final Optional<FileEntry> fileEntryOpt = FilesClient.getFileEntry(imageId);
            if (fileEntryOpt.isPresent()) {
                FileEntry fileEntry = fileEntryOpt.get();
                File imageFile = fileEntry.getImageFile(basePath);
                if (imageFile != null) {
                    final boolean success = sendImageToResponse(request, response, imageFile);
                    if (success) {
                        return;
                    }
                } else if (!isProduction) {
                    String newURL = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get()
                            + request.getRequestURI() + "?" + request.getQueryString();

                    response.sendRedirect(newURL);
                    return;
                }
            }
        }

        if (!defaultImage.equals(DefaultImage.NONE)) {
            response.sendRedirect(defaultImage.getImagePath());
            return;
        }
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @GetMapping("/image/contest/{imageId}")
    @Deprecated
    public void serveContestImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        serveImage(request, response, imageId, DefaultImage.CONTEST);
    }

    @GetMapping("/image/proposal/{imageId}")
    @Deprecated
    public void serveProposalImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        serveImage(request, response, imageId, DefaultImage.PROPOSAL);
    }

    @GetMapping("/image/member/{imageId}")
    @Deprecated
    public void serveMemberImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        serveImage(request, response, imageId, DefaultImage.MEMBER);
    }

    @GetMapping({"/image/{whatever}", "/image"})
    public void serveImageLegacy(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "img_id", required = false) Long imgId,
            @RequestParam(required = false) Long portraitId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false, defaultValue = "NONE") DefaultImage defaultImage)
            throws IOException {

        if (userId != null) {
            serveMemberImage(request, response, userId);
            return;
        }

        Long imageId = null;
        if (imgId != null) {
            imageId = imgId;
        }

        if (imageId == null) {
            if (portraitId != null) {
                imageId = portraitId;
            } else {
                imageId = 0L;
            }
        }

        if (request.getRequestURI().contains("user_male_portrait")) {
            defaultImage = DefaultImage.MEMBER;
        }

        serveImage(request, response, imageId, defaultImage);
    }

    private boolean sendImageToResponse(HttpServletRequest request, HttpServletResponse response,
            File imageFile) {

        try {
            ServletContext servletContext = request.getSession().getServletContext();
            String mime = servletContext.getMimeType(imageFile.getAbsolutePath());
            if (mime == null) {
                log.error("Could not retrieve mime typ for image {}", imageFile.getAbsolutePath());
                return false;
            }

            response.setContentType(mime);
            response.setContentLength((int) imageFile.length());

            FileInputStream in = new FileInputStream(imageFile);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
            response.setHeader(HttpHeaders.CACHE_CONTROL,
                    CacheControl.maxAge(7, TimeUnit.DAYS)
                            .staleWhileRevalidate(90, TimeUnit.DAYS)
                            .getHeaderValue());
            return true;
        } catch (IOException e) {
            log.error("Error while sending image {} to response: {}",
                    imageFile.getAbsolutePath(), e.getMessage());
            return false;
        }
    }

    public enum DefaultImage {
        NONE(""),
        MEMBER("/images/user_default.png"),
        PROPOSAL("/images/proposal_default.png"),
        CONTEST("/images/proposal_default.png");

        private final String imagePath;

        DefaultImage(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
}
