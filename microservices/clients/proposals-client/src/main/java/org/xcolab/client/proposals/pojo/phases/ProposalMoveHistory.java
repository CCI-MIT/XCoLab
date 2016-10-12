package org.xcolab.client.proposals.pojo.phases;

import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class ProposalMoveHistory extends AbstractProposalMoveHistory {

    public ProposalMoveHistory() {}

    public ProposalMoveHistory(ProposalMoveHistory value) {
        super(value);
    }

    public ProposalMoveHistory(
            Long id_,
            Long sourceproposalid,
            Long sourcecontestid,
            Long sourcephaseid,
            Long targetproposalid,
            Long targetcontestid,
            Long targetphaseid,
            Long movinguserid,
            Timestamp movedate,
            String movetype
    ) {
        super(id_, sourceproposalid, sourcecontestid, sourcephaseid, targetproposalid,
                targetcontestid, targetphaseid, movinguserid, movedate, movetype);
    }

    public ProposalMoveHistory(AbstractProposalMoveHistory abstractProposalMoveHistory,
            RestService restService) {
        super(abstractProposalMoveHistory);
    }
}
