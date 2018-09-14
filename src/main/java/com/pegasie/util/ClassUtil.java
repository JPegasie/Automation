package com.pegasie.util;

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {
    // Get all the page classes name and write it to ClassName.txt file in resources folder
    public static void main(String[] args) {
        String[] packages = new String[]{"com.pegasie.pages", "com.pegasie.pages.cia"};
        String names = "";
        for (String pack : packages) {
            List<Class> classes = null;
            try {
                classes = getClasses(ResolverUtil.Test.class.getClassLoader(), pack);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Class c : classes) {
                names += c.toString().substring(23) + "\n";
            }
        }
        Path txtFile = Paths.get("src/main/resources/ClassName.txt");
        FileUtil.writeTxtFile(txtFile, names);
    }

    private static List<Class> getClasses(ClassLoader cl, String packageName) throws Exception {
        List<Class> classes = new ArrayList<>();
        URL packageUrl = cl.getResource(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) packageUrl.getContent()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + line.substring(0, line.lastIndexOf('.'))));
            }
        }
        return classes;
    }
}
