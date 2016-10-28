package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelOutputChartOrder;

import java.util.List;
import java.util.Optional;

public interface ModelOutputChartOrderDao {

    Optional<ModelOutputChartOrder> get(long id);

    List<ModelOutputChartOrder> list();

    ModelOutputChartOrder create(ModelOutputChartOrder pojo);

    boolean update(ModelOutputChartOrder pojo);

    boolean delete(long id);
}
