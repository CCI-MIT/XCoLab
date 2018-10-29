package org.xcolab.pojo.generator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterfaceDescription {

    private static final Pattern SETTER_REGEX = Pattern.compile("void set[a-zA-Z]+\\([a-zA-Z]+ [a-zA-Z]+\\);");

    private final String interfaceName;
    private final Map<String, String> members;

    public InterfaceDescription(File interfaceFile) {
        this.interfaceName = interfaceFile.getName().replaceFirst("I", "").replace(".java", "");
        this.members = new HashMap<>();
        extractMembers(interfaceFile);
    }

    private void extractMembers(File interfaceFile) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(interfaceFile))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (isSetter(line)) {
                        String type = extractType(line);
                        String name = extractName(line);
                        members.put(name, type);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isSetter(String line) {
        Matcher matcher = SETTER_REGEX.matcher(line);
        return matcher.find();
    }

    private static String extractType(String line) {
        String type = line.substring(line.indexOf("(") + 1);
        type = type.substring(0, type.indexOf(" "));
        return type;
    }

    private static String extractName(String line) {
        String name = line.substring(0, line.indexOf(")"));
        name = name.substring(name.lastIndexOf(" ") + 1);
        name.replaceAll(" ", "");
        return name;
    }

    public String getName() {
        return this.interfaceName;
    }

    public Map<String, String> getMembers() {
        return Collections.unmodifiableMap(this.members);
    }
}
