package org.xcolab.portlets.modelsadmin.web;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.modeling.RomaClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputIndexedDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesType;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.portlets.modelsadmin.web.form.UpdateModelOutputWidgetsBean;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@RequestMapping("view")
@Controller
public class UpdateModelOutputWidgetsAction {

    @RequestMapping(params = {"action=updateOutputs", "modelId", "tab=outputWidgets"})
    public void update(ActionRequest request, ActionResponse response,
            UpdateModelOutputWidgetsBean updateModelWidgetsBean,
            @RequestParam Long modelId)
            throws SystemException, IllegalUIConfigurationException, IOException {

        Simulation simulation = RomaClientUtil.repository().getSimulation(modelId);
        ModelDisplay modelDisplay = ModelUIFactory.getInstance().getDisplay(simulation);


        for (ModelOutputDisplayItem item : ModelsAdminController
                .getAllOutputsFromDisplay(modelDisplay)) {
            if (item instanceof ModelOutputSeriesDisplayItem) {
                ModelOutputSeriesDisplayItem series = (ModelOutputSeriesDisplayItem) item;
                long id = series.getMetaData().getId();
                Map<Long, Long> associated = updateModelWidgetsBean.getAssociatedMetaDatas();
                Map<Long, Integer> orders = updateModelWidgetsBean.getOrdersById();
                Map<Long, ModelOutputSeriesType> serieTypes =
                        updateModelWidgetsBean.getSerieTypes();
                if (associated.containsKey(id)) {
                    series.setAssociatedMetaDataId(associated.get(id));
                }
                if (orders.containsKey(id)) {
                    series.setOrder(orders.get(id));
                }
                if (serieTypes.containsKey(id)) {
                    series.setSeriesType(serieTypes.get(id));
                }
            } else if (item instanceof ModelOutputIndexedDisplayItem) {
                ModelOutputIndexedDisplayItem indexed = (ModelOutputIndexedDisplayItem) item;
                Map<String, Integer> orders = updateModelWidgetsBean.getOrdersByName();

                if (orders.containsKey(indexed.getName())) {
                    indexed.setOrder(orders.get(indexed.getName()));
                }

            }

        }

    }

}
