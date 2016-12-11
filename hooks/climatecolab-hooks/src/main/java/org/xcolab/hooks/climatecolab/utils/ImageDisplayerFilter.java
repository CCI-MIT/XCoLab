package org.xcolab.hooks.climatecolab.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.files.FilesClient;
import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageDisplayerFilter implements Filter {

    private final static Logger _log = LoggerFactory.getLogger(ImageDisplayerFilter.class);

    private void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String imageId = null;

        // image/contest?img_id=1272201
        // image/proposal?img_id=1266433
        // image/contest?img_id=1267815
        if (request.getParameter("img_id") != null) {
            imageId = request.getParameter("img_id");
        }

        // /image/user_male_portrait?screenName=slocum&companyId=10112&portraitId=2051002&userId=40218
        // image/user_male_portrait?screenName=carlosbpf&companyId=10112&portraitId=2390159
        if (request.getParameter("portraitId") != null) {
            if(request.getParameter("userId") != null){
                try {
                    Member member = MembersClient.getMember(Long.parseLong(request.getParameter("userId")));
                    if ( member.getPortraitFileEntryId() != null ) {
                        imageId = member.getPortraitFileEntryId() + "";
                    } else {
                        imageId = null;
                    }
                } catch (MemberNotFoundException e) {
                    imageId = request.getParameter("portraitId");
                }
            }else {
                imageId = request.getParameter("portraitId");
            }
        }

        String path = request.getSession().getServletContext().getRealPath("/");
        if (imageId != null && !imageId.isEmpty()) {
            try {
                FileEntry fileEntry = FilesClient.getFileEntry(new Long(imageId));
                String filePath = FilesClient.getFilePathFromFinalDestination(fileEntry, path);
                if (filePath != null) {
                    //check if file exists
                    File f = new File(filePath);
                    if(f.exists() && !f.isDirectory()) {
                        sendImageToResponse(request, response, filePath);
                        return;
                    }
                }
            } catch (FileEntryNotFoundException ignored) {

            }
        }

        if (request.getRequestURI().contains("user_male_portrait")) {
            String pathToFailOverImage;
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            if (themeDisplay != null) {
                pathToFailOverImage =
                        path + "../" + themeDisplay.getPathImage() + "user_default.png";
            } else {
                pathToFailOverImage = path + "../climatecolab-theme/images/user_default.png";
                _log.warn("Theme display was null in image filter - falling back to default theme");
            }
            sendImageToResponse(request, response, pathToFailOverImage);
            return;
        }
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException ignored) {

        }
    }

    private void sendImageToResponse(HttpServletRequest request, HttpServletResponse response, String filePath) {

        try {
            ServletContext cntx = request.getSession().getServletContext();
            String mime = cntx.getMimeType(filePath);
            if (mime == null) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            response.setContentType(mime);
            File file = new File(filePath);
            response.setContentLength((int) file.length());

            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
            return;
        } catch (IOException ignored) {
        }
        try{
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }catch (IOException ignored){

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //no initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        doGet((HttpServletRequest) request, (HttpServletResponse) response);

    }

    @Override
    public void destroy() {
        //no destroy method needed
    }
}
