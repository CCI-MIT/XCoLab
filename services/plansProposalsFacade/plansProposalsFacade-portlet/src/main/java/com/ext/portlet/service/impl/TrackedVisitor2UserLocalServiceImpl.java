package com.ext.portlet.service.impl;

import com.ext.portlet.model.TrackedVisitor2User;
import com.ext.portlet.service.base.TrackedVisitor2UserLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;


import java.util.List;

/**
 * The implementation of the tracked visitor2 user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.TrackedVisitor2UserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Manuel Thurner
 * @see com.ext.portlet.service.base.TrackedVisitor2UserLocalServiceBaseImpl
 * @see com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil
 */
public class TrackedVisitor2UserLocalServiceImpl
        extends TrackedVisitor2UserLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil} to access the tracked visitor2 user local service.
     */

    @Override
    public String findUuidForUserId(long userId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(TrackedVisitor2User.class)
                .add(PropertyFactoryUtil.forName("userId").eq(userId))
                .addOrder(OrderFactoryUtil.desc("createDate"));
        List<TrackedVisitor2User> result = dynamicQuery(query);
        if (!result.isEmpty()) {
            return result.get(0).getUuid();
        } else {
            return null;
        }
    }

    @Override
    public TrackedVisitor2User addIfNotExists(String uuid, long userId) throws SystemException {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(TrackedVisitor2User.class)
                .add(PropertyFactoryUtil.forName("uuid").eq(uuid))
                .add(PropertyFactoryUtil.forName("userId").eq(userId));
        List<TrackedVisitor2User> result = dynamicQuery(query);

        if (result.size() < 1) {
            //add new TrackedVisitor2User
            long trackedVisitor2UserId = counterLocalService.increment(TrackedVisitor2User.class.getName());

            TrackedVisitor2User trackedVisitor2User = trackedVisitor2UserPersistence.create(trackedVisitor2UserId);
            trackedVisitor2User.setUuid(uuid);
            trackedVisitor2User.setUserId(userId);
            //trackedVisitor2User.setCreateDate(new Date());

            addTrackedVisitor2User(trackedVisitor2User);

            return trackedVisitor2User;
        } else {
            return result.get(0);
        }
    }
}
