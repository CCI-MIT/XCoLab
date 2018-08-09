package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFolder implements Serializable {

    private static final long serialVersionUID = 111562325;

    public static final TypeProvider<ContentFolder> TYPES =
            new TypeProvider<>(ContentFolder.class,
                    new ParameterizedTypeReference<List<ContentFolder>>() {
                    });
    public static final long RESOURCE_FOLDER_ID = 4L;

    private Long id;
    private String name;
    private String description;
    private Long parentFolderId;

    public ContentFolder() {
    }

    public ContentFolder(Long id, String name,
            String description, Long parentFolderId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentFolderId = parentFolderId;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentFolderId() {
        return this.parentFolderId;
    }

    public void setParentFolderId(Long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    @Override
    public String toString() {
        return "ContentFolder (" + id +
                ", " + name +
                ", " + description +
                ", " + parentFolderId +
                ")";
    }
}
