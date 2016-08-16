package org.xcolab.service.members.domain.loginlog;

import org.xcolab.model.tables.pojos.LoginLog;

import java.util.Optional;

public interface LoginLogDao {

    Optional<LoginLog> get(long loginLogId);
    LoginLog create(LoginLog loginLog);
}
