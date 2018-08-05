package org.xcolab.view.pages.contestmanagement.utils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestScheduleNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
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

    public static Contest createNewContest(String contestShortName, long authorUserid) {
        Contest contest = ContestClientUtil.createContest(authorUserid, contestShortName);
        contest.setContestYear((long) DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShow_in_tile_view(true);
        contest.setShow_in_list_view(true);
        contest.setShow_in_outline_view(true);
        final Long templateId = getOrCreateDefaultTemplate().getId_();
        contest.setPlanTemplateId(templateId);
        final Long contestScheduleId = getOrCreateDefaultContestSchedule().getId_();
        contest.setContestScheduleId(contestScheduleId);
        contest.setContestTypeId(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        ContestClientUtil.updateContest(contest);
        ContestScheduleChangeHelper
                changeHelper = new ContestScheduleChangeHelper(contest.getContestPK(), contestScheduleId);
        changeHelper.changeScheduleForBlankContest();

        return contest;
    }

    private static PlanTemplate getOrCreateDefaultTemplate() {
        final long defaultTemplateId = ConfigurationAttributeKey.DEFAULT_CONTEST_TEMPLATE_ID.get();
        try {
            if (defaultTemplateId > 0) {
                return PlanTemplateClientUtil.getPlanTemplate(defaultTemplateId);
            }
            final PlanTemplate newDefaultTemplate = ProposalTemplateLifecycleUtil
                    .create(DEFAULT_TEMPLATE_NAME);

            ConfigurationAttribute defaultTemplateAttribute = new ConfigurationAttribute();
            defaultTemplateAttribute
                    .setName(ConfigurationAttributeKey.DEFAULT_CONTEST_TEMPLATE_ID.name());
            defaultTemplateAttribute.setNumericValue(newDefaultTemplate.getId_());
            AdminClient.createConfigurationAttribute(defaultTemplateAttribute);

            log.warn("No DEFAULT_TEMPLATE_SCHEDULE_ID found; created new Schedule with id {} "
                    + "and corresponding ConfigurationAttribute.", newDefaultTemplate.getId_());

            return newDefaultTemplate;
        } catch (PlanTemplateNotFoundException e) {
            //fail early if it doesn't exist
            throw ReferenceResolutionException
                    .toObject(PlanTemplate.class, defaultTemplateId)
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
            defaultScheduleAttribute.setNumericValue(newDefaultSchedule.getId_());
            AdminClient.createConfigurationAttribute(defaultScheduleAttribute);

            log.warn("No DEFAULT_CONTEST_SCHEDULE_ID found; created new Schedule with id {} "
                    + "and corresponding ConfigurationAttribute.", newDefaultSchedule.getId_());

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
