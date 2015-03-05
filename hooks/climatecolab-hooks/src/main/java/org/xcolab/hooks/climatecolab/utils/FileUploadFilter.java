package org.xcolab.hooks.climatecolab.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;

public class FileUploadFilter implements Filter {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		InputStream is = null;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload fileUpload = new ServletFileUpload();
				FileItemIterator items = fileUpload.getItemIterator(request);

				Boolean keepFormat = false;
				while (items.hasNext()) {
					FileItemStream item = items.next();
					if(item.isFormField() && item.getFieldName().equals("keepFormat")){
						keepFormat = true;
						continue;
					}
					if (!item.isFormField()
							&& item.getContentType().contains("image")) {
						// currently we support only images
						long imageId = CounterLocalServiceUtil
								.increment(Image.class.getName());
						//
						//
						is = item.openStream();

						byte[] imgBArr = IOUtils.toByteArray(is);
						if(!keepFormat){
							imgBArr = resizeAndCropImage(imgBArr);
						}
						
						//Image img = ImageLocalServiceUtil..getImage(imgBArr);
						Image img = ImageLocalServiceUtil.createImage(imageId);
						//ImageLocalServiceUtil.u
						//img.setImageId(imageId);
						img.setModifiedDate(new Date());
						ImageLocalServiceUtil.addImage(img);
						ImageLocalServiceUtil.updateImage(imageId, imgBArr);

						// return JSON with image id
						response.getWriter().append(
								"{\"success\": \"true\", \"imageId\": " + img.getPrimaryKey() + "}");
						response.getWriter().close();
					}
					else {
                        response.getWriter().append(
                                "{\"error\": \"true\", \"message\":\"Invalid file format, only images are accepted\"}");
                        response.getWriter().close();
					}
				}
			}
			else {
                response.getWriter().append(
                        "{\"error\": \"true\", \"message\":\"Unknown request\"}");
                response.getWriter().close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// it's bad but ignore that
				}
			}
		}
	}

	private byte[] resizeAndCropImage(byte[] imgBArr) throws IOException {
		int newW = 150;
		int newH = 150;

		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBArr));
		// crop image

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

		BufferedImage cropedImage = img.getSubimage(cropX, cropY, cropSize, cropSize);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dimg.createGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0,0,newW, newH);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setComposite(AlphaComposite.Src);
		g.drawImage(cropedImage, 0, 0, newW, newH, 0, 0, cropSize, cropSize, null);
		g.dispose();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(dimg, "png", bos);
		return bos.toByteArray();
	}

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doPost((HttpServletRequest) request, (HttpServletResponse) response);
        
    }

    public void destroy() {
        // TODO Auto-generated method stub
        
    }
}