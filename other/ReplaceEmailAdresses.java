import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ReplaceEmailAdresses {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter pr = null;


        if(args.length > 0) {
            try {
                FileReader dumpReader = new FileReader(args[0]);
                br = new BufferedReader(dumpReader);
                File newDump = new File("newDump.sql");
                pr = new PrintWriter(newDump);
                String sqlStatement = "INSERT INTO `User_`";
                final Scanner scanner = new Scanner(dumpReader);
                StringBuilder sb = new StringBuilder();

                while (scanner.hasNextLine()) {
                    final String lineFromFile = scanner.nextLine();
                    if(lineFromFile.contains(sqlStatement)) {
                        String[] listOfValues = lineFromFile.split(",");
                        for (int i=0; i<listOfValues.length; i++) {
                            if (listOfValues[i].contains("@")){
                                listOfValues[i] = "asdf@as.df";
                            }
                        }
                        for (String n : listOfValues) {
                            if (sb.length() > 0) {
                                sb.append(',');
                            }
                            sb.append(n);
                        }
                        pr.println(sb.toString());
                    }
                    else{
                        pr.println(lineFromFile);
                    }
                }
                System.out.println("New SQL-file newDump.sql created!");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            br.close();
            pr.close();
        }
        else
            System.out.println("Please specify the path of your dump as command-line argument!");
    }

}
