package me.tehcpu.artists.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by codebreak on 23/04/16.
 */
public class Common {
    private static String TAG="Common";
    private static Common instance;

    public static String formSummary(long num, String nominative, String singular, String plural) {
        if (num > 10 && ((num % 100) / 10) == 1) return plural;
        switch ((int) (num % 10)) {
            case 1:
                return nominative;
            case 2:
            case 3:
            case 4:
                return singular;
            default: // case 0, 5-9
                return plural;
        }
    }

    public static String fixEncoding(String str) {
        try {
            byte[] u = str.getBytes("ISO-8859-1");
            str = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    public static Common getInstance() {
        if (instance == null) instance = new Common();
        return instance;
    }
}
