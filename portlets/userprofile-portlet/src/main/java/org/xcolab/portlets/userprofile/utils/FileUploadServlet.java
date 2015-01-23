package org.xcolab.portlets.userprofile.utils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class FileUploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		InputStream is = null;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload fileUpload = new ServletFileUpload();
				FileItemIterator items = fileUpload.getItemIterator(request);

				while (items.hasNext()) {
					FileItemStream item = items.next();

					if (!item.isFormField()
							&& item.getContentType().contains("image")) {
						// currently we support only images
						long imageId = CounterLocalServiceUtil
								.increment(Image.class.getName());
						//
						//
						is = item.openStream();

						byte[] imgBArr = IOUtils.toByteArray(is);
						imgBArr = resizeAndCropImage(imgBArr);
						
						Image img = ImageLocalServiceUtil.createImage(imageId);
						img.setModifiedDate(new Date());
						ImageLocalServiceUtil.addImage(img);
						ImageLocalServiceUtil.updateImage(imageId, imgBArr);

						// return JSON with image id
						response.getWriter().append(
								"{\"imageId\": " + img.getPrimaryKey() + "}");
						response.getWriter().close();
					}
				}
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