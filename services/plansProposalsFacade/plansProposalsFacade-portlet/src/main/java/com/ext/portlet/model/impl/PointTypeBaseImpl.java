package com.ext.portlet.model.impl;

import com.ext.portlet.model.PointType;
import com.ext.portlet.service.PointTypeLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PointType service. Represents a row in the &quot;xcolab_PointType&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PointTypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointTypeImpl
 * @see com.ext.portlet.model.PointType
 * @generated
 */
public abstract class PointTypeBaseImpl extends PointTypeModelImpl
    implements PointType {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a point type model instance should use the {@link PointType} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PointTypeLocalServiceUtil.addPointType(this);
        } else {
            PointTypeLocalServiceUtil.updatePointType(this);
        }
    }
}
