package org.xcolab.hooks.climatecolab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalService;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceWrapper;

import java.util.Date;

public class CustomSocialActivityLocalServiceImpl extends SocialActivityLocalServiceWrapper {

    private final Logger _log = LoggerFactory.getLogger(CustomSocialActivityLocalServiceImpl.class);

    private static final Object mutex = new Object();

    public CustomSocialActivityLocalServiceImpl(SocialActivityLocalService socialActivityLocalService) {
        super(socialActivityLocalService);
    }

    @Override
    public void addActivity(
            SocialActivity activity, SocialActivity mirrorActivity)
            throws PortalException, SystemException {

        super.addActivity(activity, mirrorActivity);

        if (activity.getActivityId() > 0) {
            triggerReindex(activity);
        }
    }

    @Override
    public void addActivity(
            long userId, long groupId, Date createDate, String className,
            long classPK, int type, String extraData, long receiverUserId)
            throws PortalException, SystemException {

        synchronized (mutex) {
            super.addActivity(userId, groupId, createDate, className,
                    classPK, type, extraData, receiverUserId);

            SocialActivity newlyAddedActivity = SocialActivityLocalServiceUtil.getSocialActivities(0, 1).get(0);
            if (newlyAddedActivity.getActivityId() > 0) {
                triggerReindex(newlyAddedActivity);
            }
        }
    }

    private void triggerReindex(SocialActivity socialActivity) {
        try {
            final Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    SocialActivity.class);
            indexer.reindex(socialActivity);
        } catch (SearchException e) {
            _log.warn("Could not reindex SocialActivity with id {}", socialActivity.getActivityId(),
                    e);
        }
    }
}
