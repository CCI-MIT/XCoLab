package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanSectionDefinition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanSectionDefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinition
 * @generated
 */
public class PlanSectionDefinitionCacheModel implements CacheModel<PlanSectionDefinition>,
    Serializable {
    public long id;
    public String adminTitle;
    public String title;
    public String defaultText;
    public String helpText;
    public int characterLimit;
    public long focusAreaId;
    public boolean locked;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", adminTitle=");
        sb.append(adminTitle);
        sb.append(", title=");
        sb.append(title);
        sb.append(", defaultText=");
        sb.append(defaultText);
        sb.append(", helpText=");
        sb.append(helpText);
        sb.append(", characterLimit=");
        sb.append(characterLimit);
        sb.append(", focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", locked=");
        sb.append(locked);
        sb.append("}");

        return sb.toString();
    }

    public PlanSectionDefinition toEntityModel() {
        PlanSectionDefinitionImpl planSectionDefinitionImpl = new PlanSectionDefinitionImpl();

        planSectionDefinitionImpl.setId(id);

        if (adminTitle == null) {
            planSectionDefinitionImpl.setAdminTitle(StringPool.BLANK);
        } else {
            planSectionDefinitionImpl.setAdminTitle(adminTitle);
        }

        if (title == null) {
            planSectionDefinitionImpl.setTitle(StringPool.BLANK);
        } else {
            planSectionDefinitionImpl.setTitle(title);
        }

        if (defaultText == null) {
            planSectionDefinitionImpl.setDefaultText(StringPool.BLANK);
        } else {
            planSectionDefinitionImpl.setDefaultText(defaultText);
        }

        if (helpText == null) {
            planSectionDefinitionImpl.setHelpText(StringPool.BLANK);
        } else {
            planSectionDefinitionImpl.setHelpText(helpText);
        }

        planSectionDefinitionImpl.setCharacterLimit(characterLimit);
        planSectionDefinitionImpl.setFocusAreaId(focusAreaId);
        planSectionDefinitionImpl.setLocked(locked);

        planSectionDefinitionImpl.resetOriginalValues();

        return planSectionDefinitionImpl;
    }
}
