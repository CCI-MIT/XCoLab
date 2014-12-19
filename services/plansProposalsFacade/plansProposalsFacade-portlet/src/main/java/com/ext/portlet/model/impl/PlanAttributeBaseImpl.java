package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PlanAttribute service. Represents a row in the &quot;xcolab_PlanAttribute&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PlanAttributeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeImpl
 * @see com.ext.portlet.model.PlanAttribute
 * @generated
 */
public abstract class PlanAttributeBaseImpl extends PlanAttributeModelImpl
    implements PlanAttribute {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a plan attribute model instance should use the {@link PlanAttribute} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanAttributeLocalServiceUtil.addPlanAttribute(this);
        } else {
            PlanAttributeLocalServiceUtil.updatePlanAttribute(this);
        }
    }
}
