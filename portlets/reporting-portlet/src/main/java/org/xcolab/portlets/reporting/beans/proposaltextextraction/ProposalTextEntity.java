package org.xcolab.portlets.reporting.beans.proposaltextextraction;

/**
* @author pdeboer
*         First created on 01/07/14 at 10:46
*/
public class ProposalTextEntity {
    private String url;
    private Long id;
    private String content = "";
    private int htmlElementCount;
    private boolean isFinalist;

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

    public boolean isFinalist() {
        return isFinalist;
    }

    public void setFinalist(boolean isFinalist) {
        this.isFinalist = isFinalist;
    }
}
