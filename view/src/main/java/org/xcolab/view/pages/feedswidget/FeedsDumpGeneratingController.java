package org.xcolab.view.pages.feedswidget;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedsDumpGeneratingController {

	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private byte[] generatedActivities = {};
	private int activitiesInGeneratedDump;

	@GetMapping("/feedswidget/generateDump")
	public void showFeed(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		generateActivitiesDump(request);
		response.setContentType("application/zip");
		response.getOutputStream().write(generatedActivities);
		response.addHeader("Content-Disposition", "inline");
		response.addHeader("filename", "myfile.txt");
		
		// 
	}

	private synchronized void generateActivitiesDump(HttpServletRequest request)
			throws IOException {

			int currentCount = ActivitiesClientUtil.countActivities(null,null);
			if (currentCount > activitiesInGeneratedDump) {
				// regenerate

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(bos);

				zos.putNextEntry(new ZipEntry("activities.csv"));

				Writer fw = new OutputStreamWriter(zos);
				CSVWriter csvWriter = new CSVWriter(fw);

				for (ActivityEntry activity : ActivitiesClientUtil.getActivityEntries(0,Integer.MAX_VALUE,null,null)) {
					try {

						String body = activity.getActivityEntryBody();
						if (body != null && !body.trim().isEmpty()) {
						    //TODO: this doesn't work post-liferay
							body = body.replace("/web/guest",
									"http://climatecolab.org/web/guest");
						csvWriter.writeNext(new String[] {
								body,
								df.format(new Date(activity.getCreateDate().getTime())),
								activity.getActivityEntryId() + ""});

						}
					} catch (Throwable t) {
						// ignore
					}
				}
				fw.flush();
				zos.closeEntry();
				csvWriter.close();
				fw.close();

				generatedActivities = bos.toByteArray();
				activitiesInGeneratedDump = currentCount;
			}

	}

}
