package org.xcolab.portlets.userprofile.utils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Klemens Mang on 20.02.14.
 */
public class ImageUploadUtils {

    public static long uploadImage(URL url) {
        try {
            byte[] imgBArr;
            BufferedImage image = ImageIO.read(url);
            imgBArr = resizeAndCropImage(image);

            long imageId = CounterLocalServiceUtil
                    .increment(Image.class.getName());
            Image img = ImageLocalServiceUtil.createImage(imageId);
            img.setModifiedDate(new Date());
            ImageLocalServiceUtil.addImage(img);
            ImageLocalServiceUtil.updateImage(imageId, imgBArr);

            return imageId;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return 0L;

    }

    private static byte[] resizeAndCropImage(BufferedImage img) throws IOException {
        int newW = 150;
        int newH = 150;

        // crop image

        int w = img.getWidth();
        int h = img.getHeight();

        int cropSize = 0;
        int cropX = 0;
        int cropY = 0;

        if (h < w) {
            cropSize = h;
            cropX = (w - h) / 2;
        } else {
            cropSize = w;
            cropY = (h - w) / 2;
        }

        BufferedImage cropedImage = img.getSubimage(cropX, cropY, cropSize,
                cropSize);

        int imgType = BufferedImage.TYPE_3BYTE_BGR;
        if (img.getType() != BufferedImage.TYPE_CUSTOM) {
            imgType = img.getType();
        }

        BufferedImage dimg = new BufferedImage(newW, newH, imgType);
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(cropedImage, 0, 0, newW, newH, 0, 0, cropSize, cropSize,
                null);
        g.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(dimg, "jpg", bos);
        return bos.toByteArray();
    }

    public static boolean getPictureDifference(URL url1, URL url2) {
        byte[] hash1 = getPictureHash(url1);
        byte[] hash2 = getPictureHash(url2);

        // return false when the new picture cannot be retrieved
        if (hash1 != null && hash2 != null) {
            return !Arrays.equals(hash1, hash2);

        } else {
            return false;
        }
    }

    private static byte[] getPictureHash(URL url) {
        InputStream is = null;
        try {
            is = url.openStream();
            byte[] bytes = IOUtils.toByteArray(is);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            return md.digest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateProfilePicture(User user, String picURL) {
        // Link picture if it is not yet set
        if (user.getPortraitId() == 0) {
            user.setPortraitId(linkProfilePicture(picURL));
            try {
                UserLocalServiceUtil.updateUser(user);
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }
    }

    public static long linkProfilePicture(String picUrl) {
        try {
            URL url = new URL(picUrl);
            return ImageUploadUtils.uploadImage(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return 0L;
    }
}
