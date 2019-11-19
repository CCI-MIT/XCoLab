package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Arrays;

import org.xcolab.client.content.pojo.tables.pojos.FileEntry;

import java.sql.Timestamp;

@JsonDeserialize(as = FileEntry.class)
public interface IFileEntry {

    Long getId();

    void setId(Long id);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getFileExtension();

    void setFileExtension(String fileExtension);

    String getFileName();

    void setFileName(String fileName);

    Integer getFileSize();

    void setFileSize(Integer fileSize);

    @JsonIgnore
    default String getLinkUrl() {
        String[] array = {"png", "jpg", "jpeg", "gif", "raw"};
        if (Arrays.asList(array).contains(this.getFileExtension())) {
            return getImageUrl();
        } else {
            return getFileUrl();
        }
    }

    @JsonIgnore
    default String getImageUrl() {
        return "/image?img_id=" + getId();
    }

    @JsonIgnore
    default String getFileUrl() {
        return "/file?file_id=" + getId();
    }
}
