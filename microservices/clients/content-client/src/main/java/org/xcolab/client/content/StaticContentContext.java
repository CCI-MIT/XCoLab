package org.xcolab.client.content;

import org.springframework.util.Assert;

public class StaticContentContext {

    private static IContentClient contentClient;
    private static IFileClient fileClient;

    public static void setClients(IContentClient contentClient, IFileClient fileClient) {
        Assert.notNull(contentClient, "contentClient must not be null!");
        Assert.notNull(fileClient, "fileClient must not be null!");
        StaticContentContext.contentClient = contentClient;
        StaticContentContext.fileClient = fileClient;
    }

    public static IContentClient getContentClient() {
        return contentClient;
    }

    public static IFileClient getFileClient() {
        return fileClient;
    }
}
