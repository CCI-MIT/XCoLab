package org.xcolab.portlets.contests;

import java.io.Serializable;

import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class ContestPhaseAutopromotionTask implements MessageListener, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log _log = LogFactoryUtil.getLog(ContestPhaseAutopromotionTask.class);
	private static Boolean working = false;

    @Override
    public void receive(Message message) throws MessageListenerException {
        if (working) return;
        synchronized(working) {
            if (working) return;
            working = true;
            try {
                ContestPhaseLocalServiceUtil.autoPromoteProposals();
            } catch (PortalException | SystemException e) {
                _log.error(e, e);
            }
            working = false;
        }
        
    }

}
