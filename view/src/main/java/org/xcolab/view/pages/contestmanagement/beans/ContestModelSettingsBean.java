package org.xcolab.view.pages.contestmanagement.beans;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.modeling.roma.RomaClient;
import org.xcolab.client.modeling.roma.RomaClient.RomaClientNotConnectedException;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.html.LabelStringValue;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.view.util.entity.enums.ModelRegions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContestModelSettingsBean implements Serializable {

    private Long defaultModelId;
    private List<Long> otherModelIds;
    private String otherModels;
    private String defaultModelSettings;
    private String modelRegion;

    public ContestModelSettingsBean() { }

    public ContestModelSettingsBean(ContestWrapper contest) {
        this.defaultModelId = contest.getDefaultModelId();
        this.otherModels = contest.getOtherModels();
        this.defaultModelSettings = contest.getDefaultModelSettings();
        this.modelRegion = "";
        this.otherModelIds = new ArrayList<>();
        initOtherModelsAndRegion();
    }

    private void initOtherModelsAndRegion() {
        if (StringUtils.isNotEmpty(this.otherModels)) {
            for (String otherModelId : this.otherModels.split(",")) {
                otherModelIds.add(Long.parseLong(otherModelId.trim()));
            }
        }
        this.modelRegion = getRegionFromDefaultModelSettings(this.defaultModelSettings);
    }

    private String getRegionFromDefaultModelSettings(String defaultModelSettingsString) {
        if (StringUtils.isBlank(defaultModelSettingsString)) {
            return "";
        }
        JSONObject defaultModelSettings = new JSONObject(defaultModelSettingsString);
        return defaultModelSettings.has("region") ? defaultModelSettings.getString("region") : "";
    }

    public static List<LabelValue> getAllModelIds() {
        try {
            final RomaClient repository = RomaClientUtil.client();
            List<Simulation> simulationsSorted = new ArrayList<>(repository.getAllSimulations());
            simulationsSorted.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
            List<LabelValue> allModelIds = new ArrayList<>();
            for (Simulation simulation : simulationsSorted) {
                allModelIds.add(new LabelValue(simulation.getId(),
                        "(Id: " + simulation.getId() + ") " + simulation.getName()));
            }
            return allModelIds;
        } catch (RomaClientNotConnectedException e) {
            return Collections.emptyList();
        }
    }

    public static List<LabelStringValue> getAllModelRegions() {
        List<LabelStringValue> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new LabelStringValue(modelRegion.getModelRegionName(),
                    modelRegion.getModelRegionTitle()));
        }
        return modelRegions;
    }

    public String getModelRegion() {
        return modelRegion;
    }

    public void setModelRegion(String modelRegion) {
        this.modelRegion = modelRegion;
        if (StringUtils.isBlank(modelRegion)) {
            this.defaultModelSettings = "";
        } else {
            JSONObject defaultModelSettings = new JSONObject();
            defaultModelSettings.put("region", modelRegion);
            this.defaultModelSettings = defaultModelSettings.toString();
        }
    }

    public Long getDefaultModelId() {
        return defaultModelId;
    }

    public void setDefaultModelId(Long defaultModelId) {
        this.defaultModelId = defaultModelId;
    }

    public String getOtherModels() {
        return otherModels;
    }

    public void setOtherModels(String otherModelIds) {
        if (otherModelIds != null) {
            this.otherModels = otherModelIds.replaceAll("[^0-9,]", "").replaceAll(",,", ",");
        }
    }

    public List<Long>
    getOtherModelIds() {
        return otherModelIds;
    }

    public void setOtherModelIds(List<Long> otherModelIds) {
        if (otherModelIds == null) {
            this.otherModelIds = new ArrayList<>();
        } else {
            this.otherModelIds = otherModelIds;
        }
        this.otherModels =
                this.otherModelIds.toString().replaceAll("\\[", "").replaceAll("\\]", "");
    }

    public String getDefaultModelSettings() {
        return defaultModelSettings;
    }

    public void setDefaultModelSettings(String defaultModelSettings) {
        if (defaultModelSettings != null) {
            this.defaultModelSettings = defaultModelSettings.replaceAll("'", "\"");
        }
    }

    public void persist(ContestWrapper contest) {
        if (otherModels != null) {
            contest.setOtherModels(otherModels);
        }
        if (defaultModelId != null) {
            contest.setDefaultModelId(defaultModelId);
        }
        if (defaultModelSettings != null) {
            contest.setDefaultModelSettings(defaultModelSettings);
        }
        StaticContestContext.getContestClient().updateContest(contest);
    }
}
