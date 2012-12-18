package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologyTermEntity;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing OntologyTermEntity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntity
 * @generated
 */
public class OntologyTermEntityCacheModel implements CacheModel<OntologyTermEntity>,
    Serializable {
    public long id;
    public long termId;
    public long classNameId;
    public long classPK;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", termId=");
        sb.append(termId);
        sb.append(", classNameId=");
        sb.append(classNameId);
        sb.append(", classPK=");
        sb.append(classPK);
        sb.append("}");

        return sb.toString();
    }

    public OntologyTermEntity toEntityModel() {
        OntologyTermEntityImpl ontologyTermEntityImpl = new OntologyTermEntityImpl();

        ontologyTermEntityImpl.setId(id);
        ontologyTermEntityImpl.setTermId(termId);
        ontologyTermEntityImpl.setClassNameId(classNameId);
        ontologyTermEntityImpl.setClassPK(classPK);

        ontologyTermEntityImpl.resetOriginalValues();

        return ontologyTermEntityImpl;
    }
}
