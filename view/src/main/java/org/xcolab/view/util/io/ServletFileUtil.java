package org.xcolab.view.util.io;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ServletFileUtil {

    private ServletFileUtil() {
    }

    public static String resolveMimeType(HttpServletRequest request, File file) throws IOException {
        final ServletContext servletContext = request.getServletContext();
        final String mimeType = servletContext.getMimeType(file.getAbsolutePath());
        if (mimeType == null) {
            throw new IOException("Cannot resolve mime type for file " + file.getAbsolutePath());
        }
        return mimeType;
    }

    public static void sendFileToResponse(HttpServletResponse response, File imageFile,
            String mimeType) throws IOException {
        response.setContentType(mimeType);

        try (FileInputStream in = new FileInputStream(imageFile)) {
            final int count = IOUtils.copy(in, response.getOutputStream());
            response.setContentLength(count);
        }
    }
}
