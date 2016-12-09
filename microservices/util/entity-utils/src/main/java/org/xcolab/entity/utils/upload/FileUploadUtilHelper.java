package org.xcolab.entity.utils.upload;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FileUploadUtilHelper {

    private final BufferedImage img;

    private boolean containsTransparency;

    public FileUploadUtilHelper(BufferedImage img) {
        this.img = img;
        this.containsTransparency = false;
    }

    public boolean getContainsTransparency() {
        return containsTransparency;
    }

    public BufferedImage resizeImage(int IMG_WIDTH, int IMG_HEIGHT) {
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

        int type = BufferedImage.TYPE_INT_RGB;
        if (containsAlphaPixels()) {
            type = BufferedImage.TYPE_INT_ARGB;
        }

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(croppedImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, 0, 0, cropSize, cropSize,
                null);
        g.dispose();

        return resizedImage;
    }

    private boolean containsAlphaPixels() {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y), true);
                if (c.getAlpha() < 255) {
                    containsTransparency = true;
                    return true;
                }
            }
        }
        return false;
    }

}