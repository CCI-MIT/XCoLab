package org.xcolab.view.pages.contestmanagement.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.util.CsvConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCsvConverter extends CsvConverter {

    private static final Logger log = LoggerFactory.getLogger(ActivityCsvConverter.class);

    private static final int NUM_COLUMNS = 6;
    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Member ID",
            "screenName",
            "firstName",
            "lastName",
            "emailAddress",
            "activityType",
            "activityCreateDate",
            "activityBody"
            );

    public ActivityCsvConverter() {
        super(NUM_COLUMNS);
        addRow(COLUMN_NAMES);
    }

    public void addActivity(ActivityEntry activityEntry){

        final String colabUrl = ConfigurationAttributeKey.COLAB_URL.get();




            ActivityEntryType cet = ActivityEntryType.getActivityEntryTypeByPrimaryType(activityEntry.getPrimaryType());
            Member member = getMemberOrNull(activityEntry);



            List<String> row = new ArrayList<>();
            addValue(row, member != null ? member.getId_() : "Member not found");
            addValue(row, member != null ? member.getScreenName() : "Member not found");
            addValue(row, member != null ? member.getFirstName() : "Member not found");
            addValue(row, member != null ? member.getLastName() : "Member not found");
            addValue(row, member != null ? member.getEmailAddress() : "Member not found");
            addValue(row, cet.name());
            addValue(row, activityEntry.getCreateDate());
            addValue(row, activityEntry.getActivityEntryBody());

            addRow(row);

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
