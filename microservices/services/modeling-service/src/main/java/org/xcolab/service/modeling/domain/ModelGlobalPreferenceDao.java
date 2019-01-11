package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelGlobalPreference;

import java.util.Optional;

public interface ModelGlobalPreferenceDao {

    IModelGlobalPreference create(IModelGlobalPreference modelGlobalPreference);

    Optional<IModelGlobalPreference> get(long id);

    Optional<IModelGlobalPreference> getByModelId(long modelId);

    boolean update(IModelGlobalPreference pojo);
}
