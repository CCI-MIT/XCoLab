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
public interface IEmailTemplateClient {

    @GetMapping("/emailTemplates")
    List<IEmailTemplate> listEmailTemplates();

    @GetMapping("/emailTemplates/{emailTemplateType}")
    IEmailTemplate getEmailTemplate(@PathVariable("emailTemplateType") String emailTemplateType);

    @PutMapping("/emailTemplates")
    boolean updateEmailTemplate(@RequestBody IEmailTemplate emailTemplate);

    @PostMapping("/emailTemplates")
    IEmailTemplate createEmailTemplate(@RequestBody IEmailTemplate emailTemplate);
}
