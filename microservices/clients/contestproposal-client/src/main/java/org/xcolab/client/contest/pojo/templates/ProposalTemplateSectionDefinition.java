package org.xcolab.client.contest.pojo.templates;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.enums.Plurality;
import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProposalTemplateSectionDefinition extends AbstractProposalTemplateSectionDefinition {

    private static final Logger log = LoggerFactory.getLogger(ProposalTemplateSectionDefinition.class);

    private final Proposal proposal;

    private final ServiceNamespace serviceNamespace;

    public ProposalTemplateSectionDefinition() {
        proposal = null;
        serviceNamespace = null;
    }

    public ProposalTemplateSectionDefinition(ProposalTemplateSectionDefinition value) {
        super(value);
        if(value.getServiceNamespace() != null){
            serviceNamespace = value.getServiceNamespace();
        }else{
            serviceNamespace = null;
        }
        proposal = null;
    }

    public ProposalTemplateSectionDefinition(ProposalTemplateSectionDefinition value, Proposal proposal) {
        super(value);
        if (value.getServiceNamespace() != null) {
            serviceNamespace = value.getServiceNamespace();
        } else {
            if (proposal.getServiceNamespace() != null) {
                serviceNamespace = proposal.getServiceNamespace();
            } else {
                serviceNamespace = null;
            }
        }
        this.proposal = proposal;
    }
    public ProposalTemplateSectionDefinition(
            AbstractProposalTemplateSectionDefinition abstractPlanSectionDefinition,
            ServiceNamespace serviceNamespace) {
        super(abstractPlanSectionDefinition);
        proposal = null;
        this.serviceNamespace = serviceNamespace;
    }

    public List<Long> getAdditionalIdsAsList() {
        final String stringOfStringIds = this.getAdditionalIds();
        return IdListUtil.getIdsFromString(stringOfStringIds);
    }

    public String getContent() {
        ProposalAttribute attr = getSectionAttribute();

        if (attr == null) {
            return null;
        }
        return attr.getStringValue().trim();
    }

    public String getContentFormatted() throws URISyntaxException {
        String content = getContent();
        if (content == null) {
            //default text if available
            return ( !StringUtils.isEmpty(this.getDefaultText())) ? this.getDefaultText() : null;
        }
        final String baseUri = PlatformAttributeKey.COLAB_URL.get();
        Document contentDocument = Jsoup.parse(content.trim(), baseUri);
        // TODO COLAB-2593: do we need this? this should already happen in HtmlUtil#cleanX
        contentDocument = HtmlUtil.addNoFollowToLinkTagsInDocument(contentDocument);

        for (Element aTagElements : contentDocument.select("a")) {
            String curURL = aTagElements.attr("href");
            final String[] youtubeAddresses = {"http://youtu.be", "https://youtu.be", "http://www.youtube.com",
                    "https://www.youtube.com", "http://youtube.com", "https://youtube.com"};
            boolean isYoutube = false;
            for (String youtubePrefix: youtubeAddresses) {
                if (curURL.startsWith(youtubePrefix)) {
                    isYoutube = true;
                    break;
                }
            }
            if (!isYoutube) {
                continue;
            }
            if (!(aTagElements.hasClass("utube") || aTagElements.text().toLowerCase().startsWith("embed") || aTagElements.text().equalsIgnoreCase("v"))) {
                // only links with "embed" text or "utube" class should be replaced by an iframe
                continue;
            }
            String videoId = null;
            if (curURL.indexOf("?") > 0 && curURL.indexOf("v=") > 0) {
                // legacy url support
                List<NameValuePair> params = URLEncodedUtils
                        .parse(curURL.substring(curURL.indexOf("?") + 1), Charset.defaultCharset());
                for (NameValuePair nvp : params) {
                    if (nvp.getName().equals("v")) {
                        videoId = nvp.getValue();
                    }
                }
            } else {
                final Pattern videoIdPattern = Pattern.compile("\\/([\\p{Alnum}\\-_]{11})");
                Matcher m = videoIdPattern.matcher(curURL);
                if (m.find()) {
                    videoId = m.group(1);
                }
            }
            if (videoId != null) {

                aTagElements.after("<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe><br/>");
                aTagElements.remove();
            }
        }

        // Regex pattern originated from
        // http://stackoverflow.com/questions/1806017/extracting-urls-from-a-text-document-using-java-regular-expressions
        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(((\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2})))|localhost)(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");


        for (Element pTagElements : contentDocument.select("p")) {
            // Separate the <p> tags by the space character and process potential URLs
            String html = pTagElements.html();

            // Eliminates weird &nbsp; ASCII val 160 characters
            String text = pTagElements.text().replaceAll("[\\u00A0]", " ");
            String[] words = text.split("\\s");
            for (int i = 0; i < words.length; i++) {
                final String word = words[i];
                final Matcher matcher = pattern.matcher(word);

                // If a match is found create a new <a> tag
                if (matcher.find()) {
                    final String link = word.substring(matcher.start(), matcher.end());
                    final Proposal linkedProposal = ProposalClientUtil.getProposalFromLinkUrl(link);

                    String elementName;
                    if (linkedProposal != null) {
                        elementName = linkedProposal.getName();
                    } else {
                        elementName = link;
                    }

                    String newLinkElementWithNoFollow = HtmlUtil.createLink(link, elementName);
                    // Replace exactly this word in the HTML code with leading and trailing spaces
                    if (words.length == 1) { // In this case there are no leading and trailing spaces in the html code
                        if (!html.contains("<")) {
                            html = html.replaceFirst(Pattern.quote(word), newLinkElementWithNoFollow);
                        }
                    } else if (i == 0) {
                        html = html.replaceFirst(Pattern.quote(word) + "(\\s|&nbsp;)", newLinkElementWithNoFollow);
                    } else if (i == words.length - 1) {
                        html = html.replaceFirst("(\\s|&nbsp;)" + Pattern.quote(word), newLinkElementWithNoFollow);
                    } else {
                        html = html.replaceFirst("(\\s|&nbsp;)" + Pattern.quote(word) + "(\\s|&nbsp;)", newLinkElementWithNoFollow);
                    }
                }
            }

            // Rebuild the whole value string of the <p> tag and exchange the old one
            pTagElements.after("<p>"+html+"</p>");
            pTagElements.remove();
        }

        return contentDocument.select("body").html();
    }

    public PlanSectionTypeKeys getType() {
        if (StringUtils.isBlank(this.getType_())) {
            return PlanSectionTypeKeys.TEXT;
        }
        return PlanSectionTypeKeys.valueOf(this.getType_());
    }

    public Long getSectionDefinitionId() {
        return this.getId();
    }

    public boolean isLocked() {
        return this.getLocked();
    }


    public OntologyTerm getNumericValueAsOntologyTerm() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }

        return OntologyClientUtil.getOntologyTerm(attr.getNumericValue());
    }

    public Proposal getNumericValueAsProposal() throws ProposalNotFoundException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return (ProposalClientUtil.getProposal(attr.getNumericValue()));
    }

    public List<Proposal> getStringValueAsProposalArray() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || StringUtils.isEmpty(attr.getStringValue())) {
            return Collections.emptyList();
        }

        final List<Long> proposalIds = IdListUtil.getIdsFromString(attr.getStringValue());
        final List<Proposal> proposals = new ArrayList<>(proposalIds.size());

        for (Long proposalId : proposalIds) {
            try {
                proposals.add(new Proposal(ProposalClientUtil.getProposal(proposalId)));
            } catch (ProposalNotFoundException e) {
                log.warn(String.format("Section %d contains invalid proposal reference %d",
                        getSectionDefinitionId(), proposalId));
            }
        }
        return proposals;
    }

    public long getNumericValue() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return 0;
        }
        return attr.getNumericValue();
    }

    public String getStringValue() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    public List<OntologyTerm> getFocusAreaTerms() {
        if (this.getFocusAreaId() <= 0) {
            return null;
        }

        FocusArea area = OntologyClientUtil.getFocusArea(this.getFocusAreaId());

        return OntologyClientUtil.getOntologyTermsForFocusArea(area);
    }

    public List<String> getOptionsForDropdownMenu() {
        return Stream.of(this.getAllowedValues().split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getOptionsForCheckbox() {
        return Stream.of(this.getAllowedValues().split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private List<String> getSelectedValuesForCheckbox() {
        return Arrays.asList(this.getStringValue().split(";"));
    }

    public boolean isOptionForCheckboxSelected(String value) {
        for (String selectedValue : getSelectedValuesForCheckbox()) {
            if (selectedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public List<Long> getAllowedContestTypeIdsList() {
        final List<Long> allowedContestTypeIds =
            IdListUtil.getIdsFromString(this.getAllowedContestTypeIds());
        if (allowedContestTypeIds.isEmpty()) {
            return Collections.singletonList(
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        }
        return allowedContestTypeIds;
    }

    public String getProposalNames() {
        return ContestTypeClient
                .getProposalNames(getAllowedContestTypeIdsList(), Plurality.SINGULAR.name(), "or");
    }


    public String getProposalNamesPlural() {
        return ContestTypeClient
                .getProposalNames(getAllowedContestTypeIdsList(), Plurality.PLURAL.name(), "and");
    }

    public String getContestNames() {
        return ContestTypeClient
                .getContestNames(getAllowedContestTypeIdsList(), Plurality.SINGULAR.name(), "or");
    }

    public String getContestNamesPlural() {
        return ContestTypeClient
                .getContestNames(getAllowedContestTypeIdsList(), Plurality.PLURAL.name(), "or");
    }

    public ServiceNamespace getServiceNamespace() {
        return serviceNamespace;
    }

    private ProposalAttribute getSectionAttribute() {
        if (proposal == null) {
            throw new ProposalFieldNotInitializedException();
        }
        ProposalAttributeHelper proposalAttributeHelper = proposal.getProposalAttributeHelper();
        return proposalAttributeHelper
                .getAttributeOrNull(ProposalAttributeKeys.SECTION, this.getId());
    }

    public static class ProposalFieldNotInitializedException extends RuntimeException {

        public ProposalFieldNotInitializedException() {
            super("A value in the proposal field is required to perform this action.");
        }
    }
}
