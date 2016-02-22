package org.xcolab.portlets.admintasks;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * Created by johannes on 2/18/16.
 */
public final class ContestLinkMigrationUtil {

    private static final Log _log = LogFactoryUtil.getLog(ContestLinkMigrationUtil.class);

    private ContestLinkMigrationUtil() { }

    public static void generateContestUrls() throws SystemException {
        for (Contest contest : ContestLocalServiceUtil.getContests(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            String contestUrlName = ContestLocalServiceUtil.generateContestUrlName(contest);
            if (StringUtils.isNumeric(StringUtils.substringAfterLast(contestUrlName, "-"))) {
                contestUrlName = StringUtils.substringBeforeLast(contestUrlName, "-");
            }
            contest.setContestUrlName(contestUrlName);
            DateTime earliestDate = contest.getCreated() != null ? new DateTime(contest.getCreated()) : null;
            for (ContestPhase contestPhase : ContestPhaseLocalServiceUtil.getPhasesForContest(contest)) {
                final DateTime phaseStartDate = new DateTime(contestPhase.getPhaseStartDate());
                if (earliestDate == null || earliestDate.isAfter(phaseStartDate)) {
                    earliestDate = phaseStartDate;
                }
            }
            if (earliestDate != null) {
                contest.setContestYear(earliestDate.getYear());
            }
            contest.persist();
        }
    }
}
