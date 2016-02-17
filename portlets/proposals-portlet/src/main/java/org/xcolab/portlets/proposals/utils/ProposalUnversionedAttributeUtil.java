package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.model.ProposalUnversionedAttribute;
import com.ext.portlet.service.ProposalUnversionedAttributeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.Date;
import java.util.List;

/**
 * Created by carlosbpf on 2/16/16.
 */
public class ProposalUnversionedAttributeUtil {

    public static void createOrUpdateProposalUnversionedAttribute(long authorId,
                                                                   String attributeValue,
                                                                   String attributeName,
                                                                   ProposalWrapper proposal,
                                                                   List<ProposalUnversionedAttribute> unversionedAttributes)
            throws PortalException, SystemException {
        ProposalUnversionedAttribute pua = null;
        pua = getCurrentProposalUnversionedAttribute(attributeName.toString(),unversionedAttributes);
        if(pua == null) {
            pua = ProposalUnversionedAttributeLocalServiceUtil.createProposalUnversionedAttribute(CounterLocalServiceUtil.increment(ProposalUnversionedAttribute.class.getName()));
            pua.setCreateAuthorId(authorId);
            pua.setCreateDate(new Date());
            pua.setLastUpdateDate(new Date());
            pua.setName(attributeName);
            pua.setStringValue(attributeValue);
            pua.setProposalId(proposal.getProposalId());
            ProposalUnversionedAttributeLocalServiceUtil.addProposalUnversionedAttribute(pua);
        }else{
            pua.setCreateAuthorId(authorId);
            pua.setLastUpdateDate(new Date());
            pua.setStringValue(attributeValue);
            ProposalUnversionedAttributeLocalServiceUtil.updateProposalUnversionedAttribute(pua);
        }
    }

    private static ProposalUnversionedAttribute getCurrentProposalUnversionedAttribute(String attributeName,
                                                                                       List<ProposalUnversionedAttribute> currentAttributes){
        if(currentAttributes!= null &&! currentAttributes.isEmpty()){
            for(ProposalUnversionedAttribute currentAttribute : currentAttributes){
                if(currentAttribute.getName().equals(attributeName))
                    return currentAttribute;
            }

        }
        return null;
    }
}
