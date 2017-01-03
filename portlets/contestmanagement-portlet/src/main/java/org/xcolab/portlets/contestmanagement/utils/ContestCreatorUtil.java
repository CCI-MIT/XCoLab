package org.xcolab.portlets.contestmanagement.utils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.group.Group_;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleChangeHelper;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.util.Random;
import java.util.UUID;

public final class ContestCreatorUtil {

    private static final Logger log = LoggerFactory.getLogger(ContestCreatorUtil.class);

    private static final String SEED_CONTEST_SCHEDULE_NAME = "Seed Contest Schedule";
    private static final String DEFAULT_SCHEDULE_NAME = "[DEFAULT] Contest Schedule";

    private static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    private final static long DEFAULT_CONTEST_TEMPLATE_ID = 102L;
    private static final Random rand = new Random();

    private ContestCreatorUtil() { }

    public static Contest createNewContest(String contestShortName)  {

        Long sharedId = SharedColabClient.retrieveContestSharedId(contestShortName,ConfigurationAttributeKey.COLAB_NAME.get());
        Contest contest = ContestClientUtil.createContest(sharedId,10144L, contestShortName);
        contest.setContestYear((long) DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShow_in_tile_view(true);
        contest.setShow_in_list_view(true);
        contest.setShow_in_outline_view(true);
        contest.setIsSharedContest(false);
        contest.setSharedOrigin(ConfigurationAttributeKey.COLAB_NAME.get());
        contest.setPlanTemplateId(DEFAULT_CONTEST_TEMPLATE_ID);
        final Long contestScheduleId = getOrCreateDefaultContestSchedule().getId_();
        contest.setContestScheduleId(contestScheduleId);
        contest.setContestTypeId(
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        ContestClientUtil.updateContest(contest);
        ContestScheduleChangeHelper changeHelper = new ContestScheduleChangeHelper(contest, contestScheduleId);
        changeHelper.changeScheduleForBlankContest();

        setGroupForContest(contest);


        return contest;
    }

    private static ContestSchedule getOrCreateDefaultContestSchedule() {
        final long defaultContestScheduleId = ConfigurationAttributeKey
                .DEFAULT_CONTEST_SCHEDULE_ID.get();
        try {
            if (defaultContestScheduleId > 0) {
                return ContestClientUtil.getContestSchedule(defaultContestScheduleId);
            }
            final ContestSchedule newDefaultSchedule = ContestScheduleLifecycleUtil
                    .createProposalCreationOnlySchedule(DEFAULT_SCHEDULE_NAME);

            ConfigurationAttribute defaultScheduleAttribute = new ConfigurationAttribute();
            defaultScheduleAttribute
                    .setName(ConfigurationAttributeKey.DEFAULT_CONTEST_SCHEDULE_ID.name());
            defaultScheduleAttribute.setNumericValue(newDefaultSchedule.getId_());
            AdminClient.createConfigurationAttribute(defaultScheduleAttribute);

            log.warn("No DEFAULT_CONTEST_SCHEDULE_ID found; created new Schedule with id {} "
                    + "and corresponding ConfigurationAttribute.", newDefaultSchedule.getId_());

            return newDefaultSchedule;
        } catch (ContestScheduleNotFoundException e) {
            //fail early if it doesn't exist
            throw ReferenceResolutionException.toObject(ContestSchedule.class, defaultContestScheduleId)
                    .fromObject(ConfigurationAttribute.class, "DEFAULT_CONTEST_SCHEDULE_ID");
        }
    }

    private static void setGroupForContest(Contest c) {

        String groupName = c.getContestName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group_ group = new Group_();
        group.setCompanyId(10112l);
        group.setCreatorUserId(10144l);
        group.setClassNameId(10009l);
        group.setParentGroupId(0l);
        group.setLiveGroupId(0l);
        group.setName(groupName);
        group.setDescription(String.format(DEFAULT_GROUP_DESCRIPTION, groupName));
        group.setType_(2);
        group.setFriendlyURL("/contest-" + c.getContestPK());
        group.setActive_(true);
        group.setSite(true);
        group.setUuid_(UUID.randomUUID().toString());
        group.setManualMembership(true);
        group.setMembershipRestriction(0);
        group.setRemoteStagingGroupCount(0);
        group = ProposalClientUtil.createGroup(group);
        group.setTreePath("/" + group.getGroupId() + "/");
        group.setClassPK(group.getGroupId());
        ProposalClientUtil.updateGroup(group);
        c.setGroupId(group.getGroupId());
        ContestClientUtil.updateContest(c);
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() {
        //TODO: create count endpoint instead of getting all schedules
        if (ContestClientUtil.getAllContestSchedules().isEmpty()) {
            ContestScheduleLifecycleUtil.createProposalCreationOnlySchedule(SEED_CONTEST_SCHEDULE_NAME);
        }
    }
}
