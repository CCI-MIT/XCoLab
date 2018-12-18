package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.content.pojo.tables.pojos.ContentFolder;

@JsonDeserialize(as = ContentFolder.class)
public interface IContentFolder {

    long RESOURCE_FOLDER_ID = 4L;

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Long getParentFolderId();

    void setParentFolderId(Long parentFolderId);
}
