package com.ext.portlet;

/**
 * @author pdeboer
 *         First created on 10/7/13 at 7:37 PM
 */
public class JudgingSystemActions {
    public enum AdvanceDecision {
        NO_DECISION(0, "no decision"), DONT_MOVE_ON(1, "don't move on"), MOVE_ON(2, "move on");

        int attributeValue;
        String description;

        private AdvanceDecision(int attributeValue, String description) {
            this.attributeValue = attributeValue;
            this.description = description;
        }

        public static AdvanceDecision fromInt(Integer value) {
            for(AdvanceDecision a : values()) {
                if(a.attributeValue == value) return a;
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
        NO_DECISION(0, "No decision", false, false),
        INCOMPLETE(1, "Incomplete", true, false),
        OFFTOPIC(2, "Off-topic", true, false),
        PASS_TO_JUDGES(3, "Advance to judges", false, true);

        int attributeValue;
        String description;
        boolean commentEnabled;
        boolean selectJudgesEnabled;

        private FellowAction(int attributeValue, String description, boolean commentEnabled, boolean selectJudgesEnabled) {
            this.attributeValue = attributeValue;
            this.description = description;
            this.commentEnabled = commentEnabled;
            this.selectJudgesEnabled = selectJudgesEnabled;
        }

        public static FellowAction fromInt(Integer value) {
            for(FellowAction a : values()) {
                if(a.attributeValue == value) return a;
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
    }

    public enum JudgeReviewStatus {
        NOT_RESPONSIBLE(0, "Judge is not responsible for reviewing"),
        NOT_DONE(1, "Judge is responsible but has not finished yet"),
        DONE(2, "Judge has finished the review");

        private int statusValue;
        private String statusDescription;

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
