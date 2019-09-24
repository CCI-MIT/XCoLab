package org.xcolab.service.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.StaticContentContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(IContentClient contentClient, IFileClient fileClient) {
        StaticContentContext.setClients(contentClient, fileClient);
    }
}
