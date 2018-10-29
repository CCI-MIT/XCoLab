package org.xcolab.pojo.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import org.xcolab.pojo.generator.util.InterfaceDescription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class PojoGenerator extends AbstractMojo {

    @Parameter(property = "interfaceDirectory", required = true)
    private File interfaceDirectory;

    @Parameter(defaultValue = "${project.build.directory}", property = "outputDirectory", required = true)
    private File outputDirectory;

    @Override
    public void execute()
            throws MojoExecutionException {
        List<InterfaceDescription> interfaces = getInterfaces();

        for (InterfaceDescription desc : interfaces) {
            System.out.println(desc.getName());
            for (Entry<String, String> member : desc.getMembers().entrySet()) {
                System.out.println(member.getValue() + " " + member.getKey());
            }
        }

        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdirs();
        }

        File touch = new File(f, "touch.txt");

        FileWriter w = null;
        try {
            w = new FileWriter(touch);

            w.write("touch.txt");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private List<InterfaceDescription> getInterfaces() {
        if (!interfaceDirectory.exists()) {
            throw new IllegalArgumentException("interfaceDirectory " + interfaceDirectory + " does not exist!");
        }
        if (!interfaceDirectory.isDirectory()) {
            throw new IllegalArgumentException("interfaceDirectory " + interfaceDirectory + " is not a directory!");
        }

        List<InterfaceDescription> interfaces = new ArrayList<>();
        File[] interfaceFiles = interfaceDirectory.listFiles();
        for (File interfaceFile : interfaceFiles) {
            InterfaceDescription desc = new InterfaceDescription(interfaceFile);
            interfaces.add(desc);
        }
        return interfaces;
    }
}
