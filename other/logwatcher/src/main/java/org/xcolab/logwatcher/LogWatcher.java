
package org.xcolab.logwatcher;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class LogWatcher {

    private static final Properties prop = new Properties();
    private static final List<ParsedException> knownExceptions = new LinkedList<>();

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        parseConfigFile();
        createWhiteListFileIfNotExists();
        readWhiteListFromDisk();

        String command = "tail -F " + prop.getProperty("pathForLogFile");

        String[] cmd = { "/bin/sh", "-c", command };
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);

        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        String line;

        List<ParsedException> newLogMessages= new LinkedList<>();
        long lastActivity = System.currentTimeMillis();

        while((line=input.readLine()) != null) {
            //System.out.println(line);
            if (line.length() < 5) {
                continue;
            }
            if (!line.contains("\tat ") && !line.contains("Caused by")){
                newLogMessages.add(new ParsedException(line));
            } else {
                if (newLogMessages.size() == 0) {
                    continue;
                }
                newLogMessages.get(newLogMessages.size()-1).stackTrace.add(line);
            }
            // Check if last log activity is more then 1 second in the past and trigger handling
            if(System.currentTimeMillis() - lastActivity > 1000){
                lastActivity = System.currentTimeMillis();
                // check all new log messages
                Iterator<ParsedException> i = newLogMessages.iterator();
                while (i.hasNext()) {
                    ParsedException e = i.next();
                    // handle only exceptions (check first half of message)
                    String half = e.header.substring(0,e.header.length()/2);
                    if (half.contains("ERROR") || half.contains("Exception")) {
                        // ignore known exceptions
                        boolean known = false;
                        for (ParsedException ex : knownExceptions){
                            if(e.equals(ex)) {
                                known = true;
                            }
                        }
                        if (!known) {
                            knownExceptions.add(e);
                            //System.out.println(e.header);
                            createIssue(e);
                            appendToWhiteListOnDisk(e);
                        }
                    }
                    i.remove();
                }
            }
        }

        int exitVal = pr.waitFor();
        System.out.println("Exited with error code " + exitVal);
    }

    private static void createIssue(ParsedException e){
        try{
            final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(new URI(prop.getProperty("jiraURL")), prop.getProperty("jiraUserName"), prop.getProperty("jiraUserPassword"));

            IssueInputBuilder issueBuilder = new IssueInputBuilder(prop.getProperty("jiraProjectKey"), 1l);

            issueBuilder.setSummary("[LOGWATCHER] " + e.header.substring(0,Math.min(e.header.length(),200)));
            issueBuilder.setDescription("This ticket was generated automatically by logwatcher.\n\nStacktrace:\n" + e.header + "\n\n" + e.stackTrace);

            restClient.getIssueClient().createIssue(issueBuilder.build()).claim();
            restClient.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void createWhiteListFileIfNotExists(){
        File f = new File(prop.getProperty("knownExceptionsPath"));
        if(!f.exists()) {
            try{
                ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(prop.getProperty("knownExceptionsPath")));
                os1.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void appendToWhiteListOnDisk(ParsedException pe){
        try{
            ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream(prop.getProperty("knownExceptionsPath"), true)) {
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            os2.writeObject(pe);
            os2.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void readWhiteListFromDisk(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(prop.getProperty("knownExceptionsPath")));
            ParsedException obj;
            while ((obj = (ParsedException)ois.readObject()) != null) {
                knownExceptions.add(obj);
            }
            ois.close();
        } catch(EOFException e) {

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void parseConfigFile(){
        String path = System.getProperty("user.dir") + "/config.properties";
        File f = new File(path);
        if(!f.exists()) {
            path = System.getProperty("user.dir") + "/target/classes/config.properties"; /* local
             testing */
        }

        InputStream input = null;
        try {
            input = new FileInputStream(path);
            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
