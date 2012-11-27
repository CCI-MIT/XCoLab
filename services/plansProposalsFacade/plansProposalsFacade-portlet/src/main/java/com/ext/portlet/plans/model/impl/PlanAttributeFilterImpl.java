package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.TypedValueConverter;

/**
 * The extended model implementation for the PlanAttributeFilter service. Represents a row in the &quot;Plans_PlanAttributeFilter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanAttributeFilter} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanAttributeFilterImpl extends PlanAttributeFilterBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan attribute filter model instance should use the {@link com.ext.portlet.plans.model.PlanAttributeFilter} interface instead.
     */

    private Object typedValue;
    
    public PlanAttributeFilterImpl() {
    }
    
    public Object getTypedValue() {
        if (typedValue != null) {
            return typedValue;
        }
        Attribute attribute = Attribute.valueOf(getAttributeName());
        typedValue = TypedValueConverter.getValue(attribute.getAttributeClass(), getStringVal());
        return typedValue;
    }
    
    @Override
    public void setStringVal(String stringVal) {
        typedValue = null;
        super.setStringVal(stringVal);
    }
}
