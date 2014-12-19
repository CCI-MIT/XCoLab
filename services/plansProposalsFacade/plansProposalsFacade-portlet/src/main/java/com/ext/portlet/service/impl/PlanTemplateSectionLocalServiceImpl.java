package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.base.PlanTemplateSectionLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.PlanTemplateSectionPK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the plan template section local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanTemplateSectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanTemplateSectionLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil
 */
public class PlanTemplateSectionLocalServiceImpl
    extends PlanTemplateSectionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil} to access the plan template section local service.
     */
    
    public List<PlanTemplateSection> findByPlanTemplateId(Long planTemplateId) throws SystemException {
        return planTemplateSectionPersistence.findByPlanTemplateId(planTemplateId);
        
    }
    
    public PlanTemplateSection addPlanTemplateSection(Long planTemplateId, Long sectionId, int weight) throws SystemException {
        PlanTemplateSection pts = createPlanTemplateSection(new PlanTemplateSectionPK(planTemplateId, sectionId));
        
        pts.setWeight(weight);
        PlanTemplateSectionLocalServiceUtil.store(pts);
        
        return pts;
    }
    
    public void removePlanTemplateSection(Long planTemplateId, Long sectionId) throws SystemException, PortalException {
        PlanTemplateSectionLocalServiceUtil.remove(getPlanTemplateSection(new PlanTemplateSectionPK(planTemplateId, sectionId)));
    }
    

    public void store(PlanTemplateSection section) throws SystemException {
        if (section.isNew()) PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(section);
        else  PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(section);
    }
    
    public void remove(PlanTemplateSection section) throws SystemException {
        PlanTemplateSectionLocalServiceUtil.deletePlanTemplateSection(section);
    }
    

}
