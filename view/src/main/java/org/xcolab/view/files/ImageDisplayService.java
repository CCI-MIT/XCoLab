package org.xcolab.view.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.util.io.ServletFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class ImageDisplayService {

    private static final Logger log = LoggerFactory.getLogger(ImageDisplayService.class);

    private static final int IMAGE_CACHE_MAX_AGE_DAYS = 7;
    private static final int IMAGE_CACHE_STALE_DAYS = 90;
    private static final String BASE_PATH = PlatformAttributeKey.FILES_UPLOAD_DIR.get();

    private final boolean isProduction;

    public ImageDisplayService() {
        final ServerEnvironment serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        isProduction = serverEnvironment == ServerEnvironment.PRODUCTION;
    }

    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            long imageId, ImageType imageType) throws IOException {

        final Optional<FileEntry> fileEntryOpt = FilesClient.getFileEntry(imageId);
        if (fileEntryOpt.isPresent()) {
            FileEntry fileEntry = fileEntryOpt.get();
            File imageFile = fileEntry.getImageFile(BASE_PATH);
            final boolean success = sendImageToResponse(request, response, imageFile);
            if (success) {
                setCacheControlHeader(response);
            } else {
                handleImageNotFoundError(request, response, imageType);
            }
        } else {
            handleFileEntryNotFoundError(request, response, imageId, imageType);
        }
    }

    private void setCacheControlHeader(HttpServletResponse response) {
        response.setHeader(HttpHeaders.CACHE_CONTROL,
                CacheControl.maxAge(IMAGE_CACHE_MAX_AGE_DAYS, TimeUnit.DAYS)
                        .staleWhileRevalidate(IMAGE_CACHE_STALE_DAYS, TimeUnit.DAYS)
                        .getHeaderValue());
    }

    private void handleImageNotFoundError(HttpServletRequest request, HttpServletResponse response,
            ImageType imageType) throws IOException {
        if (isProduction) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            if (!imageType.equals(ImageType.UNKNOWN)) {
                forwardToDefaultImage(request, response, imageType);
            }
        } else {
            redirectToImageOnProduction(request, response);
        }
    }

    private void handleFileEntryNotFoundError(HttpServletRequest request,
            HttpServletResponse response, long imageId, ImageType imageType)
            throws IOException {
        if (!imageType.equals(ImageType.UNKNOWN)) {
            if (imageId > 0) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
            forwardToDefaultImage(request, response, imageType);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Image not found.");
        }
    }

    private void forwardToDefaultImage(HttpServletRequest request, HttpServletResponse response,
            ImageType imageType) {
        try {
            final ServletContext servletContext = request.getServletContext();
            servletContext.getRequestDispatcher(imageType.getImagePath())
                    .forward(request, response);
        } catch (ServletException | IOException e) {
            throw new InternalException("Forwarded request threw exception", e);
        }
    }

    private void redirectToImageOnProduction(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        final String productionUrl = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get();
        String newURL = productionUrl + request.getRequestURI();

        if (request.getQueryString() != null) {
            newURL += "?" + request.getQueryString();
        }

        response.sendRedirect(newURL);
    }

    private boolean sendImageToResponse(HttpServletRequest request, HttpServletResponse response,
            File imageFile) {

        if (imageFile == null) {
            return false;
        }

        try {
            final String mimeType = ServletFileUtil.resolveMimeType(request, imageFile);
            ServletFileUtil.sendFileToResponse(response, imageFile, mimeType);
            return true;
        } catch (IOException e) {
            log.error("Error while sending image {} to response: {}",
                    imageFile.getAbsolutePath(), e.getMessage());
            return false;
        }
    }
}
