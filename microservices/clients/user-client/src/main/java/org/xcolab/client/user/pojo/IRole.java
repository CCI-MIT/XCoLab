package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

public interface IRole {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
