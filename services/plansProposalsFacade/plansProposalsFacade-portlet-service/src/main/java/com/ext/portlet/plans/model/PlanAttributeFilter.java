package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanAttributeFilter service. Represents a row in the &quot;Plans_PlanAttributeFilter&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterModel
 * @see com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl
 * @see com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl
 * @generated
 */
public interface PlanAttributeFilter extends PlanAttributeFilterModel,
    PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.Object getTypedValue();

    public void setStringVal(java.lang.String stringVal);
}
