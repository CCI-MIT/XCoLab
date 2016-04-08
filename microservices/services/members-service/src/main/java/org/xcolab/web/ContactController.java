package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.Contact_;
import org.xcolab.service.contact.ContactService;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contact/{contactId}", method = RequestMethod.GET)
    public Contact_ getMemberCategory(@PathVariable("contactId") Long contactId) {
        return this.contactService.getContact(contactId);
    }

}
