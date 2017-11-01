package com.pratap.gplaystore;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by harsh.arora on 4/11/2017.
 */

public class App_Download extends IntentService {

    InstallApp ap;
    Context context;

   // HashMap<Integer,String> pr = new HashMap<>();

    public App_Download() {

        super("App_Download");
    }



    /*@Override
    protected String doInBackground(HashMap... param) {
        HashMap<Integer,String> pr = param[0];
        Log.d("harsh", "doInBackground:prsize: "+pr.size());
        // for(int i=0;i<pr.size();i++) {
        for(Integer entry : pr.keySet()) {
            Log.d("harsh", "doInBackground:lost: "+entry);
            try {
                Download dl = new Download();
                if (dl.DownloadFiles(pr.get(entry).toString())) {
                    Log.d("harsh", "doInBackground: dlcomp");
                } else {
                    Log.d("harsh", "doInBackground: LOL");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("harsh", "doInBackground: Loop:"+(entry +1));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onHandleIntent(Intent intent) {

        HashMap<Integer,String> pr = new HashMap<>();
        if (intent != null) {
            pr = (HashMap<Integer, String>) intent.getExtras().get("urlMap");
        }
        else{
            Log.d("harsh", "onHandleIntent:Null hai intent ");
        }
        Log.d("harsh", "doInBackground:prsize: "+pr.size());
        // for(int i=0;i<pr.size();i++) {
        for(Integer entry : pr.keySet()) {
            Log.d("harsh", "doInBackground:lost: "+entry);
            try {
                Download dl = new Download();
                String loc = dl.DownloadFiles(pr.get(entry).toString());
                if (!loc.equals("")) {
                    Log.d("harsh", "doInBackground: dlcomp");
                    ap = new InstallApp(this);
                    Log.d("harsh", loc);
                    if(ap.install(loc,"")) {
                        Log.d("harsh", "install done");
                    }else{
                        Log.e("harsh", "install fail");
                    }
                  //  dl.deleteDir();
                 //   Log.d("harsh", "directory deleted");

                } else {
                    Log.d("harsh", "doInBackground: LOL");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("harsh", "doInBackground: Loop:"+(entry +1));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return ;
    }


}
