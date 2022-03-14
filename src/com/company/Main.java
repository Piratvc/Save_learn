package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {

    static void saveGame(String dir, GameProgress progress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir))) {
            oos.writeObject(progress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String dirZip, List<String> files) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirZip))) {
            for (String s : files) {
                try (FileInputStream fis = new FileInputStream(s)) {
                    ZipEntry entry1 = new ZipEntry(new File(s).getName());
                    zout.putNextEntry(entry1);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void delete(List<String> files) {
        for (String s : files) {
            File f = new File(s);
            if (f.delete()) {
                System.out.println("Файл " + s + " удален");
            }
        }
    }


    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(1555, 20000, 3, 4);
        GameProgress save2 = new GameProgress(1, 1, 1, 1111111);
        GameProgress save3 = new GameProgress(9111111, 8, 7, 6);


        saveGame("C:\\Games\\savegames\\save1.txt", save1);
        saveGame("C:\\Games\\savegames\\save2.txt", save2);
        saveGame("C:\\Games\\savegames\\save3.txt", save3);

        List<String> fileNames = new ArrayList<>();
        fileNames.add("C:\\Games\\savegames\\save1.txt");
        fileNames.add("C:\\Games\\savegames\\save2.txt");
        fileNames.add("C:\\Games\\savegames\\save3.txt");

        zipFiles("C:\\Games\\savegames\\save.zip", fileNames);

        delete(fileNames);


    }
}
