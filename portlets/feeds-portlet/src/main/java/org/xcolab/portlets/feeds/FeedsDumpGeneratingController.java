package org.xcolab.portlets.feeds;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class FeedsDumpGeneratingController {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private byte[] generatedActivities = {};
	private int activitiesInGeneratedDump = 0;

	@RequestMapping(params = "action=generateDump")
	public void showFeed(ResourceRequest request, ResourceResponse response)
			throws IOException {
		generateActivitiesDump(request);
		response.setContentType("application/zip");
		response.getPortletOutputStream().write(generatedActivities);
		response.addProperty("Content-Disposition", "inline");
		response.addProperty("filename", "myfile.txt");
		
		// 
	}

	private synchronized void generateActivitiesDump(ResourceRequest request)
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
						if (body != null && body.trim().length() > 0) {
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
