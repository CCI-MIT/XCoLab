package org.xcolab.client.admin.pojo;

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
