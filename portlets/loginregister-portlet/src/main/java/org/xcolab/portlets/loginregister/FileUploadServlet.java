package org.xcolab.portlets.loginregister;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ImageLocalServiceUtil;
import org.xcolab.utils.FileUploadUtil;

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
						imgBArr = FileUploadUtil.resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(imgBArr)),
								150, 150);
						
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
}