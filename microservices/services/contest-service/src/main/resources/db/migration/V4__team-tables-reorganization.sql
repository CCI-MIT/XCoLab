CREATE TABLE IF NOT EXISTS contest__proposal_team_member
(
    proposal_id bigint(20) NOT NULL,
    user_id bigint(20) NOT NULL,
    CONSTRAINT contest__proposal_team_member_pk PRIMARY KEY (proposal_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX contest__proposal_team_member_userId_index ON contest__proposal_team_member (user_id);


-- Workaround to make this work in the build system, where Users_Groups doesn't exist:
create table if not exists Users_Groups(groupId bigint not null, userId bigint not null);

insert into contest__proposal_team_member (proposal_id, user_id)
  select proposal.proposalId, ug.userId from xcolab_Proposal proposal
    join Users_Groups ug on ug.groupId = proposal.groupId;

ALTER TABLE MembershipRequest ADD proposal_id bigint(20) NULL;
ALTER TABLE MembershipRequest
  MODIFY COLUMN proposal_id bigint(20) AFTER createDate;

update MembershipRequest mr set proposal_id = (select proposalId from xcolab_Proposal proposal where proposal.groupId = mr.groupId);
