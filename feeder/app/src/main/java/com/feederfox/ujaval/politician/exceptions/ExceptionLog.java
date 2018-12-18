package com.feederfox.ujaval.politician.exceptions;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vishwa on 8/4/2015.
 */
public class ExceptionLog {
    public static void logWrite()
    {
        try{
            //e.printStackTrace();
            File filename = new File(Environment.getExternalStorageDirectory()+"/logfile.txt");
            filename.createNewFile();

            String cmd = "logcat -d -f "+filename.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
        }
        catch (IOException e1) {
        }
    }
}
