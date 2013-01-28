package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhaseType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ContestPhaseType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseType
 * @generated
 */
public class ContestPhaseTypeCacheModel implements CacheModel<ContestPhaseType>,
    Serializable {
    public long id;
    public String name;
    public String description;
    public String status;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", status=");
        sb.append(status);
        sb.append("}");

        return sb.toString();
    }

    public ContestPhaseType toEntityModel() {
        ContestPhaseTypeImpl contestPhaseTypeImpl = new ContestPhaseTypeImpl();

        contestPhaseTypeImpl.setId(id);

        if (name == null) {
            contestPhaseTypeImpl.setName(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setName(name);
        }

        if (description == null) {
            contestPhaseTypeImpl.setDescription(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setDescription(description);
        }

        if (status == null) {
            contestPhaseTypeImpl.setStatus(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setStatus(status);
        }

        contestPhaseTypeImpl.resetOriginalValues();

        return contestPhaseTypeImpl;
    }
}
