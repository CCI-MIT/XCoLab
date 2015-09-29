package com.ext.portlet.service.impl.helper.points;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Klemens Mang on 21/09/15.
 */
public class DistributionConfiguration {
    //the teamMemberShares indizes are the indizes in the proposalTeamMembers field
    public Map<Integer, Double> teamMemberShares = new HashMap<Integer, Double>();
    //in additionalShares, the indizes are the indizes of the users field
    public Map<Integer, Double> additionalShares = new HashMap<Integer, Double>();
}