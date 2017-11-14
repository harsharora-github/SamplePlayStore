package com.pratap.gplaystore;

/**
 * Created by harsh.arora on 02-11-2017.
 */



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pratap.gplaystore.Database.Main_DataBase;



public class appinstallrcv extends BroadcastReceiver {





    public appinstallrcv() {


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Main_DataBase md;
        Log.d("harsh", "broadcast rcv: ");
        String actionStr = intent.getAction();
        try {
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            md = new Main_DataBase(context);


            if (Intent.ACTION_PACKAGE_ADDED.equals(actionStr)) {
                Log.d("harsh", "broadcast rcv: pkg added");
                if(md.selectDATA().size() > 0) {
                   // String packageName = intent.getData().getEncodedSchemeSpecificPart();

                    Intent i = new Intent("com.pratap.gplaystore.Downloading").putExtra("pack", packageName);
                    context.sendBroadcast(i);

                    Log.d("harsh","Install Complete");
                    md.insertUPDATE_TABLE1("InstallComplete",packageName);

                    Log.d("harsh","Open Complete");
                    Bundle x = new Bundle();
                    x.putString("check","NDB");
                    intent.putExtras(x);


                    Intent intent1 = new Intent(context,App_Download.class);
                    context.startService(intent1);
                   // Log.d("harsh","Install Complete");
                  //  md.insertUPDATE_TABLE1("InstallComplete",packageName);
                  //  openApp(context,packageName);
                }else{
                    Log.d("harsh","Installing other app");

                  //  Log.d("harsh","Install Complete");
                    md.insertUPDATE_TABLE1("InstallComplete",packageName);
                    Intent i = new Intent("com.pratap.gplaystore.Downloading").putExtra("pack", packageName);
                    context.sendBroadcast(i);
                }

            Log.d("harsh","BroadCast");

            } else if (Intent.ACTION_PACKAGE_FIRST_LAUNCH.equals(actionStr)) {

            } else if (Intent.ACTION_PACKAGE_REMOVED.equals(actionStr)) {


            }

       } catch (Exception e) {
        }
    }


    public boolean openApp(Context context, String packageName) {


        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);

        Log.d("harsh","package yeah hai"+ packageName.toString());

        Log.d("harsh","intent package yeah hai"+ i.toString());


        if (i == null) {
            Log.d("harsh","Install null hai and button will not visible");
            Toast.makeText(context, "This application is installing ", Toast.LENGTH_SHORT).show();
        } else {

            Log.d("harsh","Install Complete and button will visible");


         //   i.addCategory(Intent.CATEGORY_LAUNCHER);
           // context.startActivity(i);

        }
        return true;
    }
}





 /*   public void createMyNotification(String titl, String titlcont, String apppack, Context mContext) {

        PackageManager pm = mContext.getPackageManager();
        Intent LaunchIntent = null;
        String name = "";
        String stat = "";
        logg("creating Notification");
        try {
            if (pm != null) {
                logg("launch Noti:" + apppack);
                ApplicationInfo app = mContext.getPackageManager().getApplicationInfo(apppack, 0);
                name = (String) pm.getApplicationLabel(app);
                LaunchIntent = pm.getLaunchIntentForPackage(apppack);
            } else {
                logg("fail Noti");
                stat = "fl";

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            stat = "exc";
            logg("Launch Excep");
        }
        if (stat.equals("")) {
            logg("stats:" + stat);
            try {
                // NotificationManager notificationmanager = (NotificationManager) mContext
                //        .getSystemService(NOTIFICATION_SERVICE);
                // notificationmanager.cancel(8935);

                Intent intent = LaunchIntent; // new Intent();
                PendingIntent pIntent = PendingIntent.getActivity(mContext, (int) System.currentTimeMillis(), intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
                builder.setContentTitle(titl);
                builder.setContentText(titlcont);
                builder.setSmallIcon(R.drawable.power);
                builder.setAutoCancel(true);
                builder.setDefaults(DEFAULT_ALL);
                builder.setContentIntent(pIntent);

                NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
                manager.notify(8945, builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
            logg("stats:" + stat);
        } else {
            logg("stats:" + stat);
        }
    }  */


