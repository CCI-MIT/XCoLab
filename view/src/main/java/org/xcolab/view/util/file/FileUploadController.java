package org.xcolab.view.util.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
public class FileUploadController {

    @Autowired
    private Environment env;

    @PostMapping("/images/upload")
    public void singleFileUpload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {
        try {

            String fileUploadPath = env.getProperty("files.upload.dir");

            String path = request.getSession().getServletContext().getRealPath("/");

            path = (fileUploadPath!=null)?(fileUploadPath):(path);
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            if(request.getParameter("resize")!=null){
                bytes = FileUploadUtil.resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(bytes)),
                        150, 150);
            }

            FileEntry fileEntry = new FileEntry();
            fileEntry.setCreateDate(new Timestamp(new Date().getTime()));
            String nameExt = file.getOriginalFilename();
            fileEntry.setFileEntryExtension((FilenameUtils.getExtension(nameExt)).toLowerCase());
            fileEntry.setFileEntrySize(bytes.length);
            fileEntry.setFileEntryName(FilenameUtils.getName(nameExt));

            fileEntry = FilesClient.createFileEntry(fileEntry, bytes, path);
            // return JSON with image id
            response.getWriter()
                    .append("{\"imageId\": ")
                    .append(String.valueOf(fileEntry.getFileEntryId()))
                    .append("}");
            response.getWriter().close();


        } catch (IOException e) {

        }
    }
}
