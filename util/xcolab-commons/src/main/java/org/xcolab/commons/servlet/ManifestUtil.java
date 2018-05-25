package org.xcolab.commons.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;

public final class ManifestUtil {

    private static final Logger log = LoggerFactory.getLogger(ManifestUtil.class);

    private static final String MANIFEST_FILE = "/META-INF/MANIFEST.MF";
    private static final Attributes.Name MANIFEST_ATTRIBUTE_GIT_COMMIT
            = new Attributes.Name("Git-Commit");

    private ManifestUtil() {
    }

    public static Optional<String> getBuildCommit(ServletContext servletContext) {
        try (final InputStream manifestInputStream =
                servletContext.getResourceAsStream(MANIFEST_FILE)) {
            if (manifestInputStream != null) {
                final Manifest manifest = new Manifest(manifestInputStream);
                final Attributes mainAttributes = manifest.getMainAttributes();
                if (mainAttributes.containsKey(MANIFEST_ATTRIBUTE_GIT_COMMIT)) {
                    return Optional.of((String) mainAttributes.get(MANIFEST_ATTRIBUTE_GIT_COMMIT));
                } else {
                    log.warn("Manifest does not contain 'Git-Commit' attribute.");
                }
            }
        } catch (IOException e) {
            log.error("Exception while opening input stream for manifest: {}", e.getMessage() );
        }
        return Optional.empty();
    }
}
