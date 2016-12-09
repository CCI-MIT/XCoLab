package org.xcolab.portlets.loginregister;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.entity.utils.upload.FileUploadUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

						is = item.openStream();

						byte[] imgBArr = IOUtils.toByteArray(is);
						imgBArr = FileUploadUtil.resizeAndCropImage(ImageIO.read(new ByteArrayInputStream(imgBArr)),
								150, 150);

						String path = request.getSession().getServletContext().getRealPath("/");

						FileEntry file = new FileEntry();
						file.setCreateDate(new Timestamp(new Date().getTime()));
						String nameExt = item.getName();
						file.setFileEntryExtension((FilenameUtils.getExtension(nameExt)).toLowerCase());
						file.setFileEntrySize(imgBArr.length);
						file.setFileEntryName(FilenameUtils.getName(nameExt));

						file = FilesClient.createFileEntry(file, imgBArr, path);

						// return JSON with image id
						response.getWriter().append(
								"{\"imageId\": " + file.getFileEntryId() + "}");
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