package com.ext.portlet.model.impl;

import com.ext.portlet.model.OntologySpace;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing OntologySpace in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpace
 * @generated
 */
public class OntologySpaceCacheModel implements CacheModel<OntologySpace>,
    Serializable {
    public long id;
    public String name;
    public String description;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append("}");

        return sb.toString();
    }

    public OntologySpace toEntityModel() {
        OntologySpaceImpl ontologySpaceImpl = new OntologySpaceImpl();

        ontologySpaceImpl.setId(id);

        if (name == null) {
            ontologySpaceImpl.setName(StringPool.BLANK);
        } else {
            ontologySpaceImpl.setName(name);
        }

        if (description == null) {
            ontologySpaceImpl.setDescription(StringPool.BLANK);
        } else {
            ontologySpaceImpl.setDescription(description);
        }

        ontologySpaceImpl.resetOriginalValues();

        return ontologySpaceImpl;
    }
}
