package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the PlanSection service. Represents a row in the &quot;plansProposalsFacade_PlanSection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanSection} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanSectionImpl extends PlanSectionBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan section model instance should use the {@link com.ext.portlet.plans.model.PlanSection} interface instead.
     */
    public PlanSectionImpl() {
    }

}
