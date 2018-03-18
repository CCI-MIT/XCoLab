package org.xcolab.client.files.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.files.FilesClient;
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

    private Long fileentryid;
    private Timestamp createdate;
    private String fileentryextension;
    private String fileentryname;
    private Integer fileentrysize;

    public FileEntry() {}

    public FileEntry(FileEntry value) {
        this.fileentryid = value.fileentryid;
        this.createdate = value.createdate;
        this.fileentryextension = value.fileentryextension;
        this.fileentryname = value.fileentryname;
        this.fileentrysize = value.fileentrysize;
    }

    public FileEntry(Long fileentryid, Timestamp createdate, String fileentryextension,
            String fileentryname, Integer fileentrysize) {
        this.fileentryid = fileentryid;
        this.createdate = createdate;
        this.fileentryextension = fileentryextension;
        this.fileentryname = fileentryname;
        this.fileentrysize = fileentrysize;
    }

    public Long getFileEntryId() {
        return this.fileentryid;
    }

    public void setFileEntryId(Long fileentryid) {
        this.fileentryid = fileentryid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public String getFileEntryExtension() {
        return this.fileentryextension;
    }

    public void setFileEntryExtension(String fileentryextension) {
        this.fileentryextension = fileentryextension;
    }

    public String getFileEntryName() {
        return this.fileentryname;
    }

    public void setFileEntryName(String fileentryname) {
        this.fileentryname = fileentryname;
    }

    public Integer getFileEntrySize() {
        return this.fileentrysize;
    }

    public void setFileEntrySize(Integer fileentrysize) {
        this.fileentrysize = fileentrysize;
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

        return "FileEntry (" + fileentryid +
                ", " + createdate +
                ", " + fileentryextension +
                ", " + fileentryname +
                ", " + fileentrysize +
                ")";
    }

    @JsonIgnore
    public String getLinkUrl() {
        return "/image?img_id=" + getFileEntryId();
    }
}
