package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.Points;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.base.PointsLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalFinderUtil;
import com.ext.portlet.service.persistence.Xcolab_UserFinderUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The implementation of the points local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PointsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PointsLocalServiceBaseImpl
 * @see com.ext.portlet.service.PointsLocalServiceUtil
 */
public class PointsLocalServiceImpl extends PointsLocalServiceBaseImpl {

}
