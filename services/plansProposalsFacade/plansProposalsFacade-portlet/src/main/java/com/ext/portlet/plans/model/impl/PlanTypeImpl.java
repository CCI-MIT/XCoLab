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

}
