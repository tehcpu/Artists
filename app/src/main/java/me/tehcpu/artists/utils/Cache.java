package me.tehcpu.artists.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.tehcpu.artists.ArtistsApplication;

/**
 * Created by codebreak on 24/04/16.
 */
public class Cache {
    public static void save(String data) {
        FileOutputStream fos = null;
        try {
            fos = ArtistsApplication.getInstance().openFileOutput("artists", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get() {
        FileInputStream fis = null;
        StringBuilder fileContent = null;
        try {
            fis = ArtistsApplication.getInstance().openFileInput("artists");
            fileContent = new StringBuilder("");
            byte[] buffer = new byte[512000];
            int n;
            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
            return String.valueOf(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}