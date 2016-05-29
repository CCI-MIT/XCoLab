package org.xcolab.portlets.loginregister;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.FileUploadUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ImageUploadUtils {

    public static final int IMAGE_RESIZE_WIDTH = 300;
    public static final int IMAGE_RESIZE_HEIGHT = 300;
    private static final Log _log = LogFactoryUtil.getLog(ImageUploadUtils.class);

    public static long uploadImage(URL url) throws SystemException {
        try {
            BufferedImage image = ImageIO.read(url);
            byte[] imgBArr = FileUploadUtil.resizeAndCropImage(image, IMAGE_RESIZE_WIDTH, IMAGE_RESIZE_HEIGHT);

            long imageId = CounterLocalServiceUtil.increment(Image.class.getName());
            Image img = ImageLocalServiceUtil.createImage(imageId);
            img.setModifiedDate(new Date());
            ImageLocalServiceUtil.addImage(img);
            ImageLocalServiceUtil.updateImage(imageId, imgBArr);

            return imageId;
        } catch (IOException | PortalException e) {
            _log.error("Could not upload picture " + url.toString(), e);
        }

        return 0L;
    }

    public static void updateProfilePicture(User user, String picURL) throws SystemException {
        // Link picture if it is not yet set
        if (user.getPortraitId() == 0) {
            user.setPortraitId(linkProfilePicture(picURL));
            UserLocalServiceUtil.updateUser(user);
        }
    }

    public static long linkProfilePicture(String picUrl) throws SystemException {
        try {
            URL url = new URL(picUrl);
            return ImageUploadUtils.uploadImage(url);
        } catch (MalformedURLException e) {
            _log.warn("Could not upload  image with url " + picUrl, e);
        }

        return 0L;
    }
}
