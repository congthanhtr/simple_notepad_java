package com.example.demo;

public class Util {

    public static String userSearch = "";

    public static String trim_imageFileName(String fileName) {
        // file name would be: "file:/D:/HCMUS/Echs/Import%20photos%20from%20PC%20to%20Camera%20Roll/2020_05_08_18_45_44A4D1EA-2A60-499B-9177-1DF2126C9018.JPG"
        // we will remove "file:" to take only the path, then store it into database
        return fileName.replaceFirst("file:/","");
    }
}
