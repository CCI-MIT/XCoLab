package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhaseRibbonType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestPhaseRibbonType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonType
 * @generated
 */
public class ContestPhaseRibbonTypeCacheModel implements CacheModel<ContestPhaseRibbonType>,
    Externalizable {
    public long id;
    public int ribbon;
    public String hoverText;
    public String description;
    public boolean copyOnPromote;
    public int sortOrder;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

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
        sb.append(", sortOrder=");
        sb.append(sortOrder);
        sb.append("}");

        return sb.toString();
    }

    @Override
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
        contestPhaseRibbonTypeImpl.setSortOrder(sortOrder);

        contestPhaseRibbonTypeImpl.resetOriginalValues();

        return contestPhaseRibbonTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        ribbon = objectInput.readInt();
        hoverText = objectInput.readUTF();
        description = objectInput.readUTF();
        copyOnPromote = objectInput.readBoolean();
        sortOrder = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeInt(ribbon);

        if (hoverText == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(hoverText);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeBoolean(copyOnPromote);
        objectOutput.writeInt(sortOrder);
    }
}
