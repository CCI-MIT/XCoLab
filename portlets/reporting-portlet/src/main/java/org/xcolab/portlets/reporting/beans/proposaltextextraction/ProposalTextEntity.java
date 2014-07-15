package org.xcolab.portlets.reporting.beans.proposaltextextraction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
* @author pdeboer
*         First created on 01/07/14 at 10:46
*/
public class ProposalTextEntity {

    private String url;
    private Long id;
    private String content = "";
    private int htmlElementCount;

    private ProposalRank rank;
    private String htmlContent ="";
    private boolean proposalWinsJudgesChoice = false;
    private boolean proposalWinsPopularChoice = false;

    public int getHtmlElementCount() {
        return htmlElementCount;
    }

    public void setHtmlElementCount(int htmlElementCount) {
        this.htmlElementCount = htmlElementCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProposalRank getRank() {
        return rank;
    }

    public void setRank(ProposalRank rank) {
        this.rank = rank;
    }

    public boolean isProposalWinsJudgesChoice() {
        return proposalWinsJudgesChoice;
    }

    public void setProposalWinsJudgesChoice(boolean proposalWinsJudgesChoice) {
        this.proposalWinsJudgesChoice = proposalWinsJudgesChoice;
    }

    public boolean isProposalWinsPopularChoice() {
        return proposalWinsPopularChoice;
    }

    public void setProposalWinsPopularChoice(boolean proposalWinsPopularChoice) {
        this.proposalWinsPopularChoice = proposalWinsPopularChoice;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void appendHtml(String html) {
        Document document = Jsoup.parse(html);
        String tmpContent = document.text();
        if (tmpContent == null || tmpContent.equals(""))
            tmpContent = htmlContent;
        content += tmpContent+"\n";

        htmlContent += html+"\n<br/>\n";
    }
}
