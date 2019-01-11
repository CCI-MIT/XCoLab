package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;

import java.util.List;
import java.util.Optional;

public interface ModelOutputChartOrderDao {

    Optional<IModelOutputChartOrder> get(long id);

    List<IModelOutputChartOrder> list();

    List<IModelOutputChartOrder> findByGiven(Long modelId, String label);

    IModelOutputChartOrder create(IModelOutputChartOrder pojo);

    boolean update(IModelOutputChartOrder pojo);

    boolean delete(long id);
}
