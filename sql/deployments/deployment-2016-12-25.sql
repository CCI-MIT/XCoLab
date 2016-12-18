-- COLAB-1459
INSERT INTO xcolab_MemberCategory (roleId, displayName, categoryName, sortOrder, showInList, imageName, description) VALUES (10118, 'Admin', 'Admin', 1, 0, 'icon_mem-staff', 'Site admins - non-visible role');

-- COLAB-1362
update xcolab_ProposalMoveHistory set sourcePhaseId = null where sourcePhaseId = 0 or sourcePhaseId = 20;
