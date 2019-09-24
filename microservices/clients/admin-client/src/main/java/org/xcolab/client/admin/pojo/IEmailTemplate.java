package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.admin.pojo.tables.pojos.EmailTemplate;

@JsonDeserialize(as = EmailTemplate.class)
public interface IEmailTemplate {

    String getName();

    void setName(String name);

    String getSubject();

    void setSubject(String subject);

    String getHeader();

    void setHeader(String header);

    String getFooter();

    void setFooter(String footer);
}
