package com.ext.portlet.service.impl;

import com.ext.portlet.service.base.Xcolab_UserLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Xcolab_UserFinderUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the xcolab_ user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.Xcolab_UserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.Xcolab_UserLocalServiceBaseImpl
 * @see com.ext.portlet.service.Xcolab_UserLocalServiceUtil
 */
public class Xcolab_UserLocalServiceImpl extends Xcolab_UserLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.Xcolab_UserLocalServiceUtil} to access the xcolab_ user local service.
     */
    public List<User> getUsersSortedByScreenNameAsc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public List<User> getUsersSortedByScreenNameAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User> getUsersSortedByScreenNameDesc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public List<User> getUsersSortedByScreenNameDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User> getUsersSortedByRoleNameAsc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public List<User> getUsersSortedByRoleNameDesc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public List<User> getUsersSortedByMemberSinceAsc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public List<User> getUsersSortedByMemberSinceAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User> getUsersSortedByMemberSinceDesc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public List<User> getUsersSortedByMemberSinceDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceDescFilteredByCategoryName(begin, end, filter, memberCategory);
    }

    public List<User> getUsersSortedByActivityCountAsc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public List<User> getUsersSortedByActivityCountAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User> getUsersSortedByActivityCountDesc(int begin, int end, String filter)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public List<User> getUsersSortedByActivityCountDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<Long> getUserActivityCount(Long userId)
            throws SystemException {

        List<BigInteger> userActivityCount = Xcolab_UserFinderUtil.getUserActivityCount(userId);
        List<Long> userActivityCountLongValues = new ArrayList<>(userActivityCount.size());

        for (BigInteger singleActivityCount : userActivityCount) {
            userActivityCountLongValues.add(singleActivityCount.longValue());
        }

        return userActivityCountLongValues;
    }
}
