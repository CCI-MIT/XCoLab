package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplate
 * @generated
 */
public class PlanTemplateCacheModel implements CacheModel<PlanTemplate>,
    Externalizable {
    public long id;
    public String name;
    public Long baseTemplateId;
    public Long impactSeriesTemplateId;
    public Long focusAreaListTemplateId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", baseTemplateId=");
        sb.append(baseTemplateId);
        sb.append(", impactSeriesTemplateId=");
        sb.append(impactSeriesTemplateId);
        sb.append(", focusAreaListTemplateId=");
        sb.append(focusAreaListTemplateId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanTemplate toEntityModel() {
        PlanTemplateImpl planTemplateImpl = new PlanTemplateImpl();

        planTemplateImpl.setId(id);

        if (name == null) {
            planTemplateImpl.setName(StringPool.BLANK);
        } else {
            planTemplateImpl.setName(name);
        }

        planTemplateImpl.setBaseTemplateId(baseTemplateId);
        planTemplateImpl.setImpactSeriesTemplateId(impactSeriesTemplateId);
        planTemplateImpl.setFocusAreaListTemplateId(focusAreaListTemplateId);

        planTemplateImpl.resetOriginalValues();

        return planTemplateImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        name = objectInput.readUTF();
        baseTemplateId = objectInput.readLong();
        impactSeriesTemplateId = objectInput.readLong();
        focusAreaListTemplateId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeLong(baseTemplateId);
        objectOutput.writeLong(impactSeriesTemplateId);
        objectOutput.writeLong(focusAreaListTemplateId);
    }
}
