package org.xcolab.service.members.domain.ssoclientdetails;

import org.xcolab.client.user.pojo.SsoClientDetails;

public interface SsoClientDetailsDao {

    SsoClientDetails get(String id);
}
