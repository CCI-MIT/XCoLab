package org.xcolab.interfaces;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.PortletRequest;

public interface TabActivityCountAlgorithm {
    int getActivityCount(TabContext context, PortletRequest request);
    
    public static TabActivityCountAlgorithm alwaysZero = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
    };


    public static TabActivityCountAlgorithm discussionCommentsCount = new TabActivityCountAlgorithm() {

        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
        private Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };


    public static TabActivityCountAlgorithm commentsCount = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
        private Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };
    
    public static TabActivityCountAlgorithm membersCount = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
        private Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };
}
