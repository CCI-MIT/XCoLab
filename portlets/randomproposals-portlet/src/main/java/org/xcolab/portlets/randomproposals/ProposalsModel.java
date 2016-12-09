package org.xcolab.portlets.randomproposals;

import org.xcolab.client.proposals.pojo.Proposal;

import java.util.List;

public class ProposalsModel {
	private final RandomProposalsPreferences _preferences;
	private final String _baseImagePath;

	private final List<Proposal> _proposals;
	
	public ProposalsModel(List<Proposal> proposals, RandomProposalsPreferences preferences,
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
	
	public List<Proposal> getProposals(){
		return _proposals;		
	}

	public boolean getCompact() {
		return _preferences.getCompact();
	}
}