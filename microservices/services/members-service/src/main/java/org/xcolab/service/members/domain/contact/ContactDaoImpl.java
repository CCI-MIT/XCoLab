package org.xcolab.service.members.domain.contact;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Contact_;

import static org.xcolab.model.Tables.CONTACT_;

@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    private DSLContext dslContext;

    public Contact_ getContact(Long contactId) {

        return this.dslContext.select()
                .from(CONTACT_)
                .where(CONTACT_.CONTACT_ID.equal(contactId)).fetchAny().into(Contact_.class);

    }
}
