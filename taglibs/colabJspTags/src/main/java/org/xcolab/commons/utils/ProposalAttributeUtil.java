package org.xcolab.commons.utils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author pdeboer
 *         First created on 28/10/13 at 11:36
 */
public class ProposalAttributeUtil {
    Proposal proposal;
    int version;
    private List<ProposalAttribute> attributes;

    public ProposalAttributeUtil(Proposal proposal, int version) {
        this.proposal = proposal;
        this.version = version;
    }

    public boolean hasAttribute(String name) throws PortalException, SystemException {
        return getAttributeOrNull(name, 0) != null;
    }

    public String attributeString(String name) throws PortalException, SystemException {
        return getAttributeValueString(name, "");
    }

    public String getAttributeValueString(String attributeName, String defaultVal) throws PortalException, SystemException {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }


    public String getAttributeValueString(String attributeName, long additionalId, String defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public long getAttributeValueLong(String attributeName, long defaultVal) throws PortalException, SystemException {
        return getAttributeValueLong(attributeName, 0, defaultVal);
    }

    public long getAttributeValueLong(String attributeName, long additionalId, long defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();

    }

    public double getAttributeValueReal(String attributeName, long additionalId, double defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    public ProposalAttribute getAttributeOrNull(String attributeName, long additionalId) throws PortalException, SystemException {
        try {
        	if (attributes == null) {
        		attributes = ProposalLocalServiceUtil.getAttributes(proposal.getProposalId(), version);
        	}
        	for (ProposalAttribute attr: attributes) {
        		if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) { 
        			return attr;
        		}
        	}
        }
        catch (NoSuchProposalAttributeException e) {
        }
        return null;
    }

    public ProposalAttribute getLatestAttributeOrNull(String attributeName) throws PortalException, SystemException {
        try {
        	if (attributes == null) {
        		attributes = ProposalLocalServiceUtil.getAttributes(proposal.getProposalId(), version);
        	}
        	ProposalAttribute retAttr = null;
        	for (ProposalAttribute attr: attributes) {
        		if (attr.getName().equals(attributeName)) { 
        			if (retAttr == null || retAttr.getVersionWhenCreated() < attr.getVersionWhenCreated()) 
        				retAttr = attr;
        		}
        	}
        	return retAttr;
        }
        catch (NoSuchProposalAttributeException e) {
        }
        return null;
    }    
}
