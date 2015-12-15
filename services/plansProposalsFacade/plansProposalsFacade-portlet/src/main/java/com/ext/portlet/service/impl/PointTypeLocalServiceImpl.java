package com.ext.portlet.service.impl;

import com.ext.portlet.model.PointType;
import com.ext.portlet.service.base.PointTypeLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the point type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PointTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PointTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.PointTypeLocalServiceUtil
 */
public class PointTypeLocalServiceImpl extends PointTypeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PointTypeLocalServiceUtil} to access the point type local service.
     */

    @Override
    public List<PointType> getChildrenOfPointType(long parentPointTypeId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(PointType.class)
                .add(PropertyFactoryUtil.forName("parentPointTypeId").eq(parentPointTypeId));
        query.addOrder(OrderFactoryUtil.asc("sort"));
        return dynamicQuery(query);
    }
}
