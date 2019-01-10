package org.xcolab.client.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.List;

@FeignClient("xcolab-admin-service")
public interface EmailTemplateClient {

    @GetMapping("/emailTemplates")
    List<IEmailTemplate> listEmailTemplates();

    @GetMapping("/emailTemplates/{emailTemplateType}")
    IEmailTemplate getEmailTemplates(@PathVariable("emailTemplateType") String emailTemplateType);

    @PutMapping("/emailTemplates")
    boolean updateEmailTemplates(@RequestBody IEmailTemplate emailTemplate);

    @PostMapping("/emailTemplates")
    IEmailTemplate createEmailTemplates(@RequestBody IEmailTemplate emailTemplate);
}
