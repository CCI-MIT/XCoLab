package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelCategory;

import java.util.List;
import java.util.Optional;

public interface ModelCategoryDao {

    Optional<IModelCategory> get(long id);

    List<IModelCategory> list();

    IModelCategory create(IModelCategory pojo);

    boolean update(IModelCategory pojo);

    boolean delete(long id);
}
