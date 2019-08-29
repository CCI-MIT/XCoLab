package org.xcolab.view.pages.proposals.judging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.commons.html.HtmlUtil;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ProposalReviewCsvExporter {

    private static final String DEL = ","; // delimiter
    private static final String TQF = ""; // text qualifier
    private static final String delimiter = TQF + DEL + TQF;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final Contest contest;
    /*
        * Cluster all proposal reviews (from multiple Contest phases) by proposal since we
        * have multiple reviews for each proposal (multiple judging phases)
        */
    private final Map<Proposal, List<ProposalReview>> proposalToProposalReviewsMap;
    private final List<ProposalRatingType> ratingTypes;

    public ProposalReviewCsvExporter(Contest contest,
            Map<Proposal, List<ProposalReview>> proposalToProposalReviewsMap,
            List<ProposalRatingType> ratingTypes) {
        this.contest = contest;
        this.proposalToProposalReviewsMap = proposalToProposalReviewsMap;
        this.ratingTypes = ratingTypes;
    }


    public String getCsvString() {
        if (proposalToProposalReviewsMap.isEmpty()) {
            return "";
        }

        StringBuilder tableBody = new StringBuilder();
        for (Map.Entry<Proposal, List<ProposalReview>> entry : proposalToProposalReviewsMap.entrySet()) {
            final Proposal proposal = entry.getKey();
            final List<ProposalReview> proposalReviews = entry.getValue();
            String proposalName = ProposalAttributeClientUtil.getProposalAttribute(proposal.getId(),
                    ProposalAttributeKeys.NAME, 0L).getStringValue();



            for (ProposalReview proposalReview : proposalReviews) {
                for (Member reviewer : proposalReview.getReviewers()) {

                    tableBody.append(getRowHeader(proposalName, proposalReview));
                    tableBody.append(String.format("\"%s %s\"", (reviewer.getFirstName()), (reviewer.getLastName())));

                    StringBuilder commentString = new StringBuilder();

                    Double ratingAverage = proposalReview.getUserRatingAverage(reviewer);

                    if (ratingAverage == null) {
                        commentString.append(delimiter + "\"-\"" + TQF);
                    } else {
                        commentString.append(String.format("%s\"%s%s\"", delimiter, DECIMAL_FORMAT.format(ratingAverage), TQF));
                    }

                    for (ProposalRatingType ratingType : ratingTypes) {
                        Double rating = proposalReview.getUserRating(reviewer, ratingType);
                        if (rating == null) {
                            if (proposalReview.getReviewers().contains(reviewer)) {
                                commentString.append(delimiter + "\"-\"" + TQF);
                            } else {
                                commentString.append(delimiter + "\"\"");
                            }
                        } else {
                            commentString.append(String.format("%s\"%s%s\"", delimiter,
                                    DECIMAL_FORMAT.format(rating), TQF));
                        }
                    }

                    Boolean shouldAdvanceDecision = proposalReview.getShouldAdvanceDecision(reviewer);
                    if (shouldAdvanceDecision == null) {
                        commentString.append(delimiter + "\"\"");
                    } else {
                        commentString.append(String.format("%s\"%s%s\"", delimiter, shouldAdvanceDecision, TQF));
                    }

                    String review = proposalReview.getReview(reviewer);
                    if (StringUtils.isEmpty(review)) {
                        commentString.append(delimiter + "\"\"");
                    } else {
                        commentString.append(String.format("%s\"%s%s\"", delimiter, escapeQuote(review), TQF));
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

    private String getAverageRatings(ProposalReview proposalReview) {
        StringBuilder averageRating = new StringBuilder();
        averageRating.append(String.format("\"Average\"%s\"%s%s\"", delimiter, DECIMAL_FORMAT.format(proposalReview.getRatingAverage()), TQF));

        for (ProposalRatingType ratingType : ratingTypes) {
            Double average = proposalReview.getRatingAverage(ratingType);
            if (average == null) {
                averageRating.append(delimiter + "\"\"");
            } else {
                averageRating.append(String.format("%s\"%s%s\"", delimiter, DECIMAL_FORMAT.format(average), TQF));
            }
        }

        return averageRating.toString();
    }

    private String getRowHeader(String proposalName, ProposalReview proposalReview) {
        String contestPhaseName = ContestClientUtil
                .getContestPhaseType(proposalReview.getContestPhase().getContestPhaseTypeId())
                .getName();

        Proposal proposal = proposalReview.getProposal();

        final String dataFields = getDataFields(proposal);

        return TQF + "\"" + escapeQuote(proposalName) + "\""
                + delimiter + "\"" + escapeQuote(proposalReview.getProposalTeamAuthor()) + "\""
                + delimiter + "\"" + proposalReview.getProposalUrl() + "\""
                + delimiter + "\"" + escapeQuote(proposalReview.getProposal().getCleanPitch()) + "\""
                + delimiter + dataFields
                + "\"" + escapeQuote(contestPhaseName) + "\"" + delimiter;
    }

    private String getDataFields(Proposal proposal) {
        StringBuilder dataFields = new StringBuilder(TQF);
        for (ProposalTemplateSectionDefinition sectionDefinition : proposal.getSections()) {
            if (sectionDefinition.getIncludeInJudgingReport()) {
                dataFields.append("\"")
                        .append(escapeQuote(HtmlUtil.cleanAll(sectionDefinition.getContent())))
                        .append("\"")
                        .append(delimiter);
            }
        }
        return dataFields.toString();
    }

    private String getTableHeader() {
        final String ratingSubHeader = getRatingSubHeader();
        final String dataFieldHeaders = getDataFieldHeaders();

        return TQF + "\"Proposal title\"" + delimiter +
                "\"Author/Team name\"" + delimiter +
                "\"Proposal URL\"" + delimiter +
                "\"Proposal Pitch\"" + delimiter +
                dataFieldHeaders +
                "\"Contest Phase\"" + delimiter +
                "\"Judge\"" + delimiter +
                "\"Average\"" + delimiter +
                ratingSubHeader +
                "\"ShouldAdvance\"" + delimiter +
                "\"Comment\"" + delimiter +
                "\"Presented by\"" + "\n";
    }

    private String getRatingSubHeader() {
        StringBuilder ratingSubHeader = new StringBuilder(TQF);
        for (ProposalRatingType ratingType : ratingTypes) {
            String ratingTitle = ratingType.getLabel();
            ratingSubHeader.append(String.format("\"%s\"%s", ratingTitle, delimiter));
        }
        return ratingSubHeader.toString();
    }

    private String getDataFieldHeaders() {
        StringBuilder dataFieldHeaders = new StringBuilder(TQF);
        for (ProposalTemplateSectionDefinition sectionDefinition : contest.getSections()) {
            if (sectionDefinition.getIncludeInJudgingReport()) {
                dataFieldHeaders.append(
                        String.format("\"%s\"%s", sectionDefinition.getTitle(), delimiter));
            }
        }
        return dataFieldHeaders.toString();
    }

    private String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    private String replaceNonAsciiCharacters(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", "");
    }

    private String escapeQuote(String input) {
        //replace double quotes with single quotes (safer than 3 quotes)
        input = StringUtils.replace(input, "\\\"", "'");
        //delete new lines
        input = StringUtils.replace(input, "\r\n", " ");
        input = StringUtils.replace(input, "\n", " ");
        input = StringUtils.replace(input, "\r", " ");

        return input;
    }
}
