package com.ext.portlet.service.impl;

import com.ext.portlet.service.base.Xcolab_UserLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Xcolab_UserFinderUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

    @Override
    public List<User> getUsersSortedByScreenName(int begin, int end, String filter, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByScreenNameAsc(begin, end, filter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    @Override
    public List<User> getUsersSortedByScreenNameFilteredByCategory(int begin, int end, String filter, String memberCategory, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByScreenNameAscFilteredByCategory(begin, end, filter, memberCategory);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByScreenNameDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    @Override
    public List<User> getUsersSortedByRoleName(int begin, int end, String filter, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByRoleNameAsc(begin, end, filter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    @Override
    public List<User> getUsersSortedByMemberSince(int begin, int end, String filter, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceAsc(begin, end, filter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    @Override
    public List<User> getUsersSortedByMemberSinceFilteredByCategory(int begin, int end, String filter,
                                                                    String memberCategory, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceAscFilteredByCategory(begin, end, filter, memberCategory);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByMemberSinceDescFilteredByCategoryName(begin, end, filter, memberCategory);
    }

    @Override
    public List<User> getUsersSortedByActivityCount(int begin, int end, String filter, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByActivityCountAsc(begin, end, filter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    @Override
    public List<User> getUsersSortedByActivityCountFilteredByCategory(int begin, int end, String filter,
                                                                      String memberCategory, boolean ascendingOrder)
            throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByActivityCountAscFilteredByCategory(begin, end, filter, memberCategory);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByActivityCountDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    @Override
    public List<User> getUsersSortedByPoints(int begin, int end, String filter, boolean ascendingOrder) throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByPointsAsc(begin, end, filter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByPointsDesc(begin, end, filter);
    }

    @Override
    public List<User> getUsersSortedByPointsFilteredByCategory(int begin, int end, String filter,
                                                               String memberCategoryFilter, boolean ascendingOrder) throws SystemException {
        if (ascendingOrder) {
            return Xcolab_UserFinderUtil.getUsersSortedByPointsAscFilteredByCategory(begin, end, filter, memberCategoryFilter);
        }
        return Xcolab_UserFinderUtil.getUsersSortedByPointsDescFilteredByCategory(begin, end, filter, memberCategoryFilter);
    }

    @Override
    public List<Long> getUserActivityCount(Long userId)
            throws SystemException {

        List<BigInteger> userActivityCount = Xcolab_UserFinderUtil.getUserActivityCount(userId);
        List<Long> userActivityCountLongValues = new ArrayList<>(userActivityCount.size());

        for (BigInteger singleActivityCount : userActivityCount) {
            userActivityCountLongValues.add(singleActivityCount.longValue());
        }

        return userActivityCountLongValues;
    }

    @Override
    public List<User> findUsersByLoginIP(String loginIP) throws SystemException {
        return Xcolab_UserFinderUtil.findUsersByLoginIP(QueryUtil.ALL_POS, QueryUtil.ALL_POS, loginIP);
    }
}
