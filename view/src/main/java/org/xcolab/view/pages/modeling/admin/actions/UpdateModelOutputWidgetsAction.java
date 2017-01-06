package org.xcolab.view.pages.modeling.admin.actions;

import edu.mit.cci.roma.client.Simulation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputIndexedDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputSeriesType;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.view.pages.modeling.admin.ModelsAdminController;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelOutputWidgetsBean;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/modeling")
public class UpdateModelOutputWidgetsAction {

    @PostMapping("model/{modelId}/updateOutputs")
    public void update(HttpServletRequest request, HttpServletResponse response,
            UpdateModelOutputWidgetsBean updateModelWidgetsBean, @PathVariable long modelId)
            throws IllegalUIConfigurationException, IOException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
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
        response.sendRedirect(ModelsAdminController.getTabMapping(modelId, "outputWidgets"));
    }

}
