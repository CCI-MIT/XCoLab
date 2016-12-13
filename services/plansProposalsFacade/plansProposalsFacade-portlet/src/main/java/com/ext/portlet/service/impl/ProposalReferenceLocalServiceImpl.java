package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalReferenceException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.base.ProposalReferenceLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalReferencePK;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The implementation of the proposal reference local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalReferenceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalReferenceLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalReferenceLocalServiceUtil
 */
public class ProposalReferenceLocalServiceImpl
    extends ProposalReferenceLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalReferenceLocalServiceUtil} to access the proposal reference local service.
     */

}
