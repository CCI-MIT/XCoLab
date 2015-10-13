package com.ext.portlet.service.impl;

import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.service.base.ProposalReferenceLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

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

    public List<ProposalReference> getByProposalId(long proposalId) throws SystemException {
        return proposalReferencePersistence.findByProposalId(proposalId);
    }

    public List<ProposalReference> getBySubProposalId(long subProposalId) throws SystemException {
        return proposalReferencePersistence.findBySubProposalId(subProposalId);
    }
}
