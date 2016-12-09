package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalAttributeException;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.base.ProposalAttributeLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.proposals.events.ProposalAttributeRemovedEvent;
import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;
import org.xcolab.services.EventBusService;
import org.xcolab.utils.ProposalAttributeDetectUpdateAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the proposal attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalAttributeLocalServiceUtil
 */
public class ProposalAttributeLocalServiceImpl
    extends ProposalAttributeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalAttributeLocalServiceUtil} to access the proposal attribute local service.
     */

}
