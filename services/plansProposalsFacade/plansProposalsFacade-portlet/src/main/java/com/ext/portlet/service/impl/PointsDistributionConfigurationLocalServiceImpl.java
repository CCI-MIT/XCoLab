package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchPointsDistributionConfigurationException;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.service.base.PointsDistributionConfigurationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

import org.xcolab.utils.EntityGroupingUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The implementation of the points distribution configuration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PointsDistributionConfigurationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PointsDistributionConfigurationLocalServiceBaseImpl
 * @see com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil
 */
public class PointsDistributionConfigurationLocalServiceImpl
    extends PointsDistributionConfigurationLocalServiceBaseImpl {

    private final static Log _log = LogFactoryUtil.getLog(PointsDistributionConfigurationLocalServiceImpl.class);

    private final static double EQUALITY_EPSILON = 0.00000001d;
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil} to access the points distribution configuration local service.
     */
	@Override
    public List<PointsDistributionConfiguration> findByProposalIdPointTypeId(long proposalId, long pointTypeId)
            throws SystemException {
		return pointsDistributionConfigurationPersistence.findByProposalIdPointTypeId(proposalId, pointTypeId);
	}

    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        pointsDistributionConfigurationPersistence.removeByProposalId(proposalId);
    }

    @Override

    public PointsDistributionConfiguration getByPlanSectionDefinitionId(long planSectionDefinitionId)
            throws SystemException, NoSuchPointsDistributionConfigurationException {
        return pointsDistributionConfigurationPersistence.findByTargetPlanSectionDefinitionId(planSectionDefinitionId);
    }

    @Override
    public PointsDistributionConfiguration addDistributionConfiguration(long proposalId, long pointTypeId,
                                                                        Long targetUserId, Long targetSubProposalId,
                                                                        double percentage, long creator)
            throws SystemException {

        long id = counterLocalService.increment(PointsDistributionConfiguration.class.getName());

        PointsDistributionConfiguration model = pointsDistributionConfigurationPersistence.create(id);

        model.setProposalId(proposalId);
        model.setPointTypeId(pointTypeId);
        if (targetUserId != null) {
            model.setTargetUserId(targetUserId);
        }
        if (targetSubProposalId != null) {
            model.setTargetSubProposalId(targetSubProposalId);
        }
        model.setPercentage(percentage);
        model.setCreator(creator);
        model.setCreateDate(new Date());

        addPointsDistributionConfiguration(model);

        return model;
    }

    @Override
    public void verifyDistributionConfigurationsForProposalId(long proposalId) throws SystemException, PortalException {
        Map<Long, List<PointsDistributionConfiguration>> pdcsByPointTypeId = new HashMap<>();
        for (PointsDistributionConfiguration pdc : pointsDistributionConfigurationPersistence.findByProposalId(proposalId)) {
            List<PointsDistributionConfiguration> pdcs = EntityGroupingUtil.getInnerListOrCreate(pdc.getPointTypeId(), pdcsByPointTypeId);
            pdcs.add(pdc);
        }

        for (Map.Entry<Long, List<PointsDistributionConfiguration>> entry : pdcsByPointTypeId.entrySet()) {
            final long pointTypeId = entry.getKey();
            final List<PointsDistributionConfiguration> pdcs = entry.getValue();


            PointType pointType = pointTypeLocalService.getPointType(pointTypeId);
            /*if (ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy())
                    .equals(ReceiverLimitationStrategy.ANY_TEAM_MEMBER)) {
                verifyTeamMemberships(proposalId, pointTypeId, pdcs);
            }*/

            double sum = 0;
            for (PointsDistributionConfiguration pdc : pdcs) {
                sum += pdc.getPercentage();
            }

            if (Math.abs(sum - 1.0) > EQUALITY_EPSILON) {
                _log.warn(String.format("Fixing PointsDistributionConfiguration for proposal %d pointType %d: sum is %f (should be 1)",
                        proposalId, pointTypeId, sum));
                double scaleFactor = 1.0/sum;
                for (PointsDistributionConfiguration pdc : pdcs) {
                    pdc.setPercentage(pdc.getPercentage() * scaleFactor);
                    pdc.persist();
                }
            }
        }
    }

    private void verifyTeamMemberships(long proposalId, long pointTypeId, List<PointsDistributionConfiguration> pdcs) throws SystemException, PortalException {
        Set<Long> memberIds = new HashSet<>();
        Set<Long> missingMemberIds = new HashSet<>();
        for (User user : proposalLocalService.getMembers(proposalId)) {
            memberIds.add(user.getUserId());
            missingMemberIds.add(user.getUserId());
        }

        for (PointsDistributionConfiguration pdc : pdcs) {
            if (memberIds.contains(pdc.getTargetUserId())) {
                missingMemberIds.remove(pdc.getTargetUserId());
            } else {
                pointsDistributionConfigurationPersistence.remove(pdc);
                _log.info(String.format("Removing PointsDistributionConfiguration non-team member %d for proposal %d pointType %d.",
                        pdc.getTargetUserId(), proposalId, pointTypeId));
            }
        }

        for (long userId : missingMemberIds) {
            addDistributionConfiguration(proposalId, pointTypeId, userId, 0L, 1.0/memberIds.size(), 0L);
            _log.info(String.format("Adding missing PointsDistributionConfiguration for team member %d for proposal %d pointType %d.",
                    userId, proposalId, pointTypeId));
        }
    }
}
