package org.xcolab.client.modeling.roma;

import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.comm.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class ClientRepositoryInitializationUtil {

    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryInitializationUtil.class);

    private static final int TIMEOUT_IN_SECONDS = 15;

    private ClientRepositoryInitializationUtil() {
    }

    static ClientRepository load(String host) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ClientRepository> future = executor.submit(new InitTask(host));
        ClientRepository clientRepository = null;
        try {
            log.warn("Initializing ROMA ClientRepository - may take up to {} seconds", TIMEOUT_IN_SECONDS);
            long startTime = System.nanoTime();
            clientRepository = future.get(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            long endTime = System.nanoTime();
            log.info("ROMA ClientRepository initialized in {} ms", (endTime - startTime) / 1000_000);
        } catch (TimeoutException e) {
            future.cancel(true);
            log.error("ROMA ClientRepository initialization timed out");
        } catch (ExecutionException e) {
            log.error("Exception occurred while initializing ROMA ClientRepository", e.getCause());
        } catch (InterruptedException e) {
            future.cancel(true);
        }

        executor.shutdownNow();
        return clientRepository;
    }

    private static class InitTask implements Callable<ClientRepository> {

        private static final Logger log = LoggerFactory.getLogger(InitTask.class);
        private final String host;

        public InitTask(String host) {
            this.host = host;
        }

        @Override
        public ClientRepository call() throws IOException {

            log.info("Starting up modeling client ({})", host);
            ClientRepository instance = ClientRepository.instance(host);

            for (Simulation s : instance.getAllSimulations()) {
                log.info("Loaded... {}", s.getName());
            }
            log.info("Modeling client initialized");
            return instance;
        }
    }
}
