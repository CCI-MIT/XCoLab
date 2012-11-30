package org.climatecollaboratorium.plans.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
    public static void resizeAndCropImage(File file, int newWidth, int newHeight) throws IOException {
        int newW = newWidth;
        int newH = newHeight;
        
        BufferedImage img = ImageIO.read(file);
        // crop image
        
        int w = img.getWidth();  
        int h = img.getHeight();
        
        int cropSize = 0;
        int cropX = 0;
        int cropY = 0;
        
        
        if (h < w) {
            cropSize = h;
            cropX = (w - h) / 2;
        }
        else {
            cropSize = w;
            cropY = (h-w) / 2;
        }
        
        BufferedImage cropedImage = img.getSubimage(cropX, cropY, cropSize, cropSize);
        
        int imgType = BufferedImage.TYPE_3BYTE_BGR;
        if (img.getType() != BufferedImage.TYPE_CUSTOM) {
            imgType = img.getType();
        }
          
        BufferedImage dimg = new BufferedImage(newW, newH, imgType);  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(cropedImage, 0, 0, newW, newH, 0, 0, cropSize, cropSize, null);  
        g.dispose(); 
        
        String imageType = file.getName();
        imageType = imageType.substring(imageType.lastIndexOf(".") + 1);
        
        ImageIO.write(dimg, imageType, file);
        
    }

}
