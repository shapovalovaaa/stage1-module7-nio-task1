package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try(BufferedReader fileReader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                var lineEntry = parseLine(line);
                setProfileField(profile, lineEntry);
            }
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage());
        }
        return profile;
    }

    private void setProfileField(Profile profile, Map.Entry<String, String> lineEntry) {
        switch (lineEntry.getKey()){
            case "Name" :{
                profile.setName(lineEntry.getValue());
                break;
            }
            case "Age":{
                profile.setAge(Integer.parseInt(lineEntry.getValue()));
                break;
            }
            case "Email":{
                profile.setEmail(lineEntry.getValue());
                break;
            }
            case "Phone":{
                profile.setPhone(Long.parseLong(lineEntry.getValue()));
                break;
            }
            default:{
                throw new IllegalArgumentException(String.format("There is no field with name: %s, in class %s", lineEntry.getKey(), profile));
            }
        }
    }

    private Map.Entry<String, String> parseLine(String line){
        var entry = line.split(": ");
        String key = entry[0];
        String value = entry[1];
        return Map.entry(key, value);
    }
}
