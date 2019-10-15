package org.xcolab.view.files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileDisplayController {

    private final FileDisplayService fileDisplayService;

    public FileDisplayController(FileDisplayService fileDisplayService) {
        this.fileDisplayService = fileDisplayService;
    }

    @GetMapping("/image/upload/images/{imageId}")
    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId)
            throws IOException {
        fileDisplayService.serveImage(request, response, imageId, ImageType.UNKNOWN);
    }

    @GetMapping("/image/upload/{imageType}/images/{imageId}")
    public void serveImage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long imageId, @PathVariable ImageType imageType)
            throws IOException {
        fileDisplayService.serveImage(request, response, imageId, imageType);
    }

    //@GetMapping("/image/upload/images/{fileName}")
/*    public void serveFile(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long fileName)
            throws IOException {
        fileDisplayService.serveImage(request, response, imageId, ImageType.UNKNOWN);
    }*/
}
