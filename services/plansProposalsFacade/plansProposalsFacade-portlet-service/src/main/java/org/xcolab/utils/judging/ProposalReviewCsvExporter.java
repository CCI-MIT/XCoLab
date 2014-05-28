package org.xcolab.utils.judging;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import org.xcolab.enums.ContestPhaseType;

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

    // Cluster all proposal reviews by proposal since we have multiple reviews for each proposal (multiple judging phases)
    private Map<Proposal, List<ProposalReview>> proposalToProposalReviewsMap;
    private List<User> reviewers;

    // Helper variable, see comment below
    private Map<ProposalReview, Map<User, ProposalReview.IndividualReview>> userToReviewMaps;

    public ProposalReviewCsvExporter(Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap, List<User> reviewers) {
        this.proposalToProposalReviewsMap = proposalToProposalReviewsMap;
        this.reviewers = reviewers;

        buildUserToReviewMaps();
    }

    public String getCsvString() throws SystemException, PortalException {
        if (proposalToProposalReviewsMap.size() == 0) {
            return StringPool.BLANK;
        }

        String delimiter = TQF + DEL + TQF;
        // Set the table header

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

                String contestPhaseName = ContestPhaseType.getContestPhaseTypeByTypeId(proposalReview.getContestPhase().getContestPhaseType()).getName();
                tableBody.append("\"" + escapeQuote(contestPhaseName) + "\"" +  delimiter + "\"" + proposalReview.getAverageRating() + "\"");

                StringBuilder ratingString = new StringBuilder();
                StringBuilder commentString = new StringBuilder();
                Map<User, ProposalReview.IndividualReview> userToReviewMap = userToReviewMaps.get(proposalReview);
                for (User reviewer : reviewers) {
                    ProposalReview.IndividualReview individualReview = userToReviewMap.get(reviewer);
                    if (Validator.isNull(individualReview)) {
                        ratingString.append(delimiter + "\"\"");
                        commentString.append(delimiter + "\"\"");
                    } else {
                        ratingString.append(delimiter + individualReview.getRating());
                        commentString.append(delimiter + "\"" + escapeQuote(individualReview.getComment()) + TQF + "\"");
                    }

                }
                tableBody.append(ratingString).append(commentString).append("\n");
                isFirstLine = false;
            }
        }

        String rawCsv = getTableHeader() + tableBody;
        return replaceNonAsciiCharacters(deAccent(rawCsv));
    }

    /**
     * Admittedly this is a weird data structure, but it significantly improves performance, especially when dealing
     * with a long list of judges and/or proposals
     */
    private void buildUserToReviewMaps() {
        userToReviewMaps = new HashMap<>();

        for (List<ProposalReview> proposalReviews : proposalToProposalReviewsMap.values()) {
            for (ProposalReview proposalReview : proposalReviews) {
                Map<User, ProposalReview.IndividualReview> userToReviewMap = new HashMap<>(reviewers.size());

                for (ProposalReview.IndividualReview individualReview : proposalReview.getReviews()) {
                    userToReviewMap.put(individualReview.getReviewer(), individualReview);
                }

                userToReviewMaps.put(proposalReview, userToReviewMap);
            }
        }
    }

    private String getTableHeader() {
        String delimiter = TQF + DEL + TQF;

        String header = TQF + "Proposal title" + delimiter + "Proposal URL" + delimiter + "Contest Phase " + delimiter + "Avg";
        StringBuilder ratingSubHeader = new StringBuilder(TQF);
        StringBuilder commentSubHeader = new StringBuilder(TQF);
        for (User reviewer : reviewers) {
            String judgeInitials = reviewer.getFirstName().substring(0, 1).toUpperCase() + reviewer.getLastName().substring(0, 1).toUpperCase();
            ratingSubHeader.append(delimiter + "\"" + judgeInitials + " rating\"");
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
