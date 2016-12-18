package org.xcolab.client.modeling.roma;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class RomaClientUtil {

	private static final Logger log = LoggerFactory.getLogger(RomaClientUtil.class);

	private static RomaClient romaClient;
    private static final Object mutex = new Object();

    private RomaClientUtil() {
    }

    public static void initialize() {
        synchronized (mutex) {
            if (romaClient == null) {
                // try to read configuration from default location
                final String propertiesPath = System.getProperty("user.home") + File.separator
                        + ".xcolab.application.properties";
                String host;
                try (InputStream inputStream = new FileInputStream(propertiesPath)) {
                    Properties prop = new Properties();
                    prop.load(inputStream);
                    host = prop.getProperty("edu.mit.roma.address", "");
                } catch (IOException e) {
                    host = "";
                }
                if (StringUtils.isBlank(host)) {
                    log.warn("Can't find edu.mit.roma.address property");
                }
                romaClient = new RomaClient(host);
            }
        }
	}

	public static RomaClient client() {
        if (romaClient == null) {
            initialize();
        }
		return romaClient;
	}
}
