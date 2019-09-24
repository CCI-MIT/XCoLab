package org.xcolab.service.members.domain.loginlog;

import java.util.Optional;
import org.xcolab.client.user.pojo.tables.pojos.LoginLog;

public interface LoginLogDao {

    Optional<LoginLog> get(long loginLogId);
    LoginLog create(LoginLog loginLog);
}
