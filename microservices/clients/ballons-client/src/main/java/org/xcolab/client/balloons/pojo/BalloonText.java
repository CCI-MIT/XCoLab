package org.xcolab.client.balloons.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalloonText implements Serializable {

    public static final TypeProvider<BalloonText> TYPES = new TypeProvider<>(BalloonText.class,
            new ParameterizedTypeReference<List<BalloonText>>() {});

    private static final long serialVersionUID = 669206607;

    private Long id;
    private String name;
    private String textbeforeform;
    private String textbeforesharebuttons;
    private String emailtemplate;
    private String emailsubjecttemplate;
    private String shareTitle;
    private String shareDescription;
    private Boolean enabled;

    public BalloonText() {}

    public BalloonText(BalloonText value) {
        this.id = value.id;
        this.name = value.name;
        this.textbeforeform = value.textbeforeform;
        this.textbeforesharebuttons = value.textbeforesharebuttons;
        this.emailtemplate = value.emailtemplate;
        this.emailsubjecttemplate = value.emailsubjecttemplate;
        this.shareTitle = value.shareTitle;
        this.shareDescription = value.shareDescription;
        this.enabled = value.enabled;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextBeforeForm() {
        return this.textbeforeform;
    }

    public void setTextBeforeForm(String textbeforeform) {
        this.textbeforeform = textbeforeform;
    }

    public String getTextBeforeShareButtons() {
        return this.textbeforesharebuttons;
    }

    public void setTextBeforeShareButtons(String textbeforesharebuttons) {
        this.textbeforesharebuttons = textbeforesharebuttons;
    }

    public String getEmailTemplate() {
        return this.emailtemplate;
    }

    public void setEmailTemplate(String emailtemplate) {
        this.emailtemplate = emailtemplate;
    }

    public String getEmailSubjectTemplate() {
        return this.emailsubjecttemplate;
    }

    public void setEmailSubjectTemplate(String emailsubjecttemplate) {
        this.emailsubjecttemplate = emailsubjecttemplate;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BalloonText)) {
            return false;
        }
        BalloonText that = (BalloonText) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(textbeforeform, that.textbeforeform)
                && Objects.equals(textbeforesharebuttons, that.textbeforesharebuttons)
                && Objects.equals(emailtemplate, that.emailtemplate)
                && Objects.equals(emailsubjecttemplate, that.emailsubjecttemplate)
                && Objects.equals(getShareTitle(), that.getShareTitle())
                && Objects.equals(getShareDescription(), that.getShareDescription())
                && Objects.equals(getEnabled(), that.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getId(), getName(), textbeforeform, textbeforesharebuttons, emailtemplate,
                        emailsubjecttemplate, getShareTitle(), getShareDescription(), getEnabled());
    }

    @Override
    public String toString() {
        return "BalloonText{" + "id_=" + id + ", name='" + name + '\'' + ", textbeforeform='"
                + textbeforeform + '\'' + ", textbeforesharebuttons='" + textbeforesharebuttons
                + '\'' + ", emailtemplate='" + emailtemplate + '\'' + ", emailsubjecttemplate='"
                + emailsubjecttemplate + '\'' + ", shareTitle='" + shareTitle + '\''
                + ", shareDescription='" + shareDescription + '\'' + ", enabled=" + enabled + '}';
    }
}
