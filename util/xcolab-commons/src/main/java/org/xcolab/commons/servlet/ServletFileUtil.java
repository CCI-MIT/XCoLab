package org.xcolab.commons.servlet;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
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

    public static String resolveMimeType(HttpServletRequest request, File file)
            throws IOException {
        Magic magic = new Magic();
        try {
            MagicMatch match = magic.getMagicMatch(file, false);
            final String mimeType = match.getMimeType();
            if (mimeType == null) {
                throw new IOException("Cannot resolve mime type for file " + file.getAbsolutePath());
            }
            return mimeType;
        } catch (MagicParseException | MagicException | MagicMatchNotFoundException e) {
            throw new IOException("Cannot resolve mime type for file " + file.getAbsolutePath());
        }
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
