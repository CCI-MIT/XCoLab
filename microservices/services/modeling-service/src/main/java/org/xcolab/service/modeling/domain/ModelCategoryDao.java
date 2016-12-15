package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelCategory;

import java.util.List;
import java.util.Optional;

public interface ModelCategoryDao {

    Optional<ModelCategory> get(long id);

    List<ModelCategory> list();

    ModelCategory create(ModelCategory pojo);

    boolean update(ModelCategory pojo);

    boolean delete(long id);
}
