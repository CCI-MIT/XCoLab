package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.base.PointsDistributionConfigurationLocalServiceBaseImpl;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.SystemException;

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
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil} to access the points distribution configuration local service.
     */
	@Override
    public List<PointsDistributionConfiguration> findByProposalPointType(Proposal proposal, PointType pointType) throws SystemException {
		return pointsDistributionConfigurationPersistence.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId());
	}

    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        pointsDistributionConfigurationPersistence.removeByProposalId(proposalId);
    }

    @Override
    public PointsDistributionConfiguration addDistributionConfiguration(long proposalId, long pointTypeId,
                                                                        Long targetUserId, Long targetSubProposalId, double percentage, long creator)
            throws SystemException, NoSuchUserException {

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
}
