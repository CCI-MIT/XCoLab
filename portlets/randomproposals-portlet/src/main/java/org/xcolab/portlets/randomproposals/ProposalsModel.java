package org.xcolab.portlets.randomproposals;

import java.util.List;

public class ProposalsModel {
	private final RandomProposalsPreferences _preferences;
	private final String _baseImagePath;

	private final List<ProposalWrapper> _proposals;
	
	public ProposalsModel(List<ProposalWrapper> proposals, RandomProposalsPreferences preferences,
			String baseImagePath){
		_proposals = proposals;
		_preferences = preferences;
		_baseImagePath = baseImagePath;
	}
	
	public RandomProposalsPreferences getPreferences() {
		return _preferences;
	}

	public String getBaseImagePath() {
		return _baseImagePath;
	}
	
	public List<ProposalWrapper> getProposals(){
		return _proposals;		
	}

	public boolean getCompact() {
		return _preferences.getCompact();
	}
}