package org.xcolab.service.sharedcolab.domain.sharedMember;

import org.xcolab.model.tables.pojos.SharedMember;

import java.sql.Timestamp;

public interface SharedMemberDao {

    Long create(String screenName, String emailAddress, Timestamp createDate);

    boolean isScreenNameTaken(String screenName);

    boolean isEmailUsed(String email);

    SharedMember getByScreenNameAndEmail(String screenName, String email);
}
