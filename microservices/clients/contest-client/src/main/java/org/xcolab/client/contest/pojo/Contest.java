package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.RestService;

public class Contest extends AbstractContest {

    private final ContestClient contestClient;

    public Contest() {
        contestClient = ContestClientUtil.getClient();
    }

    public Contest(Contest value) {
        super(value);
        contestClient = ContestClientUtil.getClient();
    }

    public Contest(AbstractContest abstractContest, RestService restService) {
        super(abstractContest);
        contestClient = ContestClient.fromService(restService);
    }

    public String getContestLinkUrl() {
        String link = "/";
        link += contestClient.getContestType(this.getContestTypeId())
                .getFriendlyUrlStringContests();
        link += "/%d/%s";
        return String.format(link, this.getContestYear(), this.getContestUrlName());
    }

    public String getLogoPath() {
        Long i = this.getContestLogoId();
        if (i != null) {
            return "/contest?img_id="
                    + i;// + "&t=" + ImageServletTokenUtil.getToken(i.getImageId());
        }
        return "";
    }

    public String generateContestUrlName() {
        String contestUrlName = this.getContestShortName().toLowerCase();
        return contestUrlName.replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
    }
}
