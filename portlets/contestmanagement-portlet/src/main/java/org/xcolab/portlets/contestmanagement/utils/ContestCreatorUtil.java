package org.xcolab.portlets.contestmanagement.utils;

import org.joda.time.DateTime;

import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleChangeHelper;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.util.Random;

public final class ContestCreatorUtil {

    private static final String SEED_CONTEST_SCHEDULE_NAME = "Seed Contest Schedule";

    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    //TODO: remove hard coded defaults
    public final static long DEFAULT_CONTEST_SCHEDULE_ID = 601L;
    public final static long DEFAULT_CONTEST_TEMPLATE_ID = 102L;
    private static final Random rand = new Random();

    private ContestCreatorUtil() { }

    public static Contest createNewContest(String contestShortName)  {

        Contest contest = ContestClientUtil.createContest(10144L, contestShortName);
        contest.setContestYear((long) DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShow_in_tile_view(true);
        contest.setShow_in_list_view(true);
        contest.setShow_in_outline_view(true);
        contest.setPlanTemplateId(DEFAULT_CONTEST_TEMPLATE_ID);
        contest.setContestScheduleId(DEFAULT_CONTEST_SCHEDULE_ID);
        contest.setContestTypeId(
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        ContestClientUtil.updateContest(contest);
        ContestScheduleChangeHelper changeHelper = new ContestScheduleChangeHelper(contest, DEFAULT_CONTEST_SCHEDULE_ID);
        changeHelper.changeScheduleForBlankContest();
        try {
            setGroupAndDiscussionForContest(contest);
        } catch (PortalException| SystemException ignored){

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
        long discussionId = ThreadClientUtil.createThread(thread).getThreadId();
        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(discussionId);
        ContestClientUtil.updateContest(c);
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() {
        try {
            if (ContestScheduleLocalServiceUtil.getContestSchedulesCount() == 0) {
                ContestScheduleLifecycleUtil.createProposalCreationOnlySchedule(SEED_CONTEST_SCHEDULE_NAME);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }
}
