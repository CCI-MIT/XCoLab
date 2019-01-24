package org.xcolab.service.members.domain.ssoclientdetails;

import org.xcolab.client.user.pojo.ISsoClientDetails;

public interface SsoClientDetailsDao {

    ISsoClientDetails get(String id);
}
