package org.xcolab.service.members.domain.ssoclientdetails;

import org.xcolab.client.user.pojo.wrapper.SsoClientDetailsWrapper;

public interface SsoClientDetailsDao {

    SsoClientDetailsWrapper get(String id);
}
