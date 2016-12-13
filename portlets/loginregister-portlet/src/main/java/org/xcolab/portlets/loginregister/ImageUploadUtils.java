package org.xcolab.portlets.loginregister;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.entity.utils.upload.FileUploadUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import javax.imageio.ImageIO;

public class ImageUploadUtils {

    public static final int IMAGE_RESIZE_WIDTH = 300;
    public static final int IMAGE_RESIZE_HEIGHT = 300;
    private static final Log _log = LogFactoryUtil.getLog(ImageUploadUtils.class);

    public static long uploadImage(URL url, String path) {
        try {
            BufferedImage image = ImageIO.read(url);
            byte[] imgBArr = FileUploadUtil.resizeAndCropImage(image, IMAGE_RESIZE_WIDTH, IMAGE_RESIZE_HEIGHT);


            FileEntry file = new FileEntry();
            file.setCreateDate(new Timestamp(new Date().getTime()));
            file.setFileEntryExtension("png");
            file.setFileEntrySize(imgBArr.length);
            file.setFileEntryName(url.toString());

            file = FilesClient.createFileEntry(file, imgBArr, path);

            return file.getFileEntryId();
        } catch (IOException  e) {
            _log.error("Could not upload picture " + url.toString(), e);
        }

        return 0L;
    }

    public static void updateProfilePicture(String path, User user, String picURL) {
        try {
            // Link picture if it is not yet set
            if (user.getPortraitId() == 0) {
                user.setPortraitId(linkProfilePicture(path, picURL));
                UserLocalServiceUtil.updateUser(user);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static long linkProfilePicture(String path, String picUrl) {
        try {
            URL url = new URL(picUrl);
            return ImageUploadUtils.uploadImage(url, path);
        } catch (MalformedURLException e) {
            _log.warn("Could not upload  image with url " + picUrl, e);
        }

        return 0L;
    }
}
