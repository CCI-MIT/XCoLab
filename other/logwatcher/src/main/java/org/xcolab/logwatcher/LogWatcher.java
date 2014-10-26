
package org.xcolab.logwatcher;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class LogWatcher {

    private static Properties prop = new Properties();
    private static List<ParsedException> knownExceptions = new LinkedList();

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        parseConfigFile();
        readWhiteListFromDisk();



        String command = "tail -F " + prop.getProperty("pathForLogFile");

        String[] cmd = { "/bin/sh", "-c", command };
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);

        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        String line;

        List<ParsedException> newLogMessages= new LinkedList();
        long lastActivity = System.currentTimeMillis();

        while((line=input.readLine()) != null) {
            //System.out.println(line);
            if (line.length() < 5) continue;
            if (!line.contains("\tat ") && !line.contains("Caused by")){
                newLogMessages.add(new ParsedException(line));
            } else {
                if (newLogMessages.size() == 0) continue;
                newLogMessages.get(newLogMessages.size()-1).stackTrace.add(line);
            }
            // Check if last log activity is more then 1 second in the past and trigger handling
            if(System.currentTimeMillis() - lastActivity > 1000){
                lastActivity = System.currentTimeMillis();
                // check all new log messages
                Iterator<ParsedException> i = newLogMessages.iterator();
                while (i.hasNext()) {
                    ParsedException e = i.next();
                    // handle only exceptions
                    if (e.header.contains("ERROR") || e.header.contains("Exception")) {
                        // ignore known exceptions
                        boolean known = false;
                        for (ParsedException ex : knownExceptions){
                            if(e.equals(ex)) known = true;
                        }
                        if (!known) {
                            knownExceptions.add(e);
                            System.out.println(e.header);
                            //createIssue(e);
                            saveWhiteListToDisk();
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
            issueBuilder.setDescription("This ticket was generated automatically by logwatcher.\n\nStacktrace:\n" + e.header + "\n" + e.stackTrace);

            restClient.getIssueClient().createIssue(issueBuilder.build()).claim();
            restClient.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void saveWhiteListToDisk(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(prop.getProperty("knownExceptionsPath")));
            oos.writeObject(knownExceptions);
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void readWhiteListFromDisk(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(prop.getProperty("knownExceptionsPath")));
            knownExceptions = (List<ParsedException>) ois.readObject();
            ois.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void parseConfigFile(){
        String path = System.getProperty("user.dir") + "/config.properties";
        File f = new File(path);
        if(!f.exists()) path = System.getProperty("user.dir") + "/target/classes/config.properties"; /* local testing */

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