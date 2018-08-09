package org.xcolab.view.pages.contestmanagement.utils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.templates.ProposalTemplate;
import org.xcolab.client.contest.util.ContestScheduleChangeHelper;
import org.xcolab.client.proposals.exceptions.PlanTemplateNotFoundException;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;

public final class ContestCreatorUtil {

    private static final Logger log = LoggerFactory.getLogger(ContestCreatorUtil.class);

    private static final String SEED_CONTEST_SCHEDULE_NAME = "Seed Contest Schedule";
    private static final String DEFAULT_SCHEDULE_NAME = "[DEFAULT] Contest schedule";
    private static final String DEFAULT_TEMPLATE_NAME = "[DEFAULT] Contest template";

    private ContestCreatorUtil() { }

    public static Contest createNewContest(String title, long authorUserId) {
        Contest contest = ContestClientUtil.createContest(authorUserId, title);
        contest.setContestYear((long) DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShowInTileView(true);
        contest.setShowInListView(true);
        contest.setShowInOutlineView(true);
        final Long templateId = getOrCreateDefaultTemplate().getId();
        contest.setProposalTemplateId(templateId);
        final Long contestScheduleId = getOrCreateDefaultContestSchedule().getId();
        contest.setContestScheduleId(contestScheduleId);
        contest.setContestTypeId(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        ContestClientUtil.updateContest(contest);
        ContestScheduleChangeHelper
                changeHelper = new ContestScheduleChangeHelper(contest.getId(), contestScheduleId);
        changeHelper.changeScheduleForBlankContest();

        return contest;
    }

    private static ProposalTemplate getOrCreateDefaultTemplate() {
        final long defaultTemplateId = ConfigurationAttributeKey.DEFAULT_CONTEST_TEMPLATE_ID.get();
        try {
            if (defaultTemplateId > 0) {
                return ProposalTemplateClientUtil.getPlanTemplate(defaultTemplateId);
            }
            final ProposalTemplate newDefaultTemplate = ProposalTemplateLifecycleUtil
                    .create(DEFAULT_TEMPLATE_NAME);

            ConfigurationAttribute defaultTemplateAttribute = new ConfigurationAttribute();
            defaultTemplateAttribute
                    .setName(ConfigurationAttributeKey.DEFAULT_CONTEST_TEMPLATE_ID.name());
            defaultTemplateAttribute.setNumericValue(newDefaultTemplate.getId());
            AdminClient.createConfigurationAttribute(defaultTemplateAttribute);

            log.warn("No DEFAULT_TEMPLATE_SCHEDULE_ID found; created new Schedule with id {} "
                    + "and corresponding ConfigurationAttribute.", newDefaultTemplate.getId());

            return newDefaultTemplate;
        } catch (PlanTemplateNotFoundException e) {
            //fail early if it doesn't exist
            throw ReferenceResolutionException
                    .toObject(ProposalTemplate.class, defaultTemplateId)
                    .fromObject(ConfigurationAttribute.class, "DEFAULT_CONTEST_TEMPLATE_ID");
        }
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
            defaultScheduleAttribute.setNumericValue(newDefaultSchedule.getId());
            AdminClient.createConfigurationAttribute(defaultScheduleAttribute);

            log.warn("No DEFAULT_CONTEST_SCHEDULE_ID found; created new Schedule with id {} "
                    + "and corresponding ConfigurationAttribute.", newDefaultSchedule.getId());

            return newDefaultSchedule;
        } catch (ContestScheduleNotFoundException e) {
            //fail early if it doesn't exist
            throw ReferenceResolutionException
                    .toObject(ContestSchedule.class, defaultContestScheduleId)
                    .fromObject(ConfigurationAttribute.class, "DEFAULT_CONTEST_SCHEDULE_ID");
        }
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() {
        if (ContestClientUtil.getAllContestSchedules().isEmpty()) {
            ContestScheduleLifecycleUtil
                    .createProposalCreationOnlySchedule(SEED_CONTEST_SCHEDULE_NAME);
        }
    }
}
