package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.ContentsClient;
import org.xcolab.client.content.FilesClient;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.tags.LoadContentArticleTag;

@Component
public class StaticInjector implements ApplicationRunner {

    @Autowired
    private FilesClient filesClient;

    @Autowired
    private ContentsClient contentsClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        ImageUploadUtils.setFilesClient(filesClient);
        LoadContentArticleTag.setContentsClient(contentsClient);
    }
}
