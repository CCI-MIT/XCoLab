package org.xcolab.portlets.contestmanagement.utils;


import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestPhaseTypeValue;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.sql.Timestamp;
import java.util.Random;

public final class ContestCreatorUtil {
    private static final Log _log = LogFactoryUtil.getLog(ContestCreatorUtil.class);

    public static final String SEED_CONTEST_SCHEDULE_NAME = "Seed Contest Schedule";
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    //TODO: remove hard coded defaults
    public final static long DEFAULT_CONTEST_SCHEDULE_ID = 601L;
    public final static long DEFAULT_CONTEST_TEMPLATE_ID = 102L;
    private static final Random rand = new Random();

    private ContestCreatorUtil() { }

    public static Contest createNewContest(String contestShortName)  {

            Contest contest = ContestClient.createContest(10144L, contestShortName);
            contest.setContestYear(new Long(DateTime.now().getYear()));
            contest.setContestPrivate(true);
            contest.setShow_in_tile_view(true);
            contest.setShow_in_list_view(true);
            contest.setShow_in_outline_view(true);
            contest.setPlanTemplateId(DEFAULT_CONTEST_TEMPLATE_ID);
            contest.setContestScheduleId(DEFAULT_CONTEST_SCHEDULE_ID);
            contest.setContestTypeId(
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
            ContestClient.updateContest(contest);
            ContestScheduleLifecycleUtil
                    .createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(contest,
                            DEFAULT_CONTEST_SCHEDULE_ID);
            try{
                setGroupAndDiscussionForContest(contest);
            }catch (PortalException| SystemException ignored){

            }

            return contest;

    }

    private static void setGroupAndDiscussionForContest(Contest c) throws PortalException, SystemException {

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(c.getAuthorId());

        String groupName = c.getContestName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = GroupLocalServiceUtil.addGroup(c.getAuthorId(), null, c.getContestPK(), "CONTEST:  " + c.getContestPK(),
                String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);

        CommentThread thread = new CommentThread();
        thread.setTitle(c.getContestName() + " discussion");
        thread.setAuthorId(c.getAuthorId());
        thread.setIsQuiet(false);
        long discussionId = CommentClient.createThread(thread).getThreadId();
        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(discussionId);
        ContestClient.updateContest(c);
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() {
        try {
            if (ContestScheduleLocalServiceUtil.getContestSchedulesCount() == 0) {
                ContestSchedule contestSchedule = new ContestSchedule();
                contestSchedule.setName(SEED_CONTEST_SCHEDULE_NAME);
                contestSchedule = ContestClient.createContestSchedule(contestSchedule);

                createContestPhaseForSeedSchedule(
                        new ContestPhaseBean(ContestPhaseTypeValue.PROPOSAL_CREATION,
                                DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss a")
                                        .parseDateTime("1/1/2016 12:00:00 AM").toDate(),
                                null, ContestPhasePromoteType.DEFAULT.getValue(), false),
                        contestSchedule.getId_());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void createContestPhaseForSeedSchedule(ContestPhaseBean contestPhaseBean,
            long contestScheduleId) {

            ContestPhase contestPhase = new ContestPhase();

            contestPhase.setContestPK(0L);
            contestPhase.setContestScheduleId(contestScheduleId);
            contestPhase.setContestPhaseType(contestPhaseBean.getContestPhaseType());
            contestPhase.setPhaseStartDate(new Timestamp(contestPhaseBean.getPhaseStartDate().getTime()));

            if (contestPhaseBean.getPhaseEndDate() != null) {
                contestPhase.setPhaseEndDate(new Timestamp(contestPhaseBean.getPhaseEndDate().getTime()));
            }

            contestPhase.setContestPhaseAutopromote(contestPhaseBean.getContestPhaseAutopromote());
            contestPhase.setFellowScreeningActive(contestPhaseBean.getFellowScreeningActive());
            contestPhase = ContestClient.createContestPhase(contestPhase);

    }

    public static ContestSchedule createNewSchedule() {

            ContestSchedule newContestSchedule = new ContestSchedule();

            newContestSchedule.setName("New contest schedule");

            newContestSchedule = ContestClient.createContestSchedule(newContestSchedule);
            return newContestSchedule;

    }
}
