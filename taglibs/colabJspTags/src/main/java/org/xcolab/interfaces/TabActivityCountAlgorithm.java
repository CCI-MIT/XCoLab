package org.xcolab.interfaces;

import org.xcolab.client.flagging.FlaggingClient;

import javax.portlet.PortletRequest;

public interface TabActivityCountAlgorithm {
    int getActivityCount(TabContext context, PortletRequest request);
    
    TabActivityCountAlgorithm alwaysZero = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
    };


    TabActivityCountAlgorithm discussionCommentsCount = new TabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
    };


    TabActivityCountAlgorithm commentsCount = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
    };
    
    TabActivityCountAlgorithm membersCount = new TabActivityCountAlgorithm() {
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
    };

    TabActivityCountAlgorithm flagCount = new TabActivityCountAlgorithm() {
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return FlaggingClient.countReports(null, null, null, null, null);
        }
    };
}
