-- set null version create dates to the best guess

-- first, update one proposal that is missing an update date
-- (date guessed from adjacent attribute updates)
UPDATE xcolab_Proposal set updatedDate = '2017-11-05 11:16:35'
  where proposalId = 1334219 and updatedDate is null;

-- then, set all of the newly created versions without dates to
-- the last modified date of their proposal
update xcolab_ProposalVersion pv set createDate = (select p.updatedDate from xcolab_Proposal p
  where p.proposalId = pv.proposalId) where pv.createDate is NULL;
