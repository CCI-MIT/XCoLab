package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
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

    @Override
    public boolean isAttributeSetAndTrue(long proposalId, long contestPhaseId, String attributeName, long additionalId) throws SystemException {
        boolean isTrue = false;
        try {
            ProposalContestPhaseAttribute boolAttr = getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
            if (boolAttr.getStringValue().equals("true")) {
                isTrue = true;
            }
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            //isTrue stays false
        }
        return isTrue;
    }

    @Override
    public boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, long numericValue) {
        ProposalContestPhaseAttribute attribute = getOrCreateAttribute(proposalId, contestPhaseId, attributeName, additionalId);

        attribute.setAdditionalId(additionalId);
        attribute.setNumericValue(numericValue);

        try {
            updateProposalContestPhaseAttribute(attribute);
        } catch (SystemException e) {
            _log.error(String.format("Error persisting attribute for proposal %d, contest phase %d, attribute name %s, additionalId %d, numericValue %d.",
                    proposalId, contestPhaseId, attributeName, additionalId, numericValue), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean persistAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId, String stringValue) {
        ProposalContestPhaseAttribute attribute = getOrCreateAttribute(proposalId, contestPhaseId, attributeName, additionalId);

        attribute.setAdditionalId(additionalId);
        attribute.setStringValue(stringValue);

        try {
            updateProposalContestPhaseAttribute(attribute);
        } catch (SystemException e) {
            _log.error(String.format("Error persisting attribute for proposal %d, contest phase %d, attribute name %s, additionalId %d, stringValue %s.",
                    proposalId, contestPhaseId, attributeName, additionalId, stringValue), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean persistSelectedJudgesAttribute(long proposalId, long contestPhaseId, List<Long> selectedJudges) {
        ProposalContestPhaseAttribute judges = getOrCreateAttribute(proposalId, contestPhaseId, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES, 0);


        StringBuilder attributeValue = new StringBuilder("");
        if (selectedJudges != null) {
            for (Long userId : selectedJudges) {
                attributeValue.append(userId).append(";");
            }
        }
        judges.setStringValue(attributeValue.toString().replaceAll(";$", ""));

        try {
            updateProposalContestPhaseAttribute(judges);
        } catch (SystemException e) {
            _log.error(String.format("Error persisting attribute for selected judges in proposal %d, contest phase %d.",
                    proposalId, contestPhaseId), e);
            return false;
        }
        return true;
    }

    @Override
    public ProposalContestPhaseAttribute getOrCreateAttribute(long proposalId, long contestPhaseId, String attributeName, long additionalId) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposalId);
                attribute.setContestPhaseId(contestPhaseId);
                attribute.setName(attributeName);
                addProposalContestPhaseAttribute(attribute);
                return attribute;
            } catch (SystemException e1) {
                _log.error(String.format("Error creating attribute for proposal %d, contest phase %d, attribute name %s, additionalId %d.",
                        proposalId, contestPhaseId, attributeName, additionalId), e);
                return null;
            }
        } catch (SystemException e) {
            _log.error(String.format("Error retrieving attribute for proposal %d, contest phase %d, attribute name %s, additionalId %d.",
                    proposalId, contestPhaseId, attributeName, additionalId), e);
            return null;
        }
    }
    
    /**
     * <p>Returns list of proposal phase attributes associated with given proposal in context of a phase</p>
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a phase
     * @return list of proposal phase attributes
     * @throws SystemException in case of LR error
     */
    @Override
    public List<ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(long proposalId, long contestPhaseId) throws SystemException {
        return proposalContestPhaseAttributePersistence.findByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }
        /**
         * <p>Returns a proposal phase attribute by attributeName associated with given proposal in context of a phase and additionalId</p>
         * @param proposalId id of a proposal
         * @param contestPhaseId id of a phase
         * @param attributeName name of attribute
         * @param additionalId additional id to find attribute
         * @return proposal phase attribute
         * @throws SystemException in case of LR error
         */
    @Override
    public ProposalContestPhaseAttribute getProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName,long additionalId) throws SystemException, NoSuchProposalContestPhaseAttributeException {
        return proposalContestPhaseAttributePersistence.findByProposalIdContestPhaseIdNameAdditionalId(proposalId, contestPhaseId,attributeName, additionalId);
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
    @Override
    public ProposalContestPhaseAttribute getProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName)
            throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return proposalContestPhaseAttributePersistence.findByProposalIdContestPhaseIdNameAdditionalId(proposalId, contestPhaseId, attributeName, 0);
    }

    /**
     * <p>Returns proposal phase attribute (if exists)</p>
     * @param proposalId id of a proposal
     * @param contestPhaseId id of a phase
     * @param attributeName name of an attribute
     * @return true if the attribute exists, false otherwise
     * @throws SystemException in case of lr error
     */
    @Override
    public boolean hasProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName)
            throws SystemException {
        final ProposalContestPhaseAttribute proposalContestPhaseAttribute = proposalContestPhaseAttributePersistence.fetchByProposalIdContestPhaseIdNameAdditionalId(proposalId,
                contestPhaseId, attributeName, 0);
        return proposalContestPhaseAttribute != null;
    }
    
    /**
     * <p>Returns all contest phase attributes</p>
     * @param contestPhaseId id of a phase
     * @return proposal phase attributes
     * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
     * @throws SystemException in case of lr error
     */
    @Override
    public List<ProposalContestPhaseAttribute> getAllContestPhaseAttributes(long contestPhaseId)
            throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return proposalContestPhaseAttributePersistence.findByContestPhaseId(contestPhaseId);
    }

    /**
     * <p>Returns all contest phase attributes</p>
     * @param contestPhaseId id of a phase
     * @return proposal phase attributes
     * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
     * @throws SystemException in case of lr error
     */
    @Override
    public List<ProposalContestPhaseAttribute> getAllContestPhaseProposalAttributes(long contestPhaseId, long proposalId)
            throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return proposalContestPhaseAttributePersistence.findByContestPhaseIdAndProposalId(contestPhaseId, proposalId);
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
    @Override
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
    @Override
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
    @Override
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
     * @throws SystemException in case of LR error
     */
    @Override
    public void setProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName,
            long longValue, String stringValue, double realValue) throws SystemException {
        ProposalContestPhaseAttribute attribute;
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
     * @throws SystemException in case of LR error
     */
    @Override
    public void deleteProposalContestPhaseAttribute(long proposalId, long contestPhaseId, String attributeName) throws SystemException {
        try {
            proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName));
        }
        catch (NoSuchProposalContestPhaseAttributeException ignored) { }
    }

    @Override
    public ProposalContestPhaseAttribute getAttributeOrNull(long proposalId, long contestPhaseId, String attributeName, long additionalId)
            throws PortalException, SystemException {
        try {
            return proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException ignored) { }
        return null;
    }

    @Override
    public long getAttributeLongValue(long proposalId, long contestPhaseId, String attributeName, long additionalId, long defaultVal)
            throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = proposalContestPhaseAttributeLocalService.getAttributeOrNull(proposalId, contestPhaseId, attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    @Override
    public String getAttributeStringValue(long proposalId, long contestPhaseId, String attributeName, long additionalId, String defaultVal)
            throws PortalException, SystemException {
        ProposalContestPhaseAttribute pa = proposalContestPhaseAttributeLocalService.getAttributeOrNull(proposalId, contestPhaseId, attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }
}
