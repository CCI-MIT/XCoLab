package com.ext.portlet.plans.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.Simulation;

/**
 * The extended model implementation for the PlanType service. Represents a row in the &quot;plansProposalsFacade_PlanType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanType} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanTypeImpl extends PlanTypeBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan type model instance should use the {@link com.ext.portlet.plans.model.PlanType} interface instead.
     */
    public PlanTypeImpl() {
    }

    public List<Simulation> getAvailableModels() throws SystemException {

        if (this.getModelTypeName()!=null && this.getModelTypeName().trim().length() > 0) {
               return new ArrayList<Simulation>(CollaboratoriumModelingService.repository().getSimulationsOfType(this.getModelTypeName()));
        } else if (this.getModelId()>0) {
            try {
                return Collections.singletonList(CollaboratoriumModelingService.repository().getSimulation(this.getModelId()));
            }
            catch (IOException e) {
                throw new SystemException(e);
            }
        } else return Collections.emptyList();
       }

    public Simulation getDefaultModel() throws SystemException {
        if (this.getDefaultModelId() == null) {
            return null;
        } else {
            try {
                return CollaboratoriumModelingService.repository().getSimulation(this.getDefaultModelId());
            }
            catch (IOException e) {
                throw new SystemException(e);
            }
        }
    }

    
    public List<PlanTypeColumn> getColumns() throws SystemException {
        List<PlanTypeColumn> cols= PlanTypeLocalServiceUtil.getColumnsByPlanTypeId(this.getPlanTypeId());
        return cols == null?Collections.<PlanTypeColumn>emptyList():cols;
    }
    
    public List<PlanTypeAttribute> getAttributes() throws SystemException {
        List<PlanTypeAttribute> atts = PlanTypeLocalServiceUtil.getAttributesByPlanTypeId(this.getPlanTypeId());
        return atts==null? Collections.<PlanTypeAttribute>emptyList() :atts;
    }
    
    public boolean isRegional() throws SystemException {
        return PlanTypeLocalServiceUtil.isRegionalType(getPlanTypeId());
    }
}
