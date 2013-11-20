package com.ext.portlet.model.impl;

import com.ext.portlet.model.OntologyTerm;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OntologyTerm in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTerm
 * @generated
 */
public class OntologyTermCacheModel implements CacheModel<OntologyTerm>,
    Externalizable {
    public long id;
    public long parentId;
    public long ontologySpaceId;
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

    @Override
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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        parentId = objectInput.readLong();
        ontologySpaceId = objectInput.readLong();
        name = objectInput.readUTF();
        descriptionUrl = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(parentId);
        objectOutput.writeLong(ontologySpaceId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (descriptionUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(descriptionUrl);
        }
    }
}
