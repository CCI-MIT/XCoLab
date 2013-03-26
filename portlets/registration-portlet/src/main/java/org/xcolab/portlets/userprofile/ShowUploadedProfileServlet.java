package org.xcolab.portlets.userprofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowUploadedProfileServlet extends HttpServlet {
    private final static File UPLOAD_DIR = new File("/tmp");
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        if (fileName == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        File file = new File(UPLOAD_DIR, fileName);
        
        String contentType = getContentType(fileName);
        if ((! file.exists()) || contentType == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        
        response.setContentType(contentType);
        try {
            OutputStream os = response.getOutputStream();
            InputStream is = new FileInputStream(file);
            byte[] buf = new byte[8192];
            while (is.available() > 0) {
                int byteCount = is.read(buf);
                os.write(buf, 0, byteCount);
            }
            
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    private String getContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".")+ 1).toLowerCase();
        
        if (extension.equals("jpg") || extension.equals("jpeg")) {
            return "image/jpeg";
        }
        
        if (extension.equals("png")) {
            return "image/png";
        }
        
        if (extension.equals("gif")) {
            return "image/gif";
        }
        return null;
    }
    

}
