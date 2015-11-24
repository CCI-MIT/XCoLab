package org.xcolab.helpers;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pdeboer
 *         First created on 28/10/13 at 11:36
 */
public class ProposalAttributeHelper {
    private final Proposal proposal;
    private final int version;
    private Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId;

    public ProposalAttributeHelper(Proposal proposal, int version) {
        this.proposal = proposal;
        this.version = version;
    }

    //initialization is expensive --> be lazy
    private void init() throws SystemException {
        List<ProposalAttribute> attributes = ProposalLocalServiceUtil.getAttributes(proposal.getProposalId(), version);
        if (attributesByNameAndAdditionalId == null) {
            attributesByNameAndAdditionalId = new HashMap<>();
            for (ProposalAttribute attribute : attributes) {
                Map<Long, ProposalAttribute> currentAttributes = getInnerMapOrCreate(
                        attribute.getName(), attributesByNameAndAdditionalId);

                ProposalAttribute currentAttribute = currentAttributes.get(attribute.getAdditionalId());

                //ignore older versions TODO: why are we even getting older versions from the db?
                if (currentAttribute == null || currentAttribute.getVersion() < attribute.getVersion()) {
                    currentAttributes.put(attribute.getAdditionalId(), attribute);
                }
            }
        }
    }

    private <SearchKey, MapKey, MapVal> Map<MapKey, MapVal> getInnerMapOrCreate(
                                                SearchKey searchKey, Map<SearchKey, Map<MapKey, MapVal>> searchMap) {
        Map<MapKey, MapVal> innerMap = searchMap.get(searchKey);
        if (innerMap == null) {
            innerMap = new HashMap<>();
        }
        return innerMap;
    }

    public boolean hasAttribute(String name) throws SystemException {
        return getAttributeOrNull(name, 0) != null;
    }

    public String attributeString(String name) throws SystemException {
        return getAttributeValueString(name, "");
    }

    public String getAttributeValueString(String attributeName, String defaultVal) throws SystemException {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }

    public String getAttributeValueString(String attributeName, long additionalId, String defaultVal) throws SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public long getAttributeValueLong(String attributeName, long defaultVal) throws SystemException {
        return getAttributeValueLong(attributeName, 0, defaultVal);
    }

    public long getAttributeValueLong(String attributeName, long additionalId, long defaultVal) throws SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public double getAttributeValueReal(String attributeName, long additionalId, double defaultVal) throws SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    public ProposalAttribute getAttributeOrNull(String attributeName, long additionalId) throws SystemException {
        if (attributesByNameAndAdditionalId == null) {
            init();
        }
        return attributesByNameAndAdditionalId.get(attributeName).get(additionalId);
    }

    public ProposalAttribute getAttributeOrNull(String attributeName) throws SystemException {
        return getAttributeOrNull(attributeName, 0L);
    }
}
