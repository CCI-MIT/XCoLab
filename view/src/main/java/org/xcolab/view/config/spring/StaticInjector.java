package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.xcolab.client.content.FilesClient;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;

public class StaticInjector implements ApplicationRunner {

    @Autowired
    private FilesClient filesClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        ImageUploadUtils.setFilesClient(filesClient);
    }
}
