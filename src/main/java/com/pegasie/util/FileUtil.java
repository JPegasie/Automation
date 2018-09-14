package com.pegasie.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> getFileListByExtension(String folder, String extension) {
        List<String> result = new ArrayList<String>();
        folder = folder.substring(0, folder.lastIndexOf("/"));
        File directory = new File(folder);
        for (File testPlan : directory.listFiles()) {
            String fileName = testPlan.getName();
            if (fileName.substring(fileName.lastIndexOf(".") + 1).equals(extension)) {
                result.add(testPlan.getAbsolutePath());
            }
        }
        return result;
    }

    public static String getFileNameFromAbsolutePath(String absolutePath) {
        String result = null;
        while (absolutePath.contains("\\")) {
            absolutePath = absolutePath.substring(absolutePath.indexOf("\\") + 1);
        }
        result = absolutePath.substring(0, absolutePath.indexOf("."));
        return result;
    }

    public static String getFullUploadFilePath(String fileName) {
        String path = System.getProperty("Upload");
        path += fileName;
        return path;
    }

    public static void writeTxtFile(Path txtFile, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(txtFile, Charset.forName("UTF-8"))) {
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
