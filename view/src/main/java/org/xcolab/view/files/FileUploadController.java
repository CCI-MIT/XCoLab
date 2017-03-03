package org.xcolab.view.files;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.entity.utils.upload.FileUploadUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
public class FileUploadController {

    private static final int IMAGE_CROP_WIDTH_PIXELS = 300;
    private static final int IMAGE_CROP_HEIGHT_PIXELS = 300;

    private final String fileUploadPath;

    @Autowired
    public FileUploadController(Environment env) {
        fileUploadPath = env.getProperty("files.upload.dir");
    }

    @PostMapping("/images/upload")
    public ImageResponse singleFileUpload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Boolean resize) {

            if(request.getParameter("resize") ==null){
                resize = false;
            }
        return uploadImageResponse(file, request, resize);
    }

    private ImageResponse uploadImageResponse(MultipartFile file,
            HttpServletRequest request,  Boolean resize) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/");

            path = (fileUploadPath != null) ? (fileUploadPath) : (path);

            byte[] bytes = file.getBytes();

            if (resize!= null) {
                bytes = FileUploadUtil
                        .resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(bytes)),
                                IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS);
            }

            FileEntry fileEntry = new FileEntry();
            fileEntry.setCreateDate(new Timestamp(new Date().getTime()));
            String nameExt = file.getOriginalFilename();
            fileEntry.setFileEntryExtension((FilenameUtils.getExtension(nameExt)).toLowerCase());
            fileEntry.setFileEntrySize(bytes.length);
            fileEntry.setFileEntryName(FilenameUtils.getName(nameExt));

            fileEntry = FilesClient.createFileEntry(fileEntry, bytes, path);

            final String imageIdString = String.valueOf(fileEntry.getFileEntryId());
            return new ImageResponse(imageIdString, fileEntry.getLinkUrl(), true, "");
        } catch (IOException e) {
            return new ImageResponse(null, null, false, e.getMessage());
        }
    }

    @PostMapping("/images/uploadCkEditor")
    public void singleCKEditorUpload(@RequestParam("upload") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("CKEditorFuncNum") String ckEditorFuncNum,
            @RequestParam(required = false) Boolean resize) {

        if(request.getParameter("resize") ==null){
            resize = false;
        }
        ImageResponse ir = uploadImageResponse(file, request, resize);
        try {

            response.getOutputStream()
                    .write(("<script>window.parent.CKEDITOR.tools.callFunction(" + ckEditorFuncNum + ", \"" + ir.getImageUrl() + "\");</script>").getBytes());;
        }catch (IOException ignored){

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
