package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import edu.mit.cci.roma.client.Simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thomas on 6/16/2015.
 */
public class ContestModelSettingsBean  implements Serializable {
    private final static Log _log = LogFactoryUtil.getLog(ContestModelSettingsBean.class);
    private Long defaultModelId;
    private String otherModelIds;
    private String defaultModelSettings;

    public ContestModelSettingsBean() {
    }

    public Long getDefaultModelId() {
        return defaultModelId;
    }

    public void setDefaultModelId(Long defaultModelId) {
        this.defaultModelId = defaultModelId;
    }

    public String getOtherModelIds() {
        return otherModelIds;
    }

    public void setOtherModelIds(String otherModelIds) {
        if(otherModelIds != null) {
            String removeEverythingButNumbersAndCommas = otherModelIds.replaceAll("[^0-9,]", "").replaceAll(",,", ",");
            this.otherModelIds = removeEverythingButNumbersAndCommas;
        }
    }

    public String getDefaultModelSettings() {
        return defaultModelSettings;
    }

    public void setDefaultModelSettings(String defaultModelSettings) {
        if(defaultModelSettings != null) {
            String defaultModelSettingsWithSingleQuotationMarks = defaultModelSettings.replaceAll("\"", "'");
            this.defaultModelSettings = defaultModelSettingsWithSingleQuotationMarks;
        }
    }

    public void persist(Contest contest) throws Exception{
        if(!otherModelIds.isEmpty()){
            contest.setOtherModels(otherModelIds);
        }
        if(defaultModelId != null){
            contest.setDefaultModelId(defaultModelId);
        }
        if(defaultModelSettings != null){
            contest.setDefaultModelSettings(defaultModelSettings);
        }
        contest.persist();
    }

    public static List<LabelValue> getAllModelIds() {
        List<LabelValue> allModelIds = new ArrayList<>();
        try {
            List<Simulation> simulationsSorted = new ArrayList<Simulation>(CollaboratoriumModelingService.repository().getAllSimulations());
            Collections.sort(simulationsSorted, new Comparator<Simulation>() {
                @Override
                public int compare(Simulation o1, Simulation o2) {
                    return (int) (o2.getId() - o1.getId());
                }
            });
            for (Simulation simulation : simulationsSorted) {
                allModelIds.add(new LabelValue(simulation.getId(), "(Id: " + simulation.getId() + ") " + simulation.getName()));
            }
        } catch (Exception e){
            _log.warn("Couldn't fetch contest model Ids.", e);
        }
        return allModelIds;
    }
}
