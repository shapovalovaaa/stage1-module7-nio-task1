package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public static Profile getDataFromFile(File file) {
        String fileData = readFileToString(file);
        Map<String, String> profileData = parseData(fileData);
        return createProfile(profileData);
    }

    private static String readFileToString(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static Map<String, String> parseData(String data) {
        Map<String, String> profileData = new HashMap<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] keyValue = line.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                profileData.put(key, value);
            }
        }
        return profileData;
    }

    private static Profile createProfile(Map<String, String> profileData) {
        String name = profileData.get("Name");
        int age = Integer.parseInt(profileData.get("Age"));
        String email = profileData.get("Email");
        String phone = profileData.get("Phone");
        return new Profile(name, age, email, Long.valueOf(phone));
    }
}
