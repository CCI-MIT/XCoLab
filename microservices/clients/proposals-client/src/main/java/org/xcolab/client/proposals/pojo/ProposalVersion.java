package org.xcolab.client.proposals.pojo;

import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class ProposalVersion extends AbstractProposalVersion {

    public ProposalVersion() {}

    public ProposalVersion(ProposalVersion value) {
        super(value);
    }

    public ProposalVersion(
            Long proposalid,
            Integer version,
            Long authorid,
            Timestamp createdate,
            String updatetype,
            Long updateadditionalid
    ) {
        super(proposalid, version, authorid, createdate, updatetype, updateadditionalid);
    }

    public ProposalVersion(AbstractProposalVersion abstractProposalVersion,
            RestService restService) {
        super(abstractProposalVersion);
    }
}
