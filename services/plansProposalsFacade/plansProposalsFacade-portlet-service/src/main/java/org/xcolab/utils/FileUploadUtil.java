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
        boolean containsTransparancy = containsAlphaPixels(img);
        BufferedImage resImg = resizeImage(img, IMAGE_CROP_WIDTH_PIXELS, IMAGE_CROP_HEIGHT_PIXELS, containsTransparancy);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (containsTransparancy) {
            ImageIO.write(resImg, "png", bos);
        } else {
            ImageIO.write(resImg, "jpg", bos);
        }

        return bos.toByteArray();
    }

    private static boolean containsAlphaPixels(BufferedImage img) {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y), true);
                if (c.getAlpha() < 255) {
                    return true;
                }
            }
        }
        return false;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int IMG_WIDTH, int IMG_HEIGHT, boolean containsTransparancy) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();

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

        BufferedImage croppedImage = originalImage.getSubimage(cropX, cropY, cropSize,
                cropSize);

        int type = BufferedImage.TYPE_INT_RGB;
        if (containsTransparancy) {
            type = BufferedImage.TYPE_INT_ARGB;
        }

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(croppedImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, 0, 0, cropSize, cropSize,
                null);
        g.dispose();

        return resizedImage;
    }

}
