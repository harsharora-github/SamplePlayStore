package com.pratap.gplaystore;

/**
 * Created by harsh.arora on 02-11-2017.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.pratap.gplaystore.Database.Method_DataBase;

import static com.pratap.gplaystore.Logger.logg;
import static com.pratap.gplaystore.adapters.SectionListDataAdapter.pack_name;
import static com.pratap.gplaystore.adapters.SectionListDataAdapter.urlList;


public class appinstallrcv extends BroadcastReceiver {


    Method_DataBase md1;

    public appinstallrcv() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        md1 = new Method_DataBase();
        String actionStr = intent.getAction();
        try {
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            if (Intent.ACTION_PACKAGE_ADDED.equals(actionStr)) {

                Intent intent1 = new Intent(context,App_Download.class);
                context.startService(intent1);
            Log.d("harsh","BroadCast");

            } else if (Intent.ACTION_PACKAGE_FIRST_LAUNCH.equals(actionStr)) {

            } else if (Intent.ACTION_PACKAGE_REMOVED.equals(actionStr)) {


            }

        } catch (Exception e) {
        }
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


