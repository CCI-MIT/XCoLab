package org.xcolab.view.pages.contenteditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content-editor/imageUpload")
public class ImageUploadController {

    @GetMapping
    public String showUploadForm() {
        return "contenteditor/imageUpload";
    }
}
