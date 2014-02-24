package org.xcolab.portlets.loginregister;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * Created by Mente on 20.02.14.
 */
public class ImageUploadUtils {

    public static long uploadImage(URL imageUrl) {
        try {
            return uploadImage(imageUrl.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static long uploadImage(InputStream is) {
        try {
            byte[] imgBArr;
            imgBArr = IOUtils.toByteArray(is);
            imgBArr = resizeAndCropImage(imgBArr);

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

    private static byte[] resizeAndCropImage(byte[] imgBArr) throws IOException {
        int newW = 150;
        int newH = 150;

        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBArr));
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
}
