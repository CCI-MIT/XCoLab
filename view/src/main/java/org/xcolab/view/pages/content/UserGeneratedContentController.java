package org.xcolab.view.pages.content;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;

import org.xcolab.client.content.pojo.tables.pojos.ContentArticleVersion;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.util.i18n.I18nUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserGeneratedContentController {


    private static final Long CONTENT_ARTICLE_ID = 27l;

    @Autowired
    private IContentClient contentClient;


    @PostMapping("/content/usergenerated/create")
    public void createUserGeneratedContent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String pattern = "MM/dd/yyyy HH:mm:sss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        StringBuffer csvString = new StringBuffer();
        StringBuffer header = new StringBuffer();
        header.append("create_at" + CSVWriter.DEFAULT_SEPARATOR);
        csvString.append(simpleDateFormat.format(new Date()) + CSVWriter.DEFAULT_SEPARATOR);

        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String aux = parameters.nextElement();

            header.append(cleanupString(aux) + CSVWriter.DEFAULT_SEPARATOR);
            csvString
                    .append(cleanupString(request.getParameter(aux)) + CSVWriter.DEFAULT_SEPARATOR);
        }

        try {
            final IContentArticle contentArticle = contentClient.getContentArticle(CONTENT_ARTICLE_ID);
            Locale locale = LocaleContextHolder.getLocale();
            String localeString = "en";
            if (locale.getLanguage() != null) {
                localeString = locale.getLanguage();
            }
            String currentContent = "";

            IContentArticleVersion oldContentArticleVersion = contentClient
                    .getLatestVersionByArticleIdAndLanguage(
                            contentArticle.getId(), localeString);
            if (oldContentArticleVersion == null) {
                oldContentArticleVersion = contentClient.getLatestVersionByArticleIdAndLanguage(
                        contentArticle.getId(),
                        I18nUtils.DEFAULT_LOCALE.getLanguage());
            }


            if (oldContentArticleVersion != null) {
                currentContent = oldContentArticleVersion.getContent();
            }

            IContentArticleVersion contentArticleVersion = new ContentArticleVersion();

            contentArticleVersion.setArticleId(oldContentArticleVersion.getArticleId());
            contentArticleVersion.setLang(oldContentArticleVersion.getLang());

            contentArticleVersion.setAuthorUserId(oldContentArticleVersion.getAuthorUserId());
            contentArticleVersion.setFolderId((oldContentArticleVersion.getFolderId()));
            contentArticleVersion.setTitle(oldContentArticleVersion.getTitle());
            if (currentContent.isEmpty()) {
                currentContent = header.toString() + "<br/>";
            }
            currentContent += csvString.toString() + "<br/>";

            contentArticleVersion.setContent(currentContent);
            contentClient.createContentArticleVersion(contentArticleVersion);

            response.getOutputStream().write("{success: true}".getBytes());
        } catch (ContentNotFoundException cnfe) {

        }
    }

    private String cleanupString(String original) {
        return original;
    }

    @GetMapping({"/content/usergenerated/retrievecontent"})
    public void getJudgingCsv(HttpServletRequest request, HttpServletResponse response) {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            String csvPayload = "";

            String separatorIndicationForExcel =
                    "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
            csvPayload = separatorIndicationForExcel + csvPayload;
            outputStream.write(csvPayload.getBytes());
            response.setContentType("application/csv");
            response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");

            response.setContentLength(outputStream.size());
            OutputStream out = response.getOutputStream();
            outputStream.writeTo(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }
}
