package org.xcolab.view.pages.feedback;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class ContactBean  implements Serializable {
    private static final long serialVersionUID = -6456025417093073280L;

    private String name;

    @Email(message = "Please enter a valid email address.")
    private String email;
    
    @NotBlank(message = "Please enter a message.")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message; 
    }
}
