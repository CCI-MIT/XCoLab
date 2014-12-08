package com.ext.portlet.service.impl;

import com.ext.portlet.model.StaffMember;
import com.ext.portlet.service.base.StaffMemberLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the staff member local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.StaffMemberLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.StaffMemberLocalServiceBaseImpl
 * @see com.ext.portlet.service.StaffMemberLocalServiceUtil
 */
public class StaffMemberLocalServiceImpl extends StaffMemberLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.StaffMemberLocalServiceUtil} to access the staff member local service.
     */

    public List<StaffMember> getStaffMembersByCategoryId(long categoryId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(StaffMember.class)
                .add(PropertyFactoryUtil.forName("categoryId").eq(categoryId))
                .addOrder(OrderFactoryUtil.asc("sort"));
        return dynamicQuery(query);
    }
}
