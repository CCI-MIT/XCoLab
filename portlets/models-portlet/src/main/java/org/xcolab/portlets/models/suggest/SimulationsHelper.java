package org.xcolab.portlets.models.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.comm.ClientRepository;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;

public class SimulationsHelper {
    
    private static SimulationsHelper instance;
    
    private ClientRepository repository;
    private List<SimulationDecorator> simulations;
    private Map<Long, SimulationDecorator> simulationsById = new HashMap<Long, SimulationDecorator>();



    private SimulationsHelper() throws IOException, SystemException, PortalException {

        repository = CollaboratoriumModelingService.repository();
        simulations = new ArrayList<SimulationDecorator>();


        for (Simulation sim: repository.getAllSimulations()) {
            if (simulationsById.containsKey(sim.getId())) continue;
            SimulationDecorator decorator = new SimulationDecorator(sim);
            simulations.add(decorator);
            simulationsById.put(decorator.getId(), decorator);
        }
        
        Collections.sort(simulations, new Comparator<SimulationDecorator>() {

            @Override
            public int compare(SimulationDecorator o1, SimulationDecorator o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
    
    
    public static synchronized SimulationsHelper getInstance() throws IOException, SystemException, PortalException {
        if (instance == null) {
            instance = new SimulationsHelper();
        }
        return instance;
    }
    
    public List<SimulationDecorator> getSimulations() {
        return simulations;
    }


    
    public SimulationDecorator getSimulationById(long id) {
        return simulationsById.get(id);
    }
    
    public Scenario getScenarioById(long id) {
        if (id <= 0) {
            return null;
        }
        Scenario scenario = null;
        try {
            scenario = repository.getScenario(id);
        } catch (Throwable e) {
            // ignore
            e.printStackTrace();
        }
        return scenario;
    }
    
    public ClientRepository getRepository() {
        return repository;
    }
    

    public Scenario runSimulation(Simulation sim, Map<Long, Object> inputs) throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        return repository.runModel(sim, inputs, 10144L, false);
    }


    public void saveScenario(Scenario scenario) throws ScenarioNotFoundException, IOException {
        //repository.saveScenario(scenario);
        // FIXME we had save scenario in previous interface, should work out its support in current version
    }

    
}
