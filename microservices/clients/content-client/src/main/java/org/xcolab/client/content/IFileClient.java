package org.xcolab.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.content.pojo.FileEntryWrapper;
import org.xcolab.client.content.pojo.IFileEntry;

import java.io.File;
import java.util.Optional;

@FeignClient("xcolab-content-service")
public interface IFileClient {

    @PostMapping("/fileEntries")
    IFileEntry createFileEntry(@RequestBody FileEntryWrapper fileEntryWrapper);

    @GetMapping("/imageFile")
    File getImageFile(@RequestParam("fileEntryId") Long fileEntryId,
            @RequestParam("filePath") String filePath,
            @RequestParam("fileExtension") String fileExtension);

    @GetMapping("/fileEntries/{fileEntryId}")
    Optional<IFileEntry> getFileEntry(@PathVariable("fileEntryId") Long fileEntryId);
}
