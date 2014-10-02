select distinct 
	c.`ContestShortName`,
	u.screenName,
	u.firstName,
	u.lastName,
	u.emailAddress,
	p.proposalId,
	(SELECT stringValue AS name
	 FROM xcolab_prod.xcolab_ProposalAttribute
	 WHERE
		proposalId = p.proposalId AND
		name = 'NAME' ORDER BY version DESC LIMIT 1
	) AS proposalName,
	CONCAT('http://climatecolab.org/web/guest/plans/-/plans/contestId/', c.`ContestPK`, '/planId/', p.`proposalId`) as link
from
	User_ u
	inner join `Users_Groups` ug on u.`userId` = ug.`userId`
	inner join `xcolab_Proposal` p on ug.`groupId` = p.`groupId`
	inner join `xcolab_Proposal2Phase` p2p on p.`proposalId` = p2p.`proposalId`
	inner join `xcolab_ContestPhase` cp on p2p.`contestPhaseId` = cp.`ContestPhasePK`
	inner join `xcolab_Contest` c on c.`ContestPK` = cp.`ContestPK`
where 
	cp.ContestPhaseType = 15 AND
	c.`ContestPK` IN (1300201,
		1300210,
		1300203,
		1300205,
		1300204,
		1300403,
		1300404,
		1300206,
		1300208,
		1300401,
		1300402,
		1300501,
		1300801,
		1300209,
		1300207,
		1300202
	)