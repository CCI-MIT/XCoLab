package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanModelRun;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PlanModelRun service. Represents a row in the &quot;xcolab_PlanModelRun&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PlanModelRunImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunImpl
 * @see com.ext.portlet.model.PlanModelRun
 * @generated
 */
public abstract class PlanModelRunBaseImpl extends PlanModelRunModelImpl
    implements PlanModelRun {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a plan model run model instance should use the {@link PlanModelRun} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(this);
        } else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(this);
        }
    }
}
