package com.ext.portlet.model.impl;

import com.ext.portlet.model.OntologyTermEntity;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OntologyTermEntity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntity
 * @generated
 */
public class OntologyTermEntityCacheModel implements CacheModel<OntologyTermEntity>,
    Externalizable {
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

    @Override
    public OntologyTermEntity toEntityModel() {
        OntologyTermEntityImpl ontologyTermEntityImpl = new OntologyTermEntityImpl();

        ontologyTermEntityImpl.setId(id);
        ontologyTermEntityImpl.setTermId(termId);
        ontologyTermEntityImpl.setClassNameId(classNameId);
        ontologyTermEntityImpl.setClassPK(classPK);

        ontologyTermEntityImpl.resetOriginalValues();

        return ontologyTermEntityImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        termId = objectInput.readLong();
        classNameId = objectInput.readLong();
        classPK = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(termId);
        objectOutput.writeLong(classNameId);
        objectOutput.writeLong(classPK);
    }
}
