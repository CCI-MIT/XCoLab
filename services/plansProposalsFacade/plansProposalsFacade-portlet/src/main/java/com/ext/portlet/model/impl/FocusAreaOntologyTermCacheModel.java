package com.ext.portlet.model.impl;

import com.ext.portlet.model.FocusAreaOntologyTerm;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FocusAreaOntologyTerm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTerm
 * @generated
 */
public class FocusAreaOntologyTermCacheModel implements CacheModel<FocusAreaOntologyTerm>,
    Externalizable {
    public long focusAreaId;
    public long ontologyTermId;
    public int order;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", ontologyTermId=");
        sb.append(ontologyTermId);
        sb.append(", order=");
        sb.append(order);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public FocusAreaOntologyTerm toEntityModel() {
        FocusAreaOntologyTermImpl focusAreaOntologyTermImpl = new FocusAreaOntologyTermImpl();

        focusAreaOntologyTermImpl.setFocusAreaId(focusAreaId);
        focusAreaOntologyTermImpl.setOntologyTermId(ontologyTermId);
        focusAreaOntologyTermImpl.setOrder(order);

        focusAreaOntologyTermImpl.resetOriginalValues();

        return focusAreaOntologyTermImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        focusAreaId = objectInput.readLong();
        ontologyTermId = objectInput.readLong();
        order = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(focusAreaId);
        objectOutput.writeLong(ontologyTermId);
        objectOutput.writeInt(order);
    }
}
