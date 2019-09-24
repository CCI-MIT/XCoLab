package org.xcolab.view.files;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.pojo.FileEntryWrapper;
import org.xcolab.client.content.pojo.tables.pojos.FileEntry;
import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.view.util.entity.upload.FileUploadUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileUploadController {

    private static final int IMAGE_CROP_WIDTH_PIXELS = 300;
    private static final int IMAGE_CROP_HEIGHT_PIXELS = 300;

    private final String fileUploadPath = PlatformAttributeKey.FILES_UPLOAD_DIR.get();

    private final IFileClient fileClient;

    @Autowired
    public FileUploadController(IFileClient fileClient) {
        this.fileClient = fileClient;
    }

    @PostMapping("/image/upload")
    public ImageResponse singleFileUpload(HttpServletRequest request, HttpServletResponse response,
            @RequestParam MultipartFile file,
            @RequestParam(defaultValue = "false") boolean crop) {
        return uploadImage(file, request, crop);
    }

    private ImageResponse uploadImage(MultipartFile file, HttpServletRequest request,
            boolean crop) {
        try {
            String path;
            if (fileUploadPath != null) {
                path = fileUploadPath;
            } else {
                path = request.getSession().getServletContext().getRealPath("/");
            }

            byte[] bytes = file.getBytes();

            if (crop) {
                bytes = FileUploadUtil
                        .resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(bytes)),
                                IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS);
            }

            IFileEntry fileEntry = new FileEntry();
            fileEntry.setCreatedAt(new Timestamp(new Date().getTime()));
            String nameExt = file.getOriginalFilename();
            fileEntry.setFileExtension((FilenameUtils.getExtension(nameExt)).toLowerCase());
            fileEntry.setFileSize(bytes.length);
            fileEntry.setFileName(FilenameUtils.getName(nameExt));


            fileEntry = fileClient.createFileEntry(new FileEntryWrapper(fileEntry, bytes, path));

            final String imageIdString = String.valueOf(fileEntry.getId());
            return new ImageResponse(imageIdString, fileEntry.getLinkUrl(), true, "");
        } catch (IOException e) {
            return new ImageResponse(null, null, false, e.getMessage());
        }
    }

    @PostMapping("/image/uploadCkEditor")
    public void singleCKEditorUpload(@RequestParam("upload") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, name = "CKEditorFuncNum") String ckEditorFuncNum,
            @RequestParam(defaultValue = "false") boolean crop) {

        ImageResponse ir = uploadImage(file, request, crop);
        try {
            response.setContentType("text/html");
            response.getOutputStream()
                    .write(("<script>window.parent.CKEDITOR.tools.callFunction(" + ckEditorFuncNum
                            + ", \"" + ir.getImageUrl() + "\");</script>").getBytes());
        } catch (IOException ignored) {

        }
    }

    private static class ImageResponse {

        private final String imageId;
        private final String imageUrl;
        private final boolean success;
        private final String message;

        private ImageResponse(String imageId, String imageUrl, boolean success, String message) {
            this.imageId = imageId;
            this.imageUrl = imageUrl;
            this.success = success;
            this.message = message;
        }

        public String getImageId() {
            return imageId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
