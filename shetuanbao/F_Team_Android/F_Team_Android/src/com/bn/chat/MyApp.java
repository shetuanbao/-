package com.bn.chat;


import io.rong.imkit.RongIM;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.bn.util.RongCloudEvent;

public class MyApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();


        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK���õ�һ�� ��ʼ��
             */
            RongIM.init(this);
        	if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

                RongCloudEvent.init(this);
               
            
        }
           
            
            
        }
    }

    /**
     * ��õ�ǰ��̵�����
     *
     * @param context
     * @return ��̺�
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}