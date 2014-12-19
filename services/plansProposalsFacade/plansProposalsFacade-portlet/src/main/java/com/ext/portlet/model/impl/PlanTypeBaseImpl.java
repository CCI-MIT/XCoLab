package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanType;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PlanType service. Represents a row in the &quot;xcolab_PlanType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PlanTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeImpl
 * @see com.ext.portlet.model.PlanType
 * @generated
 */
public abstract class PlanTypeBaseImpl extends PlanTypeModelImpl
    implements PlanType {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a plan type model instance should use the {@link PlanType} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeLocalServiceUtil.addPlanType(this);
        } else {
            PlanTypeLocalServiceUtil.updatePlanType(this);
        }
    }
}
