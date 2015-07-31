package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.base.PlanTemplateLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.PlanTemplateSectionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;

/**
 * The implementation of the plan template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanTemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanTemplateLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanTemplateLocalServiceUtil
 */
public class PlanTemplateLocalServiceImpl
    extends PlanTemplateLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanTemplateLocalServiceUtil} to access the plan template local service.
     */


    public void store(PlanTemplate template) throws SystemException {
        if (template.isNew()) {
            if (template.getId() <= 0L) {
                template.setId(CounterLocalServiceUtil.increment(PlanTemplate.class.getName()));
            }
            
            PlanTemplateLocalServiceUtil.addPlanTemplate(template);
        }
        else {

            PlanTemplateLocalServiceUtil.updatePlanTemplate(template);
        }
    }
    
    public List<PlanSectionDefinition> getSections(PlanTemplate template) throws SystemException, PortalException {
        List<PlanSectionDefinition> ret = new ArrayList<PlanSectionDefinition>();
        for (PlanTemplateSection pts: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(template.getId())) {
            ret.add(PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(pts.getPlanSectionId()));
        }
        
        return ret;
    }
    
    public void addSection(PlanTemplate template, PlanSectionDefinition section) throws SystemException, PortalException {
        
        int maxWeight = 0;
        for (PlanTemplateSection def: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(template.getId())) {
            maxWeight = Math.max(maxWeight, def.getWeight());
        }
        
        PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(template.getId(), section.getId(), maxWeight+1);
    }
    
    public void removeSection(PlanTemplate template, PlanSectionDefinition section) throws SystemException, PortalException {
        PlanTemplateSectionLocalServiceUtil.removePlanTemplateSection(template.getId(), section.getId());
    }
    
    public void updateSectionWeight(PlanTemplate template, PlanSectionDefinition section, int weight) throws SystemException, PortalException {
        PlanTemplateSection pts = PlanTemplateSectionLocalServiceUtil.getPlanTemplateSection(
                new PlanTemplateSectionPK(template.getId(), section.getId()));
        pts.setWeight(weight);
        PlanTemplateSectionLocalServiceUtil.store(pts);
    }

}
