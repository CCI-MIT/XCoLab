package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhaseRibbonType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ContestPhaseRibbonType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonType
 * @generated
 */
public class ContestPhaseRibbonTypeCacheModel implements CacheModel<ContestPhaseRibbonType>,
    Serializable {
    public long id;
    public int ribbon;
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

    public ContestPhaseRibbonType toEntityModel() {
        ContestPhaseRibbonTypeImpl contestPhaseRibbonTypeImpl = new ContestPhaseRibbonTypeImpl();

        contestPhaseRibbonTypeImpl.setId(id);
        contestPhaseRibbonTypeImpl.setRibbon(ribbon);

        if (hoverText == null) {
            contestPhaseRibbonTypeImpl.setHoverText(StringPool.BLANK);
        } else {
            contestPhaseRibbonTypeImpl.setHoverText(hoverText);
        }

        if (description == null) {
            contestPhaseRibbonTypeImpl.setDescription(StringPool.BLANK);
        } else {
            contestPhaseRibbonTypeImpl.setDescription(description);
        }

        contestPhaseRibbonTypeImpl.setCopyOnPromote(copyOnPromote);

        contestPhaseRibbonTypeImpl.resetOriginalValues();

        return contestPhaseRibbonTypeImpl;
    }
}
