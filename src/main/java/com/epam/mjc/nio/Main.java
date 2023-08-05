package com.epam.mjc.nio;

import java.io.File;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL url = Main.class.getClassLoader().getResource("Profile.txt");

        if (url != null) {

            File file = new File(url.getFile());


            Profile profile = FileReader.getDataFromFile(file);

            System.out.println("Name: " + profile.getName());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone: " + profile.getPhone());
        }
    }
}