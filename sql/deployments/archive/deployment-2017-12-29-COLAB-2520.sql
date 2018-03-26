update xcolab_ProposalAttribute set name = CONCAT(CONCAT(name, '_'), numericValue), numericValue = null where name in ('IMPACT_REDUCTION', 'IMPACT_ADOPTION_RATE');
