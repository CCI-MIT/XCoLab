package org.xcolab.services.tasks;

import java.io.Serializable;
import java.util.Calendar;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;

public class ProposalReindexingTask implements MessageListener, Serializable{

	private final static int REINDEX_PROPOSALS_MODIFIED_IN_LAST_X_SECONDS = 70;
	private final static Log _log = LogFactoryUtil.getLog(ProposalReindexingTask.class);
	@Override
	public void receive(Message message) throws MessageListenerException {
		final Indexer indexer = IndexerRegistryUtil.getIndexer(Proposal.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, - REINDEX_PROPOSALS_MODIFIED_IN_LAST_X_SECONDS);
		try {
			/*for (Proposal proposal: ProposalLocalServiceUtil.getModifiedAfter(calendar.getTime())) {
				_log.info("Reindexing proposal: " + proposal.getProposalId());
				indexer.reindex(proposal);
			}
			*/
		}
		catch (Exception e) {
			_log.error("Can't reindex proposal", e);
		}
		
	}

}
