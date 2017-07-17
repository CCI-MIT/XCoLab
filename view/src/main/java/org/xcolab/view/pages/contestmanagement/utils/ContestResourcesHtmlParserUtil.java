package org.xcolab.view.pages.contestmanagement.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.entity.utils.TemplateReplacementUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.SectionDefinitionWrapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ContestResourcesHtmlParserUtil {

    private final static String SECTION_DELIMITER_TAG = "h2";
    private final ContestType contestType;

    private HashMap<String, String> baseSections;
    private HashMap<String, String> additionalSections;
    private List<String> ignoreSectionTitles;

    public ContestResourcesHtmlParserUtil(ContestType contestType) {
        this.contestType = contestType;
    }

    public static String getSectionAsHtmlString(SectionDefinitionWrapper sectionDefinitionWrapper) {
        return getSectionAsHtmlString(sectionDefinitionWrapper.getTitle(),
                sectionDefinitionWrapper.getContent());
    }

    private static String getSectionAsHtmlString(String title, String content) {
        Document doc = Jsoup.parse("");
        Element bodyElement = doc.body();
        Element titleElement = createElementWithTextContent("h2", title);
        Element contentElement = Jsoup.parse(content).body();
        bodyElement.appendChild(titleElement);
        bodyElement.appendChild(contentElement);
        return bodyElement.toString();
    }

    private static Element createElementWithTextContent(String elementType, String textContent) {
        Attributes attrs = new Attributes();
        Element element = new Element(Tag.valueOf(elementType), "", attrs);
        element.appendText(textContent);
        return element;

    }

    public static String getOverviewSectionAsHtmlString(HashMap<String, String> overviewContent) {
        StringBuilder overviewSectionAsHtmlString = new StringBuilder();
        Element sectionTitle = createElementWithTextContent(SECTION_DELIMITER_TAG, "Overview");
        overviewSectionAsHtmlString.append(sectionTitle);
        overviewSectionAsHtmlString.append("<p>");
        for (String overviewSectionContentLine : overviewContent.keySet()) {
            Element head = createElementWithTextContent("strong", overviewSectionContentLine);
            overviewSectionAsHtmlString.append(head);
            overviewSectionAsHtmlString
                    .append(Jsoup.parse(overviewContent.get(overviewSectionContentLine)).body());
            overviewSectionAsHtmlString.append("<br/>");
        }
        overviewSectionAsHtmlString.append("</p>");
        return overviewSectionAsHtmlString.toString();
    }

    public void setBaseSectionTitles(String[] baseSectionTitles) {
        createSections(baseSectionTitles);
    }

    private void createSections(String[] baseSectionTitles) {
        createBaseSections(baseSectionTitles);
        additionalSections = new LinkedHashMap<>();
    }

    private void createBaseSections(String[] baseSectionTitles) {
        baseSections = new LinkedHashMap<>();
        for (String baseSectionTitle : baseSectionTitles) {
            baseSections.put(TemplateReplacementUtil
                    .replaceContestTypeStrings(baseSectionTitle, contestType), "");
        }
    }

    public void setIgnoreSectionTitles(List<String> ignoreSectionTitles) {
        this.ignoreSectionTitles = ignoreSectionTitles;
    }

    public void parseDocument(String htmlString) {
        Document htmlDocument = Jsoup.parse(htmlString);
        Elements sections = htmlDocument.getElementsByTag(SECTION_DELIMITER_TAG);
        for (Element section : sections) {
            String sectionTitle = removeWhitespaces(section.text().trim());
            String sectionContent = extractSectionContent(section);
            if (!sectionTitle.isEmpty() && !ignoreSectionTitles.contains(sectionTitle)) {
                if (baseSections.containsKey(sectionTitle.trim())) {
                    baseSections.put(sectionTitle.trim(), sectionContent);
                } else {
                    additionalSections.put(sectionTitle, sectionContent);
                }
            }
        }
    }

    private static String removeWhitespaces(String string) {
        return string.replace("\u00a0", "");
    }

    private static String extractSectionContent(Element section) {
        StringBuilder parsedSectionContent = new StringBuilder();
        while (section.nextElementSibling() != null) {
            Element nextSibling = section.nextElementSibling();
            if (nextSibling.tagName().equals(SECTION_DELIMITER_TAG)) {
                break;
            }
            parsedSectionContent.append(nextSibling.toString());
            section = nextSibling;
        }
        return parsedSectionContent.toString();
    }

    public HashMap<String, String> getBaseSections() {
        return baseSections;
    }

    public HashMap<String, String> getAdditionalSections() {
        return additionalSections;
    }

}
