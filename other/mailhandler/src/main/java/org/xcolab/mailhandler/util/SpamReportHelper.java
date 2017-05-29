package org.xcolab.mailhandler.util;

import org.xcolab.mailhandler.config.MailProperties.SpamSettings;

public class SpamReportHelper {

    private final Float spamScore;
    private final String spamReport;
    private final SpamSettings spamSettings;

    public SpamReportHelper(Float spamScore, String spamReport, SpamSettings spamSettings) {
        this.spamScore = spamScore;
        this.spamReport = spamReport;
        this.spamSettings = spamSettings;
    }

    public boolean shouldShowSpamReport() {
        return spamSettings.isShowReportOnWarning() && shouldShowSpamWarning();
    }

    public boolean shouldFilterMessage() {
        return spamSettings.isFilter() && spamScore != null
                && spamScore > spamSettings.getFilterThreshold();
    }

    public boolean shouldShowSpamWarning() {
        return spamSettings.isShowWarning() && spamScore != null
                && spamScore > spamSettings.getWarningThreshold();
    }

    public String getWarning() {
        final String s = "[Potential Spam]";
        if (spamSettings.isShowScoreOnWarning()) {
            return s + " [SpamScore: " + spamScore + "]";
        }
        return s;
    }

    public String formatSpamReport(boolean asHtml) {
        StringBuilder sb = new StringBuilder();
        if (asHtml) {
            sb.append("<br/><br/>=======<br/><br/>");
        } else {
            sb.append("\n\n=======\n\n");
        }
        sb.append(spamReport);
        return sb.toString();
    }
}
