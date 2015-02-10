package org.xcolab.utils.judging;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by kmang on 25/05/14.
 */
public class ProposalReviewCsvExporter {
    private static final String DEL = ","; // delimiter
    private static final String TQF = ""; // text qualifier
    private static final String delimiter = TQF + DEL + TQF;

    private static final DecimalFormat df = new DecimalFormat("#.##");
    /*
    * Cluster all proposal reviews (from multiple Contest phases) by proposal since we
    * have multiple reviews for each proposal (multiple judging phases)
    */
    private Map<Proposal, List<ProposalReview>> proposalToProposalReviewsMap;
    private List<User> reviewers;
    private List<ProposalRatingType> ratingTypes;

    public ProposalReviewCsvExporter(Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap, List<User> reviewers, List<ProposalRatingType> ratingTypes) {
        this.proposalToProposalReviewsMap = proposalToProposalReviewsMap;
        this.reviewers = reviewers;
        this.ratingTypes = ratingTypes;
    }

    public String getCsvString() throws SystemException, PortalException {
        if (proposalToProposalReviewsMap.size() == 0) {
            return StringPool.BLANK;
        }

        StringBuilder tableBody = new StringBuilder();
        for (Proposal proposal : proposalToProposalReviewsMap.keySet()) {
            String proposalName = ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(),
                    ProposalAttributeKeys.NAME, 0).getStringValue();

            for (ProposalReview proposalReview : proposalToProposalReviewsMap.get(proposal)) {
                for (User reviewer : proposalReview.getReviewers()) {

                    tableBody.append(getRowHeader(proposalName, proposalReview));
                    tableBody.append("\"" + reviewer.getFirstName() + " " + reviewer.getLastName() + "\"");

                    StringBuilder commentString = new StringBuilder();

                    Double ratingAverage = proposalReview.getUserRatingAverage(reviewer);

                    if (Validator.isNull(ratingAverage)) {
                        commentString.append(delimiter + "\"-\"" + TQF);
                    } else {
                        commentString.append(delimiter + "\"" + df.format(ratingAverage) + TQF + "\"");
                    }

                    for (ProposalRatingType ratingType : ratingTypes) {
                        Double rating = proposalReview.getUserRating(reviewer,ratingType);
                        if (Validator.isNull(rating)) {
                            if(proposalReview.getReviewers().contains(reviewer)) {
                                commentString.append(delimiter + "\"-\"" + TQF);
                            }
                            else {
                                commentString.append(delimiter + "\"\"");
                            }
                        } else {
                            commentString.append(delimiter + "\"" + df.format(rating) + TQF + "\"");
                        }
                    }

                    String review = proposalReview.getReview(reviewer);
                    if (Validator.isNull(review)) {
                        commentString.append(delimiter + "\"\"");
                    } else {
                        commentString.append(delimiter + "\"" + escapeQuote(review) + TQF + "\"");
                    }

                    tableBody.append(commentString).append("\n");
                }

                tableBody.append(getRowHeader(proposalName, proposalReview));
                tableBody.append(getAverageRatings(proposalReview)).append("\n");
            }
        }

        String rawCsv = getTableHeader() + tableBody;
        return replaceNonAsciiCharacters(deAccent(rawCsv));
    }

    private String getAverageRatings(ProposalReview proposalReview){
        StringBuilder averageRating = new StringBuilder();
        averageRating.append( "\"Average\"" + delimiter + "\"" + df.format(proposalReview.getRatingAverage()) + TQF + "\"");

        for (ProposalRatingType ratingType : ratingTypes) {
            Double average = proposalReview.getRatingAverage(ratingType);
            if (Validator.isNull(average)) {
                averageRating.append(delimiter + "\"\"");
            } else {
                averageRating.append(delimiter + "\"" + df.format(average) + TQF + "\"");
            }
        }

        return averageRating.toString();
    }

    private String getRowHeader(String proposalName, ProposalReview proposalReview){
        String contestPhaseName = "";
        try {
            contestPhaseName = ContestPhaseTypeLocalServiceUtil.fetchContestPhaseType(proposalReview.getContestPhase().getContestPhaseType()).getName();
        } catch(SystemException s){
            // Ignore contest phase
        }
        return  TQF + "\"" + escapeQuote(proposalName) + "\"" + delimiter +
                "\"" + escapeQuote(proposalReview.getProposalTeamAuthor()) + "\"" + delimiter +
                "\"" + proposalReview.getProposalUrl() + "\"" + delimiter +
                "\"" + escapeQuote(contestPhaseName) + "\"" + delimiter;
    }

    private String getTableHeader() {
        StringBuilder ratingSubHeader = new StringBuilder(TQF);
        for (ProposalRatingType ratingType : ratingTypes) {
            String ratingTitle = ratingType.getLabel();
            ratingSubHeader.append("\"" + ratingTitle + "\"" + delimiter);
        }

        return  TQF + "\"Proposal title\"" + delimiter +
                "\"Author/Team name\"" + delimiter +
                "\"Proposal URL\"" + delimiter +
                "\"Contest Phase\""  + delimiter +
                "\"Judge\"" + delimiter +
                "\"Average\"" + delimiter +
                ratingSubHeader.toString() +
                "\"Comment\"" + delimiter +
                "\"Presented by\"" + "\n";
    }

    private String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    private String replaceNonAsciiCharacters(String str){
        return str.replaceAll("[^\\x00-\\x7F]", "");
    }

    private String escapeQuote(String input) {
        //replace double quotes with single quotes (safer than 3 quotes)
        input = StringUtil.replace(input, "\"", "'");
        //delete new lines
        input = StringUtil.replace(input, "\r\n", " ");
        input = StringUtil.replace(input, "\n", " ");
        input = StringUtil.replace(input, "\r", " ");

        return input;
    }
}
