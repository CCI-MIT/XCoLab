package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class FileEntryWrapper {

    private IFileEntry fileEntry;
    private byte[] imgArr;
    private String path;

    public FileEntryWrapper() {
    }

    public FileEntryWrapper(IFileEntry fileEntry, byte[] imgArr, String path) {
        this.fileEntry = fileEntry;
        this.imgArr = imgArr;
        this.path = path;
    }

    public IFileEntry getFileEntry() {
        return fileEntry;
    }

    public void setFileEntry(IFileEntry fileEntry) {
        this.fileEntry = fileEntry;
    }

    public byte[] getImgArr() {
        return imgArr;
    }

    public void setImgArr(byte[] imgArr) {
        this.imgArr = imgArr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
