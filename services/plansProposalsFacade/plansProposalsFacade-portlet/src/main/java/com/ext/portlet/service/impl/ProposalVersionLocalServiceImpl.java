package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalVersionException;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.base.ProposalVersionLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the proposal version local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalVersionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalVersionLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalVersionLocalServiceUtil
 */
public class ProposalVersionLocalServiceImpl
    extends ProposalVersionLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalVersionLocalServiceUtil} to access the proposal version local service.
     */
    
    /**
     * <p>Returns proposal version count for given proposal</p>
     * @param proposalId proposal id
     * @return proposal versions count
     * @throws SystemException
     */
    @Override
    public long countByProposalId(long proposalId) throws SystemException {
        return proposalVersionPersistence.countByProposalId(proposalId);
    }
    
    /**
     * <p>Returns list of proposal versions for given proposal</p>
     * @param proposalId id of a proposal
     * @param start first entity
     * @param end last entity
     * @return list of proposal versions
     * @throws SystemException
     */
    @Override
    public List<ProposalVersion> getByProposalId(long proposalId, int start, int end) throws SystemException {
        return proposalVersionPersistence.findByProposalId(proposalId, start, end);
    }

    @Override
    public ProposalVersion getByProposalIdVersion(long proposalId, int version) throws SystemException,
            NoSuchProposalVersionException {
        ProposalVersionPK pk = new ProposalVersionPK(proposalId,version);
        return proposalVersionPersistence.findByPrimaryKey(pk);
    }
}
