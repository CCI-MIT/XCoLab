package org.xcolab.view.pages.contestmanagement.beans;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class MassMessageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    public MassMessageBean() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}