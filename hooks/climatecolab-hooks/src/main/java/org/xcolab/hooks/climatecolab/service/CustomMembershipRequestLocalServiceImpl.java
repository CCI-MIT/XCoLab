package org.xcolab.hooks.climatecolab.service;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.MembershipRequestCommentsException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.MembershipRequestLocalService;
import com.liferay.portal.service.MembershipRequestLocalServiceWrapper;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.MembershipRequestUtil;
import com.liferay.portal.service.persistence.UserUtil;

import java.util.Date;

/**
 * IMPORTANT
 * This class overrides the default behaviour of LR to disable sending out new email notifications when a new MembershipRequest is created
 * or updated. The class excludes the logic for sending the email notifications. This class also removes unnecessary validators that disallow empty comments.
 *
 * REVIEW this class when updating LR version. Changes in the class {@link com.liferay.portal.service.impl.MembershipRequestLocalServiceImpl} may break this code
 *
 * Created by kmang on 02/05/14.
 */
public class CustomMembershipRequestLocalServiceImpl extends MembershipRequestLocalServiceWrapper {


	public CustomMembershipRequestLocalServiceImpl(MembershipRequestLocalService membershipRequestLocalService) {
		super(membershipRequestLocalService);
	}

	@Override
	public MembershipRequest addMembershipRequest(
			long userId, long groupId, String comments,
			ServiceContext serviceContext)
			throws SystemException, NoSuchUserException, MembershipRequestCommentsException {


		User user = UserUtil.findByPrimaryKey(userId);
		Date now = new Date();

		long membershipRequestId = CounterLocalServiceUtil.increment();

		MembershipRequest membershipRequest =
				MembershipRequestUtil.create(membershipRequestId);

		membershipRequest.setCompanyId(user.getCompanyId());
		membershipRequest.setUserId(userId);
		membershipRequest.setCreateDate(now);
		membershipRequest.setGroupId(groupId);
		membershipRequest.setComments(comments);
		membershipRequest.setStatusId(
				MembershipRequestConstants.STATUS_PENDING);

		MembershipRequestUtil.update(membershipRequest);

		return membershipRequest;
	}

	@Override
	public void updateStatus(
			long replierUserId, long membershipRequestId, String replyComments,
			int statusId, boolean addUserToGroup, ServiceContext serviceContext)
			throws PortalException, SystemException {

		MembershipRequest membershipRequest =
				MembershipRequestUtil.findByPrimaryKey(membershipRequestId);

		if((statusId == MembershipRequestConstants.STATUS_APPROVED)||
				(statusId == MembershipRequestConstants.STATUS_DENIED)) {
			membershipRequest.setReplyComments(replyComments);
			membershipRequest.setReplyDate(new Date());

			if (replierUserId != 0) {
				membershipRequest.setReplierUserId(replierUserId);
			} else {
				long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
						membershipRequest.getCompanyId());

				membershipRequest.setReplierUserId(defaultUserId);
			}
		}
		membershipRequest.setStatusId(statusId);

		MembershipRequestUtil.update(membershipRequest);

		if ((statusId == MembershipRequestConstants.STATUS_APPROVED) &&
				addUserToGroup) {

			long[] addUserIds = {membershipRequest.getUserId()};

			UserLocalServiceUtil.addGroupUsers(
					membershipRequest.getGroupId(), addUserIds);
		}
	}
}
