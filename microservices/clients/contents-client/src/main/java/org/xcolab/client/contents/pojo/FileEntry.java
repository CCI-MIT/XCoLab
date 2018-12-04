package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contents.FilesClient;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileEntry implements Serializable {

    private static final long serialVersionUID = -863924807;

    public static final TypeProvider<FileEntry> TYPES =
            new TypeProvider<>(FileEntry.class,
                    new ParameterizedTypeReference<List<FileEntry>>() {
                    });

    private Long id;
    private Timestamp createdAt;
    private String fileExtension;
    private String fileName;
    private Integer fileSize;

    public FileEntry() {}

    public FileEntry(FileEntry value) {
        this.id = value.id;
        this.createdAt = value.createdAt;
        this.fileExtension = value.fileExtension;
        this.fileName = value.fileName;
        this.fileSize = value.fileSize;
    }

    public FileEntry(Long id, Timestamp createdAt, String fileExtension,
            String fileName, Integer fileSize) {
        this.id = id;
        this.createdAt = createdAt;
        this.fileExtension = fileExtension;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @JsonIgnore
    public File getImageFile(String basePath) {
        String filePath = FilesClient.getFilePathFromFinalDestination(this, basePath);
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            return file;
        }
        return null;
    }

    @Override
    public String toString() {

        return "FileEntry (" + id +
                ", " + createdAt +
                ", " + fileExtension +
                ", " + fileName +
                ", " + fileSize +
                ")";
    }

    @JsonIgnore
    public String getLinkUrl() {
        return "/image?img_id=" + getId();
    }
}
