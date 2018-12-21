package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
        return "/image?img_id=" + getId();
    }
}
