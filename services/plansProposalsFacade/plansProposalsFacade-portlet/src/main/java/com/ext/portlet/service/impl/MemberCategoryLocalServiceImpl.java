package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchMemberCategoryException;
import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.service.base.MemberCategoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the member category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.MemberCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.MemberCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.service.MemberCategoryLocalServiceUtil
 */
public class MemberCategoryLocalServiceImpl
    extends MemberCategoryLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.MemberCategoryLocalServiceUtil} to access the member category local service.
     */

    @Override
    public MemberCategory getByDisplayName(String displayName) {
        return null;
    }

    @Override
    public List<MemberCategory> getVisibleMemberCategories() throws NoSuchMemberCategoryException, SystemException {
        return memberCategoryPersistence.findByshowInList(true);
    }
}
