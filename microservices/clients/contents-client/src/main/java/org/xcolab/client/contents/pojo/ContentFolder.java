package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFolder implements Serializable {

    public static final long RESOURCE_FOLDER_ID = 4L;
    private static final long serialVersionUID = 111562325;

    private Long contentFolderId;
    private String contentFolderName;
    private String contentFolderDescription;
    private Long parentFolderId;

    public ContentFolder() {
    }

    public ContentFolder(Long contentFolderId, String contentFolderName,
            String contentFolderDescription, Long parentFolderId) {
        this.contentFolderId = contentFolderId;
        this.contentFolderName = contentFolderName;
        this.contentFolderDescription = contentFolderDescription;
        this.parentFolderId = parentFolderId;
    }

    public Long getContentFolderId() {
        return this.contentFolderId;
    }

    public void setContentFolderId(Long contentFolderId) {
        this.contentFolderId = contentFolderId;
    }

    public String getContentFolderName() {
        return this.contentFolderName;
    }

    public void setContentFolderName(String contentFolderName) {
        this.contentFolderName = contentFolderName;
    }

    public String getContentFolderDescription() {
        return this.contentFolderDescription;
    }

    public void setContentFolderDescription(String contentFolderDescription) {
        this.contentFolderDescription = contentFolderDescription;
    }

    public Long getParentFolderId() {
        return this.parentFolderId;
    }

    public void setParentFolderId(Long parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    @Override
    public String toString() {
        return "ContentFolder (" + contentFolderId +
                ", " + contentFolderName +
                ", " + contentFolderDescription +
                ", " + parentFolderId +
                ")";
    }
}
