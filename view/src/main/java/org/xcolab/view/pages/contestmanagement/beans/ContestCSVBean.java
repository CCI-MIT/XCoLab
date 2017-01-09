package org.xcolab.view.pages.contestmanagement.beans;

public class ContestCSVBean {

    private String contestShortName;

    private String contestQuestion;

    private String ontologyTerms;


    public String getContestShortName() {
        return contestShortName;
    }

    public void setContestShortName(String contestShortName) {
        this.contestShortName = contestShortName;
    }

    public String getContestQuestion() {
        return contestQuestion;
    }

    public void setContestQuestion(String contestQuestion) {
        this.contestQuestion = contestQuestion;
    }

    public String getOntologyTerms() {
        return ontologyTerms;
    }

    public void setOntologyTerms(String ontologyTerms) {
        this.ontologyTerms = ontologyTerms;
    }
}
