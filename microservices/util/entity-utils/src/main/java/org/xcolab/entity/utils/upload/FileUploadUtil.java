package org.xcolab.entity.utils.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUploadUtil {

    public static byte[] resizeAndCropImage(BufferedImage img, int IMAGE_CROP_WIDTH_PIXELS, int IMAGE_CROP_HEIGHT_PIXELS) throws IOException {
        FileUploadUtilHelper helper = new FileUploadUtilHelper(img);
        BufferedImage resImg = helper.resizeImage(IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (helper.getContainsTransparency()) {
            ImageIO.write(resImg, "png", bos);
        } else {
            ImageIO.write(resImg, "jpg", bos);
        }

        return bos.toByteArray();
    }
}
