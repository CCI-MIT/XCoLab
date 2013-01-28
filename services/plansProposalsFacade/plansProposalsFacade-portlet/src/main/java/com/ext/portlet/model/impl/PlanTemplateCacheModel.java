package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplate
 * @generated
 */
public class PlanTemplateCacheModel implements CacheModel<PlanTemplate>,
    Serializable {
    public long id;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    public PlanTemplate toEntityModel() {
        PlanTemplateImpl planTemplateImpl = new PlanTemplateImpl();

        planTemplateImpl.setId(id);

        if (name == null) {
            planTemplateImpl.setName(StringPool.BLANK);
        } else {
            planTemplateImpl.setName(name);
        }

        planTemplateImpl.resetOriginalValues();

        return planTemplateImpl;
    }
}
