package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplateSectionDefinition;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.enums.Plurality;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;

import java.io.Serializable;
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

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalTemplateSectionDefinitionWrapper extends ProposalTemplateSectionDefinition
        implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(
            ProposalTemplateSectionDefinitionWrapper.class);

    private final ProposalWrapper proposal;

    public ProposalTemplateSectionDefinitionWrapper() {
        proposal = null;
    }

    public ProposalTemplateSectionDefinitionWrapper(ProposalTemplateSectionDefinitionWrapper value) {
        super(value);
        proposal = null;
    }

    public ProposalTemplateSectionDefinitionWrapper(ProposalTemplateSectionDefinitionWrapper value, ProposalWrapper proposal) {
        super(value);
        this.proposal = proposal;
    }
    public ProposalTemplateSectionDefinitionWrapper(
            ProposalTemplateSectionDefinition abstractProposalTemplateSectionDefinition) {
        super(abstractProposalTemplateSectionDefinition);
        proposal = null;
    }

    @JsonIgnore
    public List<Long> getAdditionalIdsAsList() {
        final String stringOfStringIds = this.getAdditionalIds();
        return IdListUtil.getIdsFromString(stringOfStringIds);
    }

    @JsonIgnore
    public String getContent() {
        ProposalAttribute attr = getSectionAttribute();

        if (attr == null) {
            return null;
        }
        return attr.getStringValue().trim();
    }

    @JsonIgnore
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
                    final ProposalWrapper linkedProposal = StaticProposalContext.getProposalClient()
                            .getProposalFromLinkUrl(link);

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

    @JsonIgnore
    public ProposalTemplateSectionType getTypeEnum() {
        if (StringUtils.isBlank(this.getType())) {
            return ProposalTemplateSectionType.TEXT;
        }
        return ProposalTemplateSectionType.valueOf(this.getType());
    }

    @JsonIgnore
    public Long getSectionDefinitionId() {
        return this.getId();
    }

    @JsonIgnore
    public OntologyTermWrapper getNumericValueAsOntologyTerm() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }

        return StaticContestContext.getOntologyClient().getOntologyTerm(attr.getNumericValue());
    }

    @JsonIgnore
    public ProposalWrapper getNumericValueAsProposal() throws ProposalNotFoundException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return (new ProposalWrapper(StaticProposalContext.getProposalClient().getProposal(attr.getNumericValue())));
    }

    @JsonIgnore
    public List<ProposalWrapper> getStringValueAsProposalArray() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || StringUtils.isEmpty(attr.getStringValue())) {
            return Collections.emptyList();
        }

        final List<Long> proposalIds = IdListUtil.getIdsFromString(attr.getStringValue());
        final List<ProposalWrapper> proposals = new ArrayList<>(proposalIds.size());

        for (Long proposalId : proposalIds) {
            try {
                proposals.add(new ProposalWrapper(StaticProposalContext.getProposalClient()
                        .getProposal(proposalId)));
            } catch (ProposalNotFoundException e) {
                log.warn(String.format("Section %d contains invalid proposal reference %d",
                        getSectionDefinitionId(), proposalId));
            }
        }
        return proposals;
    }

    @JsonIgnore
    public long getNumericValue() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return 0;
        }
        return attr.getNumericValue();
    }

    @JsonIgnore
    public String getStringValue() {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return "";
        }
        return attr.getStringValue();
    }

    @JsonIgnore
    public List<OntologyTermWrapper> getFocusAreaTerms() {
        if (this.getFocusAreaId() <= 0) {
            return null;
        }

        FocusAreaWrapper area = StaticContestContext.getOntologyClient()
                .getFocusArea(this.getFocusAreaId());

        return StaticContestContext.getOntologyClient().getOntologyTermsForFocusArea(area);
    }

    @JsonIgnore
    public List<String> getOptionsForDropdownMenu() {
        return Stream.of(this.getAllowedValues().split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<String> getOptionsForCheckbox() {
        return Stream.of(this.getAllowedValues().split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    private List<String> getSelectedValuesForCheckbox() {
        return Arrays.asList(this.getStringValue().split(";"));
    }

    @JsonIgnore
    public boolean isOptionForCheckboxSelected(String value) {
        for (String selectedValue : getSelectedValuesForCheckbox()) {
            if (selectedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public List<Long> getAllowedContestTypeIdsList() {
        final List<Long> allowedContestTypeIds =
            IdListUtil.getIdsFromString(this.getAllowedContestTypeIds());
        if (allowedContestTypeIds.isEmpty()) {
            return Collections.singletonList(
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get());
        }
        return allowedContestTypeIds;
    }

    @JsonIgnore
    public String getProposalNames() {
        return StaticAdminContext.getContestTypeClient()
                .getProposalNames(getAllowedContestTypeIdsList(), Plurality.SINGULAR.name(), "or");
    }

    @JsonIgnore
    public String getProposalNamesPlural() {
        return StaticAdminContext.getContestTypeClient()
                .getProposalNames(getAllowedContestTypeIdsList(), Plurality.PLURAL.name(), "and");
    }

    @JsonIgnore
    public String getContestNames() {
        return StaticAdminContext.getContestTypeClient()
                .getContestNames(getAllowedContestTypeIdsList(), Plurality.SINGULAR.name(), "or");
    }

    @JsonIgnore
    public String getContestNamesPlural() {
        return StaticAdminContext.getContestTypeClient()
                .getContestNames(getAllowedContestTypeIdsList(), Plurality.PLURAL.name(), "or");
    }

    @JsonIgnore
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
