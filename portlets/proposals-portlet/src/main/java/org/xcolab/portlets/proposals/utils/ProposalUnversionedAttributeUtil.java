package org.xcolab.portlets.proposals.utils;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Created by carlosbpf on 2/16/16.
 */
public class ProposalUnversionedAttributeUtil {

    public static void createOrUpdateProposalUnversionedAttribute(long authorId,
                                                                   String attributeValue,
                                                                   String attributeName,
                                                                   Proposal proposal,
                                                                   List<ProposalUnversionedAttribute> unversionedAttributes)
            throws PortalException, SystemException {
        ProposalUnversionedAttribute pua = null;
        pua = getCurrentProposalUnversionedAttribute(attributeName.toString(),unversionedAttributes);
        if (pua == null) {
            pua = new ProposalUnversionedAttribute();
            pua.setCreateAuthorId(authorId);
            pua.setCreateDate(new Timestamp(new Date().getTime()));
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setName(attributeName);
            pua.setStringValue(attributeValue);
            pua.setProposalId(proposal.getProposalId());
            ProposalAttributeClientUtil.createProposalUnversionedAttribute(pua);
        } else {
            pua.setCreateAuthorId(authorId);
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setStringValue(attributeValue);
            ProposalAttributeClientUtil.updateProposalUnversionedAttribute(pua);
        }
    }

    private static ProposalUnversionedAttribute getCurrentProposalUnversionedAttribute(String attributeName,
                                                                                       List<ProposalUnversionedAttribute> currentAttributes){
        if ( currentAttributes != null && !currentAttributes.isEmpty() ){
            for(ProposalUnversionedAttribute currentAttribute : currentAttributes){
                if (currentAttribute.getName().equals(attributeName))
                    return currentAttribute;
            }

        }
        return null;
    }
}