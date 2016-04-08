package org.xcolab.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.contact.ContactDao;
import org.xcolab.model.tables.pojos.Contact_;

@Service
public class ContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public Contact_ getContact(Long contactId) {
        return this.contactDao.getContact(contactId);
    }
}
