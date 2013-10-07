package com.ext.portlet;

/**
 * @author pdeboer
 *         First created on 10/7/13 at 7:37 PM
 */
public class JudgingSystemActions {
    public enum JudgeAction {
        NO_DECISION(0, "no decision"), DONT_MOVE_ON(1, "don't move on"), MOVE_ON(2, "move on");

        int attributeValue;
        String description;

        private JudgeAction(int attributeValue, String description) {
            this.attributeValue = attributeValue;
            this.description = description;
        }

        public static JudgeAction fromInt(Integer value) {
            for(JudgeAction a : values()) {
                if(a.attributeValue == value) return a;
            }

            return NO_DECISION;
        }
    }

    public enum FellowAction {
        NO_DECISION(0, "no decision"), DONT_MOVE_ON(1, "don't move on"), MOVE_ON(2, "move on");

        int attributeValue;
        String description;

        private FellowAction(int attributeValue, String description) {
            this.attributeValue = attributeValue;
            this.description = description;
        }

        public static FellowAction fromInt(Integer value) {
            for(FellowAction a : values()) {
                if(a.attributeValue == value) return a;
            }

            return NO_DECISION;
        }
    }
}
