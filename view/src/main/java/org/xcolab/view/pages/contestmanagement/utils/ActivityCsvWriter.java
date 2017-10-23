package org.xcolab.view.pages.contestmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.util.CsvResponseWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ActivityCsvWriter extends CsvResponseWriter {

    private static final Logger _log = LoggerFactory.getLogger(ActivityCsvWriter.class);

    private static final String MEMBER_NOT_FOUND_MESSAGE = "Member not found";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static final String FILE_NAME = "activityReport";

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "User Id",
            "screenName",
            "firstName",
            "lastName",
            "activityType",
            "activityCreateDate",
            "activityBody"
            );

    private final ActivityEntryHelper activityEntryHelper;

    public ActivityCsvWriter(HttpServletResponse response,
            ActivityEntryHelper activityEntryHelper) throws IOException {
        super(FILE_NAME, COLUMN_NAMES, response);
        this.activityEntryHelper = activityEntryHelper;
    }

    public void writeActivities(Collection<ActivityEntry> activityEntries) {
        activityEntries.forEach(this::writeActivity);
    }

    public void writeActivity(ActivityEntry activityEntry) {

        ActivityEntryType activityType = ActivityEntryType
                .getActivityEntryTypeByPrimaryType(activityEntry.getPrimaryType());
        if (activityType != null) {
            Member member = getMemberOrNull(activityEntry);

            List<String> row = new ArrayList<>();
            addValue(row, member != null ? member.getId_() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getScreenName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getFirstName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getLastName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, activityType.name());
            addValue(row, DATE_FORMAT.format(activityEntry.getCreateDate()));
            addValue(row, activityEntryHelper.getActivityBody(activityEntry));

            writeRow(row);
        } else {
            _log.warn("Unknown ActivityEntryType {} found when generating report",
                    activityEntry.getPrimaryType());
        }
    }

    private Member getMemberOrNull(ActivityEntry activityEntry) {
        try {
            return MembersClient.getMember(activityEntry.getMemberId());
        } catch (MemberNotFoundException e) {
            _log.warn("Member {} not found when generating report", activityEntry.getMemberId());
            return null;
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}