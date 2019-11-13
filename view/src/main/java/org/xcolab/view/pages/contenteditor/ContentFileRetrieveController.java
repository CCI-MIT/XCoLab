package org.xcolab.view.pages.contenteditor;

import org.xcolab.client.content.pojo.tables.pojos.FileEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.pojo.IFileEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ContentFileRetrieveController extends BaseContentEditor{

    @Autowired
    private IFileClient fileClient;

    @GetMapping("/content-editor/fileUploaderListFiles")
    public List<FileEntry> contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {
        try {
            return fileClient.getNonImageFilesEntry();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return new ArrayList<FileEntry>();
    }
}
