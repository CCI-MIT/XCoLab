package org.xcolab.view.pages.contestmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.CsvResponseWriter;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.view.activityentry.ActivityEntryHelper;

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
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String FILE_NAME = "activityReport";

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "User Id",
            "screenName",
            "firstName",
            "lastName",
            "activityCategory",
            "activityType",
            "activityCreatedAt",
            "activityBody"
            );

    private final ActivityEntryHelper activityEntryHelper;

    public ActivityCsvWriter(HttpServletResponse response,
            ActivityEntryHelper activityEntryHelper) throws IOException {
        super(FILE_NAME, COLUMN_NAMES, response);
        this.activityEntryHelper = activityEntryHelper;
    }

    public void writeActivities(Collection<IActivityEntry> activityEntries) {
        activityEntries.forEach(this::writeActivity);
    }

    public void writeActivity(IActivityEntry activityEntry) {

        final ActivityType activityType = activityEntry.getActivityTypeEnum();
        if (activityType != null) {
            UserWrapper member = getMemberOrNull(activityEntry);

            List<String> row = new ArrayList<>();
            addValue(row, member != null ? member.getId() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getScreenName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getFirstName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, member != null ? member.getLastName() : MEMBER_NOT_FOUND_MESSAGE);
            addValue(row, activityType.getCategory().name());
            addValue(row, activityType.name());
            addValue(row, DATE_FORMAT.format(activityEntry.getCreatedAt()));
            addValue(row, activityEntryHelper.getActivityBody(activityEntry));

            writeRow(row);
        } else {
            _log.warn("Unknown ActivityCategory {} found when generating report",
                    activityEntry.getActivityCategory());
        }
    }

    private UserWrapper getMemberOrNull(IActivityEntry activityEntry) {
        try {
            return StaticUserContext.getUserClient().getMember(activityEntry.getUserId());
        } catch (MemberNotFoundException e) {
            _log.warn("Member {} not found when generating report", activityEntry.getUserId());
            return null;
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
