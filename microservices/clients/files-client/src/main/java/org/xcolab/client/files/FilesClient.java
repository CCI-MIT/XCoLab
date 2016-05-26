package org.xcolab.client.files;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.util.RequestUtils;

public final class FilesClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/files-service";

    public static Object createFileEntry(
            Object balloonLink) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/fileEntry/");
        return RequestUtils.post(uriBuilder, balloonLink, Object.class);
    }
}
