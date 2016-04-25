package me.tehcpu.artists.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import me.tehcpu.artists.ArtistsApplication;
import me.tehcpu.artists.RootActivity;

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
            while ((n = fis.read(buffer)) != -1)
            {
                fileContent.append(new String(buffer, 0, n));
            }
            return String.valueOf(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}