package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ProposalAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttribute
 * @generated
 */
public class ProposalAttributeCacheModel implements CacheModel<ProposalAttribute>,
    Serializable {
    public long id;
    public long proposalId;
    public int version;
    public int versionWhenCreated;
    public String name;
    public long additionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", version=");
        sb.append(version);
        sb.append(", versionWhenCreated=");
        sb.append(versionWhenCreated);
        sb.append(", name=");
        sb.append(name);
        sb.append(", additionalId=");
        sb.append(additionalId);
        sb.append(", numericValue=");
        sb.append(numericValue);
        sb.append(", stringValue=");
        sb.append(stringValue);
        sb.append(", realValue=");
        sb.append(realValue);
        sb.append("}");

        return sb.toString();
    }

    public ProposalAttribute toEntityModel() {
        ProposalAttributeImpl proposalAttributeImpl = new ProposalAttributeImpl();

        proposalAttributeImpl.setId(id);
        proposalAttributeImpl.setProposalId(proposalId);
        proposalAttributeImpl.setVersion(version);
        proposalAttributeImpl.setVersionWhenCreated(versionWhenCreated);

        if (name == null) {
            proposalAttributeImpl.setName(StringPool.BLANK);
        } else {
            proposalAttributeImpl.setName(name);
        }

        proposalAttributeImpl.setAdditionalId(additionalId);
        proposalAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            proposalAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            proposalAttributeImpl.setStringValue(stringValue);
        }

        proposalAttributeImpl.setRealValue(realValue);

        proposalAttributeImpl.resetOriginalValues();

        return proposalAttributeImpl;
    }
}
