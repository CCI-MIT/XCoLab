package org.xcolab.client.modeling.roma;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.comm.ClientRepository;
import edu.mit.cci.roma.client.comm.MetaDataNotFoundException;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.RepositoryManager;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class RomaClient {

    private final String host;
    private final ClientRepository clientRepository;

    public RomaClient(String host) {
        this.host = host;
        if (StringUtils.isNotBlank(host)) {
            clientRepository = ClientRepositoryInitializationUtil.load(host);
        } else {
            clientRepository = null;
        }
    }

    public boolean isConnected() {
        return clientRepository != null;
    }

    private ClientRepository repositoryOrThrow() {
        if (clientRepository == null) {
            throw new RomaClientNotConnectedException(host);
        }
        return clientRepository;
    }

    public RepositoryManager getManager() {
        return repositoryOrThrow().getManager();
    }

    public Collection<Simulation> getAllSimulations() {
        return repositoryOrThrow().getAllSimulations();
    }

    public Simulation getSimulation(Long id) throws IOException {
        return repositoryOrThrow().getSimulation(id);
    }

    public MetaData getMetaData(Long id) throws IOException {
        return repositoryOrThrow().getMetaData(id);
    }

    public Scenario getScenario(Long id) throws IOException {
        return repositoryOrThrow().getScenario(id);
    }

    public Set<Simulation> getSimulationsOfType(String type) {
        return repositoryOrThrow().getSimulationsOfType(type);
    }

    public Scenario runModel(Simulation s,
            Map<Long, Object> inputs, Long userId, boolean save)
            throws ModelNotFoundException, IOException, ScenarioNotFoundException {
        return repositoryOrThrow().runModel(s, inputs, userId, save);
    }

    public Scenario runModelWithInputNames(Simulation s,
            Map<String, Object> inputs, Long userid, boolean save)
            throws ModelNotFoundException, IOException, ScenarioNotFoundException, MetaDataNotFoundException {
        return repositoryOrThrow().runModelWithInputNames(s, inputs, userid, save);
    }

    @Override
    public String toString() {
        return "RomaClient{" +
                "host='" + host + '\'' +
                "initialized='" + (clientRepository != null) + "'" +
                '}';
    }

    public static class RomaClientNotConnectedException extends IllegalStateException {
        public RomaClientNotConnectedException(String host) {
            super("ROMA ClientRepository not connected to host " + host);
        }
    }
}
