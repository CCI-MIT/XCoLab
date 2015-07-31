package com.ext.portlet.service.impl;

import com.ext.portlet.model.User_;
import com.ext.portlet.service.base.User_LocalServiceBaseImpl;
import com.ext.portlet.service.persistence.User_FinderUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.math.BigInteger;
import java.util.List;

/**
 * The implementation of the user_ local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.User_LocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.User_LocalServiceBaseImpl
 * @see com.ext.portlet.service.User_LocalServiceUtil
 */
public class User_LocalServiceImpl extends User_LocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.User_LocalServiceUtil} to access the user_ local service.
     */

    public List<User_> getUsersSortedByScreenNameAsc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public List<User_> getUsersSortedByScreenNameAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByScreenNameAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User_> getUsersSortedByScreenNameDesc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public List<User_> getUsersSortedByScreenNameDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByScreenNameDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User_> getUsersSortedByRoleNameAsc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public List<User_> getUsersSortedByRoleNameDesc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public List<User_> getUsersSortedByMemberSinceAsc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public List<User_> getUsersSortedByMemberSinceAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByMemberSinceAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User_> getUsersSortedByMemberSinceDesc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public List<User_> getUsersSortedByMemberSinceDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByMemberSinceDescFilteredByCategoryName(begin, end, filter, memberCategory);
    }

    public List<User_> getUsersSortedByActivityCountAsc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public List<User_> getUsersSortedByActivityCountAscFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByActivityCountAscFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<User_> getUsersSortedByActivityCountDesc(int begin, int end, String filter)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public List<User_> getUsersSortedByActivityCountDescFilteredByCategory(int begin, int end, String filter, String memberCategory)
            throws SystemException {

        return User_FinderUtil.getUsersSortedByActivityCountDescFilteredByCategory(begin, end, filter, memberCategory);
    }

    public List<BigInteger> getUserActivityCount(Long userId)
            throws SystemException {

        return User_FinderUtil.getUserActivityCount(userId);
    }
}