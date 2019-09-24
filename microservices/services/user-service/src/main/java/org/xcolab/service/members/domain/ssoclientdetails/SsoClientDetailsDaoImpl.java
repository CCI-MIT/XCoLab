package org.xcolab.service.members.domain.ssoclientdetails;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.SsoClientDetailsWrapper;

import static org.xcolab.model.Tables.SSO_CLIENT_DETAILS;

@Repository
public class SsoClientDetailsDaoImpl implements SsoClientDetailsDao {

    private final DSLContext dslContext;

    @Autowired
    public SsoClientDetailsDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public SsoClientDetailsWrapper get(String id) {
        return dslContext.select().from(SSO_CLIENT_DETAILS)
                .where(SSO_CLIENT_DETAILS.ID.eq(id))
                .fetchOne().into(SsoClientDetailsWrapper.class);
    }
}
