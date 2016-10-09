package org.xcolab.client.modeling;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.comm.ClientRepository;
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

	private static ClientRepository instance;
	private static final Object mutex = new Object();

    static {
        repository();
    }
    private RomaClientUtil() {
    }

	public static ClientRepository repository() {
		if (instance == null) {

			synchronized (mutex) {

				if (instance == null) {
					// start in separate thread as container can block waiting
					// for this and if there is no roma-server available it will
					// block forever
					new Thread(new Runnable() {

						@Override
						public void run() {
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
								log.error("Can't find edu.mit.roma.address property");
							}

							log.info("Starting up modeling client ({})", host);
							try {
								instance = ClientRepository.instance(host);

								for (Simulation s : ClientRepository.instance()
										.getAllSimulations()) {
                                    log.info("Loaded... {}", s.getName());
								}
								log.info("Modeling client initialized");
							} catch (Exception e) {
								log.error("Error while loading roma client repository", e);
							}
						}
					}).start();
				}
			}

		}
		return instance;
	}
}
