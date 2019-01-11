package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelOutputItem;

import java.util.List;
import java.util.Optional;

public interface ModelOutputItemDao {

    Optional<IModelOutputItem> get(long id);

    List<IModelOutputItem> list();

    IModelOutputItem create(IModelOutputItem pojo);

    boolean update(IModelOutputItem pojo);

    boolean delete(long id);
}
