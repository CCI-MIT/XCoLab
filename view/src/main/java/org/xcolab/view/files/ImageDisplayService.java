package org.xcolab.view.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class ImageDisplayService {

    private static final Logger log = LoggerFactory.getLogger(ImageDisplayService.class);

    private final boolean isProduction;
    private final String basePath = PlatformAttributeKey.FILES_UPLOAD_DIR.get();

    public ImageDisplayService() {
        final ServerEnvironment serverEnvironment =
                PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        isProduction = serverEnvironment == ServerEnvironment.PRODUCTION;
    }

    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId,
            @RequestParam(defaultValue = "NONE") DefaultImage defaultImage) throws IOException {

        final Optional<FileEntry> fileEntryOpt = FilesClient.getFileEntry(imageId);
        if (fileEntryOpt.isPresent()) {
            FileEntry fileEntry = fileEntryOpt.get();
            File imageFile = fileEntry.getImageFile(basePath);
            final boolean success = sendImageToResponse(request, response, imageFile);
            if (!success) {
                if (isProduction) {
                    if (!defaultImage.equals(DefaultImage.NONE)) {
                        response.sendRedirect(defaultImage.getImagePath());
                    } else {
                        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    }
                } else {
                    forwardToImageOnProduction(request, response);
                }
            }
        } else if (!defaultImage.equals(DefaultImage.NONE)) {
            response.sendRedirect(defaultImage.getImagePath());
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Image not found.");
        }
    }

    private void forwardToImageOnProduction(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String newURL = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get()
                + request.getRequestURI() + "?" + request.getQueryString();

        response.sendRedirect(newURL);
    }

    private boolean sendImageToResponse(HttpServletRequest request, HttpServletResponse response,
            File imageFile) {

        if (imageFile == null) {
            return false;
        }

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

}
