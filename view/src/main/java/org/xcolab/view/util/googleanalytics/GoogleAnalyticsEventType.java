package org.xcolab.view.util.googleanalytics;

public enum GoogleAnalyticsEventType {

    REGISTRATION_FORM("Registration", "registered", "From platform form"),
    REGISTRATION_SSO_FACEBOOK("Registration", "registered", "From Facebook SSO"),
    REGISTRATION_SSO_GOOGLE("Registration", "registered", "From Google SSO"),
    REGISTRATION_COMPLETE_PROFILE("Registration", "complete_profile", "complete form profile"),
    CONTEST_ENTRY_CREATION("ContestEntry", "created_contest_entry", "created new contest entry"),
    COMMENT_CONTEST("Comment", "comment_contest", "Added a comment to a contest"),
    CONTEST_ENTRY_VOTE("Vote", "contest_entry_vote", "Added a comment to a contest"),
    CONTEST_ENTRY_SUPPORT("Support", "support_contest_entry", "Support a contest entry");


    private final String eventCategory;
    private final String eventAction;
    private final String eventLabel;


    GoogleAnalyticsEventType(String eventCategory, String eventAction, String eventLabel) {
        this.eventAction = eventAction;
        this.eventCategory = eventCategory;
        this.eventLabel = eventLabel;
    }

    public String getEventCategory() {
        return eventCategory;
    }


    public String getEventAction() {
        return eventAction;
    }


    public String getEventLabel() {
        return eventLabel;
    }


}
