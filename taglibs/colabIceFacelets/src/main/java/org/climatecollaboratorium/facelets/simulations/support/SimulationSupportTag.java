package org.climatecollaboratorium.facelets.simulations.support;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SimulationSupportTag extends TagHandler {
    private final TagAttribute simulationBeanParam;
    private final TagAttribute simulationIdParam;
    private final TagAttribute scenarioIdParam;
    private final TagAttribute editActionsParam;
    
    private static Log _log = LogFactoryUtil.getLog(SimulationSupportTag.class);

    public SimulationSupportTag(TagConfig config) {
        super(config);
        simulationBeanParam = this.getRequiredAttribute("simulationBean");
        simulationIdParam = this.getAttribute("simulationId");
        scenarioIdParam = this.getAttribute("scenarioId");
        editActionsParam = this.getAttribute("editActions");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {
        
        SimulationBean simulationBean = (SimulationBean) simulationBeanParam.getObject(ctx);
        
        Long scenarioId = (Long) scenarioIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long simulationId = (Long) simulationIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Boolean edit = editActionsParam != null ? (Boolean) editActionsParam.getObject(ctx, Boolean.class) : false;
        try {
            if (scenarioId > 0 && simulationId > 0 && !edit) 
                simulationBean.reset();
            simulationBean.init(simulationId, scenarioId, edit);
        } catch (Exception e) {
            _log.error("Can't initialize simulationBean with parameters - simulationId " + 
                    simulationId + "\tscenarioId: " + scenarioId, e);
        }
        nextHandler.apply(ctx, parent);
    }
    
    

}
