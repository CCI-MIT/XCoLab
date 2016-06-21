package org.xcolab.interfaces;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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
        private final Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };


    TabActivityCountAlgorithm commentsCount = new TabActivityCountAlgorithm() {
        
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
        private final Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };
    
    TabActivityCountAlgorithm membersCount = new TabActivityCountAlgorithm() {
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return 0;
        }
        private final Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };

    TabActivityCountAlgorithm flagCount = new TabActivityCountAlgorithm() {
        @Override
        public int getActivityCount(TabContext context, PortletRequest request) {
            return FlaggingClient.countReports(null, null, null, null, null);
        }
        private final Log _log = LogFactoryUtil.getLog(TabActivityCountAlgorithm.class);
    };
}
