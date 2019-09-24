package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffUserWrapper extends org.xcolab.client.user.pojo.tables.pojos.StaffMember {

    public StaffUserWrapper() {
    }

}
