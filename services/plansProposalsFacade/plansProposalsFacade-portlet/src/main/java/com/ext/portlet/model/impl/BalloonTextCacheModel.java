package com.ext.portlet.model.impl;

import com.ext.portlet.model.BalloonText;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing BalloonText in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonText
 * @generated
 */
public class BalloonTextCacheModel implements CacheModel<BalloonText>,
    Externalizable {
    public long id;
    public String name;
    public String textBeforeForm;
    public String textAfterForm;
    public String textBeforeShareButtons;
    public String textAfterShareButtons;
    public String acceptTosText;
    public String emailTemplate;
    public String emailSubjectTemplate;
    public String twitterDescription;
    public String twitterSubject;
    public String facebookDescription;
    public String facebookSubject;
    public boolean enabled;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(29);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", textBeforeForm=");
        sb.append(textBeforeForm);
        sb.append(", textAfterForm=");
        sb.append(textAfterForm);
        sb.append(", textBeforeShareButtons=");
        sb.append(textBeforeShareButtons);
        sb.append(", textAfterShareButtons=");
        sb.append(textAfterShareButtons);
        sb.append(", acceptTosText=");
        sb.append(acceptTosText);
        sb.append(", emailTemplate=");
        sb.append(emailTemplate);
        sb.append(", emailSubjectTemplate=");
        sb.append(emailSubjectTemplate);
        sb.append(", twitterDescription=");
        sb.append(twitterDescription);
        sb.append(", twitterSubject=");
        sb.append(twitterSubject);
        sb.append(", facebookDescription=");
        sb.append(facebookDescription);
        sb.append(", facebookSubject=");
        sb.append(facebookSubject);
        sb.append(", enabled=");
        sb.append(enabled);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BalloonText toEntityModel() {
        BalloonTextImpl balloonTextImpl = new BalloonTextImpl();

        balloonTextImpl.setId(id);

        if (name == null) {
            balloonTextImpl.setName(StringPool.BLANK);
        } else {
            balloonTextImpl.setName(name);
        }

        if (textBeforeForm == null) {
            balloonTextImpl.setTextBeforeForm(StringPool.BLANK);
        } else {
            balloonTextImpl.setTextBeforeForm(textBeforeForm);
        }

        if (textAfterForm == null) {
            balloonTextImpl.setTextAfterForm(StringPool.BLANK);
        } else {
            balloonTextImpl.setTextAfterForm(textAfterForm);
        }

        if (textBeforeShareButtons == null) {
            balloonTextImpl.setTextBeforeShareButtons(StringPool.BLANK);
        } else {
            balloonTextImpl.setTextBeforeShareButtons(textBeforeShareButtons);
        }

        if (textAfterShareButtons == null) {
            balloonTextImpl.setTextAfterShareButtons(StringPool.BLANK);
        } else {
            balloonTextImpl.setTextAfterShareButtons(textAfterShareButtons);
        }

        if (acceptTosText == null) {
            balloonTextImpl.setAcceptTosText(StringPool.BLANK);
        } else {
            balloonTextImpl.setAcceptTosText(acceptTosText);
        }

        if (emailTemplate == null) {
            balloonTextImpl.setEmailTemplate(StringPool.BLANK);
        } else {
            balloonTextImpl.setEmailTemplate(emailTemplate);
        }

        if (emailSubjectTemplate == null) {
            balloonTextImpl.setEmailSubjectTemplate(StringPool.BLANK);
        } else {
            balloonTextImpl.setEmailSubjectTemplate(emailSubjectTemplate);
        }

        if (twitterDescription == null) {
            balloonTextImpl.setTwitterDescription(StringPool.BLANK);
        } else {
            balloonTextImpl.setTwitterDescription(twitterDescription);
        }

        if (twitterSubject == null) {
            balloonTextImpl.setTwitterSubject(StringPool.BLANK);
        } else {
            balloonTextImpl.setTwitterSubject(twitterSubject);
        }

        if (facebookDescription == null) {
            balloonTextImpl.setFacebookDescription(StringPool.BLANK);
        } else {
            balloonTextImpl.setFacebookDescription(facebookDescription);
        }

        if (facebookSubject == null) {
            balloonTextImpl.setFacebookSubject(StringPool.BLANK);
        } else {
            balloonTextImpl.setFacebookSubject(facebookSubject);
        }

        balloonTextImpl.setEnabled(enabled);

        balloonTextImpl.resetOriginalValues();

        return balloonTextImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        name = objectInput.readUTF();
        textBeforeForm = objectInput.readUTF();
        textAfterForm = objectInput.readUTF();
        textBeforeShareButtons = objectInput.readUTF();
        textAfterShareButtons = objectInput.readUTF();
        acceptTosText = objectInput.readUTF();
        emailTemplate = objectInput.readUTF();
        emailSubjectTemplate = objectInput.readUTF();
        twitterDescription = objectInput.readUTF();
        twitterSubject = objectInput.readUTF();
        facebookDescription = objectInput.readUTF();
        facebookSubject = objectInput.readUTF();
        enabled = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (textBeforeForm == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(textBeforeForm);
        }

        if (textAfterForm == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(textAfterForm);
        }

        if (textBeforeShareButtons == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(textBeforeShareButtons);
        }

        if (textAfterShareButtons == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(textAfterShareButtons);
        }

        if (acceptTosText == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acceptTosText);
        }

        if (emailTemplate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailTemplate);
        }

        if (emailSubjectTemplate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailSubjectTemplate);
        }

        if (twitterDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(twitterDescription);
        }

        if (twitterSubject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(twitterSubject);
        }

        if (facebookDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(facebookDescription);
        }

        if (facebookSubject == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(facebookSubject);
        }

        objectOutput.writeBoolean(enabled);
    }
}
