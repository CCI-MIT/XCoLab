package org.xcolab.client.balloons.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalloonText implements Serializable {

    private static final long serialVersionUID = 669206607;

    public static final TypeProvider<BalloonText> TYPES =
            new TypeProvider<>(BalloonText.class,
                    new ParameterizedTypeReference<List<BalloonText>>() {
                    });

    private Long    id_;
    private String  name;
    private String  textbeforeform;
    private String  textafterform;
    private String  textbeforesharebuttons;
    private String  textaftersharebuttons;
    private String  accepttostext;
    private String  emailtemplate;
    private String  emailsubjecttemplate;
    private String  twitterdescription;
    private String  twittersubject;
    private String  facebookdescription;
    private String  facebooksubject;
    private Boolean enabled;

    public BalloonText() {}

    public BalloonText(BalloonText value) {
        this.id_ = value.id_;
        this.name = value.name;
        this.textbeforeform = value.textbeforeform;
        this.textafterform = value.textafterform;
        this.textbeforesharebuttons = value.textbeforesharebuttons;
        this.textaftersharebuttons = value.textaftersharebuttons;
        this.accepttostext = value.accepttostext;
        this.emailtemplate = value.emailtemplate;
        this.emailsubjecttemplate = value.emailsubjecttemplate;
        this.twitterdescription = value.twitterdescription;
        this.twittersubject = value.twittersubject;
        this.facebookdescription = value.facebookdescription;
        this.facebooksubject = value.facebooksubject;
        this.enabled = value.enabled;
    }

    public BalloonText(
        Long    id_,
        String  name,
        String  textbeforeform,
        String  textafterform,
        String  textbeforesharebuttons,
        String  textaftersharebuttons,
        String  accepttostext,
        String  emailtemplate,
        String  emailsubjecttemplate,
        String  twitterdescription,
        String  twittersubject,
        String  facebookdescription,
        String  facebooksubject,
        Boolean enabled
    ) {
        this.id_ = id_;
        this.name = name;
        this.textbeforeform = textbeforeform;
        this.textafterform = textafterform;
        this.textbeforesharebuttons = textbeforesharebuttons;
        this.textaftersharebuttons = textaftersharebuttons;
        this.accepttostext = accepttostext;
        this.emailtemplate = emailtemplate;
        this.emailsubjecttemplate = emailsubjecttemplate;
        this.twitterdescription = twitterdescription;
        this.twittersubject = twittersubject;
        this.facebookdescription = facebookdescription;
        this.facebooksubject = facebooksubject;
        this.enabled = enabled;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
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

    public String getTextAfterForm() {
        return this.textafterform;
    }

    public void setTextAfterForm(String textafterform) {
        this.textafterform = textafterform;
    }

    public String getTextBeforeShareButtons() {
        return this.textbeforesharebuttons;
    }

    public void setTextBeforeShareButtons(String textbeforesharebuttons) {
        this.textbeforesharebuttons = textbeforesharebuttons;
    }

    public String getTextAfterShareButtons() {
        return this.textaftersharebuttons;
    }

    public void setTextAfterShareButtons(String textaftersharebuttons) {
        this.textaftersharebuttons = textaftersharebuttons;
    }

    public String getAcceptTosText() {
        return this.accepttostext;
    }

    public void setAcceptTosText(String accepttostext) {
        this.accepttostext = accepttostext;
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

    public String getTwitterDescription() {
        return this.twitterdescription;
    }

    public void setTwitterDescription(String twitterdescription) {
        this.twitterdescription = twitterdescription;
    }

    public String getTwitterSubject() {
        return this.twittersubject;
    }

    public void setTwitterSubject(String twittersubject) {
        this.twittersubject = twittersubject;
    }

    public String getFacebookDescription() {
        return this.facebookdescription;
    }

    public void setFacebookDescription(String facebookdescription) {
        this.facebookdescription = facebookdescription;
    }

    public String getFacebookSubject() {
        return this.facebooksubject;
    }

    public void setFacebookSubject(String facebooksubject) {
        this.facebooksubject = facebooksubject;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BalloonText (");

        sb.append(id_);
        sb.append(", ").append(name);
        sb.append(", ").append(textbeforeform);
        sb.append(", ").append(textafterform);
        sb.append(", ").append(textbeforesharebuttons);
        sb.append(", ").append(textaftersharebuttons);
        sb.append(", ").append(accepttostext);
        sb.append(", ").append(emailtemplate);
        sb.append(", ").append(emailsubjecttemplate);
        sb.append(", ").append(twitterdescription);
        sb.append(", ").append(twittersubject);
        sb.append(", ").append(facebookdescription);
        sb.append(", ").append(facebooksubject);
        sb.append(", ").append(enabled);

        sb.append(")");
        return sb.toString();
    }
}
