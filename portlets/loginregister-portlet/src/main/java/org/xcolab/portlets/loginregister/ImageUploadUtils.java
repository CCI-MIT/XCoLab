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

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Klemens Mang on 20.02.14.
 */
public class ImageUploadUtils {

    private static final Log _log = LogFactoryUtil.getLog(ImageUploadUtils.class);

    public static final int IMAGE_RESIZE_WIDTH = 300;
    public static final int IMAGE_RESIZE_HEIGHT = 300;

    public static long uploadImage(URL url) throws SystemException {
        try {
            BufferedImage image = ImageIO.read(url);
            byte[] imgBArr = resizeAndCropImage(image);

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

    private static byte[] resizeAndCropImage(BufferedImage img) throws IOException {
        int w = img.getWidth();
        int h = img.getHeight();

        int cropSize;
        int cropX = 0;
        int cropY = 0;

        if (h < w) {
            cropSize = h;
            cropX = (w - h) / 2;
        } else {
            cropSize = w;
            cropY = (h - w) / 2;
        }

        BufferedImage croppedImage = img.getSubimage(cropX, cropY, cropSize,
                cropSize);

        int imgType = BufferedImage.TYPE_3BYTE_BGR;
        if (img.getType() != BufferedImage.TYPE_CUSTOM) {
            imgType = img.getType();
        }

        BufferedImage dimg = new BufferedImage(IMAGE_RESIZE_WIDTH, IMAGE_RESIZE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        dimg.createGraphics().drawImage(croppedImage, 0, 0, IMAGE_RESIZE_WIDTH, IMAGE_RESIZE_HEIGHT, 0, 0, cropSize, cropSize,
                null);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(dimg, "jpg", bos);
        return bos.toByteArray();
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
