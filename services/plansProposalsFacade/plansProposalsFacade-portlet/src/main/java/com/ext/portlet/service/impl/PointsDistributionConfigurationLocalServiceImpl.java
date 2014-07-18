package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.base.PointsDistributionConfigurationLocalServiceBaseImpl;
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
	public List<PointsDistributionConfiguration> findByProposalPointType(Proposal proposal, PointType pointType) throws SystemException {
		return pointsDistributionConfigurationPersistence.findByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId());
	}
}
