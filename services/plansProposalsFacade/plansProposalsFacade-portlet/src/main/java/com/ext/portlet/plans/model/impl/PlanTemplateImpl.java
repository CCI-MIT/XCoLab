package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the PlanTemplate service. Represents a row in the &quot;plansProposalsFacade_PlanTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanTemplate} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanTemplateImpl extends PlanTemplateBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan template model instance should use the {@link com.ext.portlet.plans.model.PlanTemplate} interface instead.
     */
    public PlanTemplateImpl() {
    }

}
