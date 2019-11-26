package org.xcolab.view.files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileDisplayController {

    private final FileDisplayService fileDisplayService;

    public FileDisplayController(FileDisplayService fileDisplayService) {
        this.fileDisplayService = fileDisplayService;
    }

    @GetMapping("/file")
    public void serveFile(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("file_id") long fileId)
            throws IOException {
        fileDisplayService.serveFile(request, response, fileId);
    }

}
