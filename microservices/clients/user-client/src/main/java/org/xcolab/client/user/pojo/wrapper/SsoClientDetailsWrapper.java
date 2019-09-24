package org.xcolab.client.user.pojo.wrapper;

public class SsoClientDetailsWrapper extends org.xcolab.client.user.pojo.tables.pojos.SsoClientDetails {



    public SsoClientDetailsWrapper() {}

    public SsoClientDetailsWrapper(SsoClientDetailsWrapper value) {
        this.setId(value.getId());
        this.setSecret(value.getSecret());
        this.setScope(value.getScope());
        this.setRegisteredredirecturi(value.getRegisteredredirecturi());

    }


}
