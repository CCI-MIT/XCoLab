package org.xcolab.view.files;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ImageDisplayController {

    private final boolean isProduction;
    private final String path = PlatformAttributeKey.FILES_UPLOAD_DIR.get();

    public ImageDisplayController() {
        final ServerEnvironment serverEnvironment =
                PlatformAttributeKey.PLATFORM_SERVER_ENVIRONMENT.get();
        isProduction = serverEnvironment == ServerEnvironment.PRODUCTION;
    }

    @GetMapping("/image/contest/{imageId}")
    public void serveContestImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        serveImage(request, response, imageId, null, null, DefaultImage.CONTEST);
    }

    @GetMapping("/image/proposal/{imageId}")
    public void serveProposalImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId) throws IOException {
        serveImage(request, response, imageId, null, null, DefaultImage.PROPOSAL);
    }

    @GetMapping("/image/member/{memberId}")
    public void serveMemberImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long memberId) throws IOException {
        serveImage(request, response, null, null, memberId, DefaultImage.MEMBER);
    }

    @GetMapping({"/image/{whatever}", "/image"})
    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "img_id", required = false) Long imgId,
            @RequestParam(required = false) Long portraitId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false, defaultValue = "NONE") DefaultImage defaultImage)
            throws IOException {
        Long imageId = null;

        if (imgId != null) {
            imageId = imgId;
        } else if (userId != null) {
            try {
                Member member =
                        MembersClient.getMember(Long.parseLong(request.getParameter("userId")));
                if (member.getPortraitFileEntryId() != null) {
                    imageId = member.getPortraitFileEntryId();
                }
            } catch (MemberNotFoundException ignored) {
            }
        }

        if (imageId == null && portraitId != null) {
            imageId = portraitId;
        }

        if (imageId != null && imageId > 0) {
            try {
                FileEntry fileEntry = FilesClient.getFileEntry(imageId);
                String filePath = FilesClient.getFilePathFromFinalDestination(fileEntry, path);
                if (filePath != null) {
                    //check if file exists
                    File f = new File(filePath);
                    if (f.exists() && !f.isDirectory()) {
                        sendImageToResponse(request, response, filePath);
                        return;
                    } else {
                        if (!isProduction) {
                            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                            String newURL =
                                    ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get() + request
                                            .getRequestURI() + "?" + request.getQueryString();
                            response.setHeader("Location", newURL);
                            return;
                        }
                    }
                }
            } catch (FileEntryNotFoundException ignored) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        }

        if (request.getRequestURI().contains("user_male_portrait")) {
            defaultImage = DefaultImage.MEMBER;
        }

        if (!defaultImage.equals(DefaultImage.NONE)) {
            response.sendRedirect(defaultImage.getImagePath());
            return;
        }
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private void sendImageToResponse(HttpServletRequest request, HttpServletResponse response,
            String filePath) {

        try {
            ServletContext servletContext = request.getSession().getServletContext();
            String mime = servletContext.getMimeType(filePath);
            if (mime == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            response.setContentType(mime);
            File file = new File(filePath);
            response.setContentLength((int) file.length());

            FileInputStream in = new FileInputStream(file);
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
                    CacheControl.maxAge(7, TimeUnit.DAYS).getHeaderValue());
            return;
        } catch (IOException ignored) {

        }
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException ignored) {

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
