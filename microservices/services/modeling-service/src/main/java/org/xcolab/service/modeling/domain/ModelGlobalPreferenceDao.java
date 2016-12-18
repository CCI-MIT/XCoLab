package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelGlobalPreference;

import java.util.Optional;

public interface ModelGlobalPreferenceDao {

    ModelGlobalPreference create(ModelGlobalPreference modelGlobalPreference);

    Optional<ModelGlobalPreference> get(long id);

    Optional<ModelGlobalPreference> getByModelId(long modelId);

    boolean update(ModelGlobalPreference pojo);
}
