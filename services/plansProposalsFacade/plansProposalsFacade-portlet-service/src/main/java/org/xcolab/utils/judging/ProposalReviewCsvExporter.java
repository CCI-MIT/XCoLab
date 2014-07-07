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

import java.text.Normalizer;
import java.util.HashMap;
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
            boolean isFirstLine = true;

            for (ProposalReview proposalReview : proposalToProposalReviewsMap.get(proposal)) {
                // Print the proposal name and url only for the first line of each proposal
                if (isFirstLine) {
                    tableBody.append(TQF + "\"" + escapeQuote(proposalName) + "\"" + delimiter +
                            "\"" + proposalReview.getProposalUrl() + "\"" + delimiter);
                } else {
                    tableBody.append(TQF + "\"\"" + delimiter + "\"\"" + delimiter);
                }

                String contestPhaseName = ContestPhaseTypeLocalServiceUtil.fetchContestPhaseType(proposalReview.getContestPhase().getContestPhaseType()).getName();
                tableBody.append("\"" + escapeQuote(contestPhaseName) + "\"");

                StringBuilder ratingString = new StringBuilder();
                StringBuilder commentString = new StringBuilder();
                for (ProposalRatingType ratingType : ratingTypes) {
                    Double average = proposalReview.getRatingAverage(ratingType);
                    if (Validator.isNull(average)) {
                        commentString.append(delimiter + "\"\"");
                    } else {
                        commentString.append(delimiter + "\"" + average + TQF + "\"");
                    }
                }
                for (User reviewer : reviewers) {
                    String review = proposalReview.getReview(reviewer);
                    if (Validator.isNull(review)) {
                        commentString.append(delimiter + "\"\"");
                    } else {
                        commentString.append(delimiter + "\"" + escapeQuote(review) + TQF + "\"");
                    }
                }
                tableBody.append(ratingString).append(commentString).append("\n");
                isFirstLine = false;
            }
        }

        String rawCsv = getTableHeader() + tableBody;
        return replaceNonAsciiCharacters(deAccent(rawCsv));
    }

    private String getTableHeader() {
        String header = TQF + "\"Proposal title\"" + delimiter + "\"Proposal URL\"" + delimiter + "\"Contest Phase\"";

        StringBuilder ratingSubHeader = new StringBuilder(TQF);
        for (ProposalRatingType ratingType : ratingTypes) {
            String ratingTitle = ratingType.getLabel();
            ratingSubHeader.append(delimiter + "\"" + ratingTitle + " avg\"");
        }

        StringBuilder commentSubHeader = new StringBuilder(TQF);
        for (User reviewer : reviewers) {
            String judgeInitials = reviewer.getFirstName().substring(0, 1).toUpperCase() + reviewer.getLastName().substring(0, 1).toUpperCase();
            commentSubHeader.append(delimiter + "\"" + judgeInitials + " comment\"");
        }

        header += ratingSubHeader.toString() + commentSubHeader.toString() + TQF + "\n";
        return header;
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
        return StringUtil.replace(input, "\"", "\"\"\"");
    }
}
