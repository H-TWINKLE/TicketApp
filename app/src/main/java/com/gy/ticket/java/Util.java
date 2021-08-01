package com.gy.ticket.java;

import android.content.Context;

import com.gy.ticket.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TWINKLE on 2017/12/26.
 */

public class Util {



    public void delete(Context context) {
        File file_cache = context.getCacheDir();
        deleteFolder(file_cache);
    }

    public void deleteFolder(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
            }
        }
        file.delete();
    }

    public static ArrayList<String> read_txt(Context context) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        InputStream is = context.getResources().openRawResource(R.raw.place);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str.substring(0,str.length()-4));
        }
        return list;
    }

    public static String getid(Context context,String text) throws Exception {

        InputStream is = context.getResources().openRawResource(R.raw.place);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            if(math(text,str)){
               str = str.substring(text.length()+1);
                return str;
            }

        }

        return str;
    }

    private static boolean math(String week,String str){

        Pattern p= Pattern.compile(week+"\\|");
        Matcher m=p.matcher(str);
        return m.find();

    }

}
