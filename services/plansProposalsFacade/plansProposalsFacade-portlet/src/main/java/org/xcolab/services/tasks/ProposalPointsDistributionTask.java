package org.xcolab.services.tasks;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

/**
 * This scheduler automatically distributes points to all contests which have points enabled
 * and which are still active.
 *
 * Created by Klemens Mang on 25/09/15.
 */
public class ProposalPointsDistributionTask implements MessageListener {

    private Log _log = LogFactoryUtil
            .getLog(ProposalPointsDistributionTask.class);
    private static final Object mutex = new Object();
    private boolean isExecuting = false;

    @Override
    public void receive(Message message) throws MessageListenerException {
        // only one thread at at time is allowed to be doing tasks
        synchronized (mutex) {
            if (isExecuting) {
                return;
            }
            isExecuting = true;
        }
//        TODO: are we still using this mechanism?
//        try {
//            for (Contest pointsContest : ContestLocalServiceUtil.getPointsEnabledContests(true)) {
//                try {
//                    PointsLocalServiceUtil.distributePoints(pointsContest.getContestPK());
//                } catch (Throwable e) {
//                    _log.error("Error while distributing points for contest with ID " + pointsContest.getContestPK(), e);
//                }
//            }
//        } catch (SystemException e) {
//            _log.error("Could not get points enabled contests.", e);
//        }

        synchronized (mutex) {
            isExecuting = false;
        }
    }
}

