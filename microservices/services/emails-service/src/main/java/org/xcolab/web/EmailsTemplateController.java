package org.xcolab.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailsTemplateController {
    @RequestMapping("/emailTemplates")
    public List<Object> listEmailTemplates() {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.GET)
    public Object getEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.POST)
    public Object updateEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/", method = RequestMethod.PUT)
    public Object createEmailTemplates() {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.DELETE)
    public Object deleteEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return null;
    }
}
