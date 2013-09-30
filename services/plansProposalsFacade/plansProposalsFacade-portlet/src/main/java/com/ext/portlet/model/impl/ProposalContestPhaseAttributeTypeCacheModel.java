package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalContestPhaseAttributeType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ProposalContestPhaseAttributeType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeType
 * @generated
 */
public class ProposalContestPhaseAttributeTypeCacheModel implements CacheModel<ProposalContestPhaseAttributeType>,
    Serializable {
    public long id;
    public String ribbon;
    public String hoverText;
    public String description;
    public boolean copyOnPromote;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", ribbon=");
        sb.append(ribbon);
        sb.append(", hoverText=");
        sb.append(hoverText);
        sb.append(", description=");
        sb.append(description);
        sb.append(", copyOnPromote=");
        sb.append(copyOnPromote);
        sb.append("}");

        return sb.toString();
    }

    public ProposalContestPhaseAttributeType toEntityModel() {
        ProposalContestPhaseAttributeTypeImpl proposalContestPhaseAttributeTypeImpl =
            new ProposalContestPhaseAttributeTypeImpl();

        proposalContestPhaseAttributeTypeImpl.setId(id);

        if (ribbon == null) {
            proposalContestPhaseAttributeTypeImpl.setRibbon(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeTypeImpl.setRibbon(ribbon);
        }

        if (hoverText == null) {
            proposalContestPhaseAttributeTypeImpl.setHoverText(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeTypeImpl.setHoverText(hoverText);
        }

        if (description == null) {
            proposalContestPhaseAttributeTypeImpl.setDescription(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeTypeImpl.setDescription(description);
        }

        proposalContestPhaseAttributeTypeImpl.setCopyOnPromote(copyOnPromote);

        proposalContestPhaseAttributeTypeImpl.resetOriginalValues();

        return proposalContestPhaseAttributeTypeImpl;
    }
}
