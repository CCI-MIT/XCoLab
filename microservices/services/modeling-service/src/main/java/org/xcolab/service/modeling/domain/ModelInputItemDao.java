package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelInputItem;

import java.util.List;
import java.util.Optional;

public interface ModelInputItemDao {

    Optional<ModelInputItem> get(long id);

    List<ModelInputItem> list();

    ModelInputItem create(ModelInputItem pojo);

    boolean update(ModelInputItem pojo);

    boolean delete(long id);
}
