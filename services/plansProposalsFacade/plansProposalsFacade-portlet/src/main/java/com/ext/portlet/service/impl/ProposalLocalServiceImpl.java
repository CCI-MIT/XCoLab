package com.ext.portlet.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.NoSuchProposalSupporterException;
import com.ext.portlet.NoSuchProposalVoteException;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.ext.portlet.service.base.ProposalLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.ext.portlet.service.persistence.ProposalSupporterPK;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.ext.portlet.service.persistence.ProposalVotePK;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.enums.MembershipRequestStatus;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;
import org.xcolab.proposals.events.ProposalMemberAddedEvent;
import org.xcolab.proposals.events.ProposalMemberRemovedEvent;
import org.xcolab.proposals.events.ProposalRemovedVoteEvent;
import org.xcolab.proposals.events.ProposalSupporterAddedEvent;
import org.xcolab.proposals.events.ProposalSupporterRemovedEvent;
import org.xcolab.proposals.events.ProposalVotedOnEvent;
import org.xcolab.services.EventBusService;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.utils.TemplateReplacementUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.portlet.PortletRequest;

/**
 * The implementation of the proposal local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.ProposalLocalService} interface.
 * <p/>
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author janusz
 * @see com.ext.portlet.service.base.ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalLocalServiceUtil
 */
public class ProposalLocalServiceImpl extends ProposalLocalServiceBaseImpl {

}
