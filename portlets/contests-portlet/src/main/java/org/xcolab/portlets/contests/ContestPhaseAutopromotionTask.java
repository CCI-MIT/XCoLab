package org.xcolab.portlets.contests;

import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class ContestPhaseAutopromotionTask implements MessageListener {
    private Log _log = LogFactoryUtil.getLog(ContestPhaseAutopromotionTask.class);

    @Override
    public void receive(Message message) throws MessageListenerException {
        
        try {
            ContestPhaseLocalServiceUtil.autoPromoteProposals();
        } catch (PortalException | SystemException e) {
            _log.error(e, e);
        }
        
    }

}
