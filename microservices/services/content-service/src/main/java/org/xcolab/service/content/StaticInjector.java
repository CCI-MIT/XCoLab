package org.xcolab.service.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.StaticContentContext;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(IContentClient contentClient, IFileClient fileClient) {
        StaticContentContext.setClients(contentClient, fileClient);
    }
}
