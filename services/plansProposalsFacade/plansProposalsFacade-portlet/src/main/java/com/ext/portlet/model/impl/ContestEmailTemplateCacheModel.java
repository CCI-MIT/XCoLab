package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestEmailTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestEmailTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplate
 * @generated
 */
public class ContestEmailTemplateCacheModel implements CacheModel<ContestEmailTemplate>,
    Externalizable {
    public String type;
    public String subject;
    public String header;
    public String footer;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{type=");
        sb.append(type);
        sb.append(", subject=");
        sb.append(subject);
        sb.append(", header=");
        sb.append(header);
        sb.append(", footer=");
        sb.append(footer);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestEmailTemplate toEntityModel() {
        ContestEmailTemplateImpl contestEmailTemplateImpl = new ContestEmailTemplateImpl();

        if (type == null) {
            contestEmailTemplateImpl.setType(StringPool.BLANK);
        } else {
            contestEmailTemplateImpl.setType(type);
        }

        if (subject == null) {
            contestEmailTemplateImpl.setSubject(StringPool.BLANK);
        } else {
            contestEmailTemplateImpl.setSubject(subject);
        }

        if (header == null) {
            contestEmailTemplateImpl.setHeader(StringPool.BLANK);
        } else {
            contestEmailTemplateImpl.setHeader(header);
        }

        if (footer == null) {
            contestEmailTemplateImpl.setFooter(StringPool.BLANK);
        } else {
            contestEmailTemplateImpl.setFooter(footer);
        }

        contestEmailTemplateImpl.resetOriginalValues();

        return contestEmailTemplateImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        type = objectInput.readUTF();
        subject = objectInput.readUTF();
        header = objectInput.readUTF();
        footer = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (type == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(type);
        }

        if (subject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subject);
        }

        if (header == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(header);
        }

        if (footer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(footer);
        }
    }
}
