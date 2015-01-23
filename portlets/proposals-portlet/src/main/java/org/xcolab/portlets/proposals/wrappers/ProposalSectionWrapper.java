package org.xcolab.portlets.proposals.wrappers;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;

import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xcolab.portlets.proposals.utils.LinkUtils;
import org.xcolab.commons.utils.ContentFilterHelper;

public class ProposalSectionWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalSectionWrapper.class);
    private PlanSectionDefinition definition;
    private Proposal proposal;
    private ProposalWrapper wrappedProposal;
    private Integer version;

    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal, ProposalWrapper wrappedProposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
        this.wrappedProposal = wrappedProposal;
    }

    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal, int version, ProposalWrapper wrappedProposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
        this.version = version;
        this.wrappedProposal = wrappedProposal;
    }


    public String getTitle() {
        return definition.getTitle();
    }

    public String getContent() throws PortalException, SystemException {
        ProposalAttribute attr = getSectionAttribute();

        if (attr == null) return null;
        else return attr.getStringValue().trim();
    }

    public String getContentFormatted() throws SystemException, PortalException, URISyntaxException {
        String content = getContent();
        if (content == null) {
            //default text if available
            return (definition!=null && !StringUtils.isEmpty(definition.getDefaultText())) ? definition.getDefaultText() : null;

        }
        Document contentDocument = Jsoup.parse(content.trim());
        contentDocument = ContentFilterHelper.addNoFollowToLinkTagsInDocument(contentDocument);

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

        	if (! (aTagElements.hasClass("utube") || aTagElements.text().toString().toLowerCase().startsWith("embed"))) {
        		// only links with "embed" text or "utube" class should be replaced by an iframe
        		continue;
        	}
        	String videoId = null;
        	if (curURL.indexOf("?") > 0 && curURL.indexOf("v=") > 0) {
        		// legacy url support
                List<NameValuePair> params = URLEncodedUtils.parse(curURL.substring(curURL.indexOf("?") + 1), Charset.defaultCharset());
                for (NameValuePair nvp : params) {
                    if (nvp.getName().equals("v")) {
                    	videoId = nvp.getValue();
                    }
                }
        	}
        	else {
        		final Pattern videoIdPattern = Pattern.compile("\\/(\\p{Alnum}{11})");
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

            // Eliminates wierd &nbsp; ASCII val 160 characters
            String text = pTagElements.text().replaceAll("[\\u00A0]", " ");
            String[] words = text.split("\\s");
            for (int i = 0; i < words.length; i++) {
                final String word = words[i];
                final Matcher matcher = pattern.matcher(word);

                // If a match is found create a new <a> tag
                if (matcher.find()) {
                    final String link = word.substring(matcher.start(), matcher.end());
                    ProposalWrapper wr = LinkUtils.getProposalLinks(link);

                    String elementName;
                    if (wr != null) {
                        elementName = wr.getName();
                    } else {
                        elementName = link;
                    }

                    String newLinkElementWithNoFollow = ContentFilterHelper.createLink(link, elementName);
                    // Replace exactly this word in the HTML code with leading and trailing spaces
                    if (words.length == 1) { // In this case there are no leading and trailing spaces in the html code
                        if (!html.contains("<"))
                            html = html.replaceFirst(Pattern.quote(word), newLinkElementWithNoFollow);
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
        if (StringUtils.isBlank(definition.getType())) {
            return PlanSectionTypeKeys.TEXT;
        }
        return PlanSectionTypeKeys.valueOf(definition.getType());
    }

    public Long getSectionDefinitionId() {
        return definition.getId();
    }

    public boolean isLocked() {
        return definition.getLocked();
    }

    public int getCharacterLimit() {
        return definition.getCharacterLimit();
    }

    public String getHelpText() {
        return definition.getHelpText();
    }

    public OntologyTerm getNumericValueAsOntologyTerm() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return OntologyTermLocalServiceUtil.getOntologyTerm(attr.getNumericValue());
    }

    public ProposalWrapper getNumericValueAsProposal() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return new ProposalWrapper(ProposalLocalServiceUtil.getProposal(attr.getNumericValue()));
    }

    public ProposalWrapper[] getStringValueAsProposalArray() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getStringValue() == null || attr.getStringValue().equals("")) {
            return null;
        }

        String props[] = attr.getStringValue().split(",");
        ProposalWrapper[] ret = new ProposalWrapper[props.length];
        for (int i = 0; i < props.length; i++) {
            try {
                ret[i] = new ProposalWrapper(ProposalLocalServiceUtil.getProposal(Long.parseLong(props[i])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public long getNumericValue() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return 0;
        }
        return attr.getNumericValue();
    }

    public String getStringValue() throws SystemException, PortalException {
        return getSectionAttribute().getStringValue();
    }

    public List<OntologyTerm> getFocusAreaTerms() throws PortalException, SystemException {
        if (definition.getFocusAreaId() <= 0) return null;

        FocusArea area = FocusAreaLocalServiceUtil.getFocusArea(definition.getFocusAreaId());

        return FocusAreaLocalServiceUtil.getTerms(area);
    }

    private ProposalAttribute getSectionAttribute() throws SystemException, PortalException {
        /*
        try {
            if (version != null && version > 0) {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), version, "SECTION", definition.getId());
            } else {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), "SECTION", definition.getId());
            }
        } catch (NoSuchProposalAttributeException linkElement) {
            return null;
        } catch (NoSuchProposalException linkElement) {
            return null;
        }
        */
        return this.wrappedProposal.getProposalAttributeUtil().getAttributeOrNull("SECTION", definition.getId());

    }
}
