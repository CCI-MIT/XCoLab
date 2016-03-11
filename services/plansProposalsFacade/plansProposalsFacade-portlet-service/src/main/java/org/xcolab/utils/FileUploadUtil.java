package org.xcolab.utils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by steve on 09/03/16.
 */
public class FileUploadUtil {

    public static byte[] resizeAndCropImage(BufferedImage img, int IMAGE_CROP_WIDTH_PIXELS, int IMAGE_CROP_HEIGHT_PIXELS) throws IOException {
        FileUploadUtilHelper helper = new FileUploadUtilHelper(img);
        BufferedImage resImg = helper.resizeImage(IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (helper.getContainsTransparancy()) {
            ImageIO.write(resImg, "png", bos);
        } else {
            ImageIO.write(resImg, "jpg", bos);
        }

        return bos.toByteArray();
    }

}
