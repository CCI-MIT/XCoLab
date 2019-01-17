package org.xcolab.view.widgets.proposals;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Proposal;

import java.util.List;

public class ProposalsModel {

    private final RandomProposalsPreferences _preferences;

    private final List<Proposal> _proposals;

    public ProposalsModel(List<Proposal> proposals, RandomProposalsPreferences preferences) {
        _proposals = proposals;
        _preferences = preferences;
    }

    public RandomProposalsPreferences getPreferences() {
        return _preferences;
    }

    public String getBaseImagePath() {
        return PlatformAttributeKey.COLAB_URL.get() + "/proposal/";
    }

    public List<Proposal> getProposals() {
        return _proposals;
    }

    public boolean getCompact() {
        return _preferences.getCompact();
    }
}
