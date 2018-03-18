package org.xcolab.util.enums.promotion;

public class JudgingSystemActions {
    public enum AdvanceDecision {
        NO_DECISION(0, "No decision made yet"),
        DONT_MOVE_ON(1, "Do Not Advance"),
        MOVE_ON(2, "Advance");

        int attributeValue;
        String description;

        AdvanceDecision(int attributeValue, String description) {
            this.attributeValue = attributeValue;
            this.description = description;
        }

        public static AdvanceDecision fromInt(Integer value) {
            for (AdvanceDecision a : values()) {
                if(a.attributeValue == value) {
                    return a;
                }
            }

            return NO_DECISION;
        }

        public int getAttributeValue(){
            return this.attributeValue;
        }

        public String getDescription(){
            return description;
        }
    }

    public enum FellowAction {
        NO_DECISION(0, "No decision made yet", false, false),
        INCOMPLETE(1, "Do Not Advance: incomplete", true, false),
        OFFTOPIC(2, "Do Not Advance: off-topic", true, false),
        NOT_ADVANCE_OTHER(4, "Do Not Advance: other", true, false),
        PASS_TO_JUDGES(3, "Advance", false, true);

        int attributeValue;
        String description;
        boolean commentEnabled;
        boolean selectJudgesEnabled;

        FellowAction(int attributeValue, String description, boolean commentEnabled,
                boolean selectJudgesEnabled) {
            this.attributeValue = attributeValue;
            this.description = description;
            this.commentEnabled = commentEnabled;
            this.selectJudgesEnabled = selectJudgesEnabled;
        }

        public static FellowAction fromInt(Integer value) {
            for(FellowAction a : values()) {
                if(a.attributeValue == value) {
                    return a;
                }
            }

            return NO_DECISION;
        }

        public int getAttributeValue(){
            return this.attributeValue;
        }

        public String getDescription(){
            return description;
        }

        public boolean getCommentEnabled() {
            return commentEnabled;
        }

        public boolean getSelectJudgesEnabled() {
            return selectJudgesEnabled;
        }

        public boolean isActionProhibitingAdvancing() {
        return this != FellowAction.PASS_TO_JUDGES;
        }
    }

    public enum JudgeReviewStatus {

        NOT_RESPONSIBLE(0, "Judge is not responsible for reviewing"),
        NOT_DONE(1, "Judge is responsible but has not finished yet"),
        DONE(2, "Judge has finished the review");

        private final int statusValue;
        private final String statusDescription;

        JudgeReviewStatus(int status, String statusDescription) {
            this.statusValue = status;
            this.statusDescription = statusDescription;
        }

        public int getStatusValue() {
            return statusValue;
        }

        public String getStatusDescription() {
            return statusDescription;
        }
    }
}
