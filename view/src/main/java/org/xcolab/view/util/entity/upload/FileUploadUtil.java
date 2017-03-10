package org.xcolab.view.util.entity.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUploadUtil {

    public static byte[] resizeAndCropImage(BufferedImage img,
            int imageCropWidthPixels, int imageCropHeightPixels)
            throws IOException {
        FileUploadUtilHelper helper = new FileUploadUtilHelper(img);
        BufferedImage resImg = helper.resizeImage(imageCropWidthPixels, imageCropHeightPixels);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (helper.getContainsTransparency()) {
            ImageIO.write(resImg, "png", bos);
        } else {
            ImageIO.write(resImg, "jpg", bos);
        }

        return bos.toByteArray();
    }
}
