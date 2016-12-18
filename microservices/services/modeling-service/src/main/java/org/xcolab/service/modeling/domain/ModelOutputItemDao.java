package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelOutputItem;

import java.util.List;
import java.util.Optional;

public interface ModelOutputItemDao {

    Optional<ModelOutputItem> get(long id);

    List<ModelOutputItem> list();

    ModelOutputItem create(ModelOutputItem pojo);

    boolean update(ModelOutputItem pojo);

    boolean delete(long id);
}
