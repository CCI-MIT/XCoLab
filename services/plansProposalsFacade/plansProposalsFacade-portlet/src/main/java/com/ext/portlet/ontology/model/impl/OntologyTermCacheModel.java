package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologyTerm;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing OntologyTerm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTerm
 * @generated
 */
public class OntologyTermCacheModel implements CacheModel<OntologyTerm>,
    Serializable {
    public Long id;
    public Long parentId;
    public Long ontologySpaceId;
    public String name;
    public String descriptionUrl;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", parentId=");
        sb.append(parentId);
        sb.append(", ontologySpaceId=");
        sb.append(ontologySpaceId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", descriptionUrl=");
        sb.append(descriptionUrl);
        sb.append("}");

        return sb.toString();
    }

    public OntologyTerm toEntityModel() {
        OntologyTermImpl ontologyTermImpl = new OntologyTermImpl();

        ontologyTermImpl.setId(id);
        ontologyTermImpl.setParentId(parentId);
        ontologyTermImpl.setOntologySpaceId(ontologySpaceId);

        if (name == null) {
            ontologyTermImpl.setName(StringPool.BLANK);
        } else {
            ontologyTermImpl.setName(name);
        }

        if (descriptionUrl == null) {
            ontologyTermImpl.setDescriptionUrl(StringPool.BLANK);
        } else {
            ontologyTermImpl.setDescriptionUrl(descriptionUrl);
        }

        ontologyTermImpl.resetOriginalValues();

        return ontologyTermImpl;
    }
}
