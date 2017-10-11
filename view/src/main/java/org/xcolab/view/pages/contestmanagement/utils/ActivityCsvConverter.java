package org.xcolab.view.pages.contestmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.util.CsvConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ActivityCsvConverter extends CsvConverter {

    private static final Logger log = LoggerFactory.getLogger(ActivityCsvConverter.class);

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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

    public ActivityCsvConverter(ActivityEntryHelper activityEntryHelper) {
        super(COLUMN_NAMES);
        this.activityEntryHelper = activityEntryHelper;
    }

    public void addActivities(Collection<ActivityEntry> activityEntries) {
        activityEntries.forEach(this::addActivity);
    }

    public void addActivity(ActivityEntry activityEntry) {

        ActivityEntryType activityType = ActivityEntryType
                .getActivityEntryTypeByPrimaryType(activityEntry.getPrimaryType());
        if (activityType != null) {
            Member member = getMemberOrNull(activityEntry);

            List<String> row = new ArrayList<>();
            addValue(row, member != null ? member.getId_() : "Member not found");
            addValue(row, member != null ? member.getScreenName() : "Member not found");
            addValue(row, member != null ? member.getFirstName() : "Member not found");
            addValue(row, member != null ? member.getLastName() : "Member not found");
            addValue(row, activityType.name());
            addValue(row, DATE_FORMAT.format(activityEntry.getCreateDate()));
            addValue(row, activityEntryHelper.getActivityBody(activityEntry));

            addRow(row);
        } else {
            log.warn("Unknown ActivityEntryType {} found when generating report",
                    activityEntry.getPrimaryType());
        }
    }

    private Member getMemberOrNull(ActivityEntry activityEntry) {
        try {
            return MembersClient.getMember(activityEntry.getMemberId());
        } catch (MemberNotFoundException e) {
            log.warn("Member {} not found when generating report", activityEntry.getMemberId());
            return null;
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
