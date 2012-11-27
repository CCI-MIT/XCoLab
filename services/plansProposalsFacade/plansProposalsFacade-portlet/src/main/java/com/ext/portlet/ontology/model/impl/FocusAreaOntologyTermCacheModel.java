package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing FocusAreaOntologyTerm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTerm
 * @generated
 */
public class FocusAreaOntologyTermCacheModel implements CacheModel<FocusAreaOntologyTerm>,
    Serializable {
    public Long focusAreaId;
    public Long ontologyTermId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", ontologyTermId=");
        sb.append(ontologyTermId);
        sb.append("}");

        return sb.toString();
    }

    public FocusAreaOntologyTerm toEntityModel() {
        FocusAreaOntologyTermImpl focusAreaOntologyTermImpl = new FocusAreaOntologyTermImpl();

        focusAreaOntologyTermImpl.setFocusAreaId(focusAreaId);
        focusAreaOntologyTermImpl.setOntologyTermId(ontologyTermId);

        focusAreaOntologyTermImpl.resetOriginalValues();

        return focusAreaOntologyTermImpl;
    }
}
