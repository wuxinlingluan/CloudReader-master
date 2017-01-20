package com.example.jingbin.cloudreader.utils;

import android.os.Handler;

/**
 * Created by ${sheldon} on 2017/1/19.
 */
public class ThreadUtils {


    /**
     * 在子线程运行 run
     * @param run
     */
    public static void runInChildThread(Runnable run){
        new Thread(run).start();
    }

    private static Handler mHandler = new Handler();

    /**
     * 在主线程运行 run
     * @param run
     */
    public static void runInUiThread(Runnable run){
        mHandler.post(run);
    }
}
