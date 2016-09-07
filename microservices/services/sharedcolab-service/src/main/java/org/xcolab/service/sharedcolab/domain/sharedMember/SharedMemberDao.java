package org.xcolab.service.sharedcolab.domain.sharedMember;

import org.xcolab.model.tables.pojos.SharedMember;

import java.util.Optional;

public interface SharedMemberDao {

    Long create(String screenName, String emailAddress, String colabOrigin);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    Optional<SharedMember> getByScreenNameAndEmail(String screenName, String email);
}
