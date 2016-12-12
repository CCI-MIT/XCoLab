package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.base.ProposalContestPhaseAttributeLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * The implementation of the proposal contest phase attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalContestPhaseAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalContestPhaseAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil
 */
public class ProposalContestPhaseAttributeLocalServiceImpl
    extends ProposalContestPhaseAttributeLocalServiceBaseImpl {

    private final static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributeLocalServiceImpl.class);
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil} to access the proposal contest phase attribute local service.
     */

}
