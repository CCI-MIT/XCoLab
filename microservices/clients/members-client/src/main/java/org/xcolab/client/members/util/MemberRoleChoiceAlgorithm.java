package org.xcolab.client.members.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This interface is used for member role choice algorithm implementations to choose the highest member role depending on the context
 * (i.e. highest role in context of members-portlet or for the proposal impact tab)
 */
public abstract class MemberRoleChoiceAlgorithm {
    private static final Logger _log = LoggerFactory.getLogger(MemberRoleChoiceAlgorithm.class);

    public final static MemberRoleChoiceAlgorithm proposalImpactTabAlgorithm = new MemberRoleChoiceAlgorithm() {

        @Override
        public MemberRole getHighestMemberRoleForUser(Member member) {
            Map<Long, Boolean> roleIdMap = getRoleIdMap(member);

            for (MemberRole memberRole : getPrioritizedMemberRoles()) {
                if (roleIdMap.get(memberRole.getRoleId()) != null) {
                    return memberRole;
                }
            }

            _log.error("No member role could be found for proposal impact tab member selection "
                            + "algorithm for user with ID {}! Returning Member...", member.getId());
            return MemberRole.GUEST;
        }

        @Override
        public MemberRole[] getPrioritizedMemberRoles() {
            return new MemberRole[]{MemberRole.IMPACT_ASSESSMENT_FELLOW, MemberRole.STAFF, MemberRole.FELLOW, MemberRole.MEMBER, MemberRole.GUEST};
        }

        @Override
        public String getMemberRoleDescription(MemberRole memberRole) {
            String memberDescription = getMemberRoleToMemberDescriptionMap().get(memberRole);
            if (memberDescription != null) {
                return memberDescription;
            }

            // Return the first default description
            return memberRole.getRoleNames()[0];
        }

        @Override
        public Map<MemberRole, String> getMemberRoleToMemberDescriptionMap() {
            Map<MemberRole, String> memberRoleToMemberDescriptionMap = new HashMap<>();
            memberRoleToMemberDescriptionMap.put(MemberRole.MEMBER, "Proposal author");

            return memberRoleToMemberDescriptionMap;
        }
    };

    /**
     * Returns the role of the user with highest priority, where the priority is determined by the algorithm's implementation
     * @param member      The user in question
     * @return          A member role object of type with the highest priority
     */
    public abstract MemberRole getHighestMemberRoleForUser(Member member);

    public abstract MemberRole[] getPrioritizedMemberRoles();

    /**
     * Returns the preferred description string for a given MemberRole. Returns the first general description
     * (according to MemberRole class) of the member role if none specific is present
     */
    public abstract String getMemberRoleDescription(MemberRole memberRole);
    public abstract Map<MemberRole, String> getMemberRoleToMemberDescriptionMap();

    // Helper methods
    public Map<Long, Boolean> getRoleIdMap(Member member) {
        List<Role> userRoles = MembersClient.getMemberRoles(member.getId());
        Map<Long, Boolean> roleIdMap = new HashMap<>(userRoles.size());
        for (Role role : userRoles) {
            roleIdMap.put(role.getId(), true);
        }

        return roleIdMap;
    }

    public String getUserRoleDescription(Member user) {
        return getMemberRoleDescription(getHighestMemberRoleForUser(user));
    }
}
