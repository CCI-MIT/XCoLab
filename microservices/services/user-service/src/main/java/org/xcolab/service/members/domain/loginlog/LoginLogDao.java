package org.xcolab.service.members.domain.loginlog;

import org.xcolab.client.user.pojo.ILoginLog;

import java.util.Optional;

public interface LoginLogDao {

    Optional<ILoginLog> get(long loginLogId);
    ILoginLog create(ILoginLog loginLog);
}
