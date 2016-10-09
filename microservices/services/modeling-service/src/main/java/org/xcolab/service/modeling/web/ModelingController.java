package org.xcolab.service.modeling.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ModelGlobalPreference;
import org.xcolab.service.modeling.domain.ModelGlobalPreferenceDao;

import java.util.Collections;
import java.util.List;

@RestController
public class ModelingController {

    private final ModelGlobalPreferenceDao modelGlobalPreferenceDao;

    public ModelingController(ModelGlobalPreferenceDao modelGlobalPreferenceDao) {
        this.modelGlobalPreferenceDao = modelGlobalPreferenceDao;
    }

    @GetMapping("/models/{modelId}/preferences")
    public List<ModelGlobalPreference> listPreferences(@PathVariable long modelId) {
        return Collections.singletonList(modelGlobalPreferenceDao.getByModelId(modelId)
                .orElseGet(() -> {
                    ModelGlobalPreference modelGlobalPreference = new ModelGlobalPreference();
                    modelGlobalPreference.setModelId(modelId);
                    modelGlobalPreference.setUsesCustomInputs(false);
                    return modelGlobalPreferenceDao.create(modelGlobalPreference);
                }));
    }
}
