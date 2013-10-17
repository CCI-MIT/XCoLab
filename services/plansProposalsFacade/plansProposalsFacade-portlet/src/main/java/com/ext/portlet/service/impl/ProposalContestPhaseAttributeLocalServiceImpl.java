package com.ext.portlet.service.impl;

import java.util.List;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.service.base.ProposalContestPhaseAttributeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

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
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil} to access the proposal contest phase attribute local service.
     */
    
    /**
     * <p>Returns list of proposal phase attributes associated with given proposal in context of a phase</p>
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a phase
     * @return list of proposal phase attributes
     * @throws SystemException in case of LR error
     */
    public List<ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(long proposalId, long contestPhaseId) throws SystemException {
        return proposalContestPhaseAttributePersistence.findByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }
    
    /**
     * <p>Returns proposal phase attribute (if exists)</p>
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a phase
     * @param attributeName name of an attribute
     * @return proposal phase attribute
     * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
     * @throws SystemException in case of lr error
     */
    public ProposalContestPhaseAttribute getProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName) 
            throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return proposalContestPhaseAttributePersistence.findByProposalIdContestPhaseIdNameAdditionalId(proposalId, contestPhaseId, attributeName, 0);
    }
    
    /**
     * <p>Sets numeric value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
     * 
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param attributeName name of an attribute
     * @param value value to be set 
     * @throws SystemException in case of LR error
     */
    public void setProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName, long value) 
            throws SystemException {
        setProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, value, null, 0);
    }
    
    /**
     * <p>Sets string value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
     * 
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param attributeName name of an attribute
     * @param value value to be set 
     * @throws SystemException in case of LR error
     */
    public void setProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName, String value) 
            throws SystemException {
        setProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, 0L, value, 0);
    }
    
    /**
     * <p>Sets real value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
     * 
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param attributeName name of an attribute
     * @param value value to be set 
     * @throws SystemException in case of LR error
     */
    public void setProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName, double value) 
            throws SystemException {
        setProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, 0L, null, value);
    }
    
    /**
     * <p>Sets values contest phase attribute</p>
     * 
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param attributeName name of an attribute
     * @param value value to be set 
     * @throws SystemException in case of LR error
     */
    public void setProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName, 
            long longValue, String stringValue, double realValue) throws SystemException {
        ProposalContestPhaseAttribute attribute = null;
        try {
            attribute = getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName);
        }
        catch (NoSuchProposalContestPhaseAttributeException e) {
            attribute = proposalContestPhaseAttributeLocalService.createProposalContestPhaseAttribute(counterLocalService.increment(ProposalContestPhaseAttribute.class.getName()));
            attribute.setProposalId(proposalId);
            attribute.setContestPhaseId(contestPhaseId);
            attribute.setName(attributeName);
        }
        attribute.setStringValue(stringValue);
        attribute.setRealValue(realValue);
        attribute.setNumericValue(longValue);
        
        if (attribute.isNew()) {
            proposalContestPhaseAttributeLocalService.addProposalContestPhaseAttribute(attribute);
        }
        else {
            proposalContestPhaseAttributeLocalService.updateProposalContestPhaseAttribute(attribute);
        }
    }
    
    /**
     * <p>Removes proposal phase attribute with given name</p>
     * 
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param attributeName name of an attribute
     * @param value value to be set 
     * @throws SystemException in case of LR error
     */
    public void deleteProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName) throws SystemException {
        try {
            proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName));
        }
        catch (NoSuchProposalContestPhaseAttributeException e) {
        }
    }
}
