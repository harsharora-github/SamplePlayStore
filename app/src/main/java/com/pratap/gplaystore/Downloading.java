package com.pratap.gplaystore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pratap.gplaystore.adapters.Download_Adapter;
import com.pratap.gplaystore.adapters.RecyclerViewDataAdapter;
import com.pratap.gplaystore.models.DownloadModel;
import com.pratap.gplaystore.models.SingleItemModel;

import java.util.ArrayList;

import static com.pratap.gplaystore.R.id.openbutton;
import static com.pratap.gplaystore.adapters.SectionListDataAdapter.imageList;
import static com.pratap.gplaystore.adapters.SectionListDataAdapter.nameList;
import static com.pratap.gplaystore.adapters.SectionListDataAdapter.pack_name;

/**
 * Created by harsh.arora on 06-11-2017.
 */

public class Downloading extends AppCompatActivity {

// static Button openbutton;
private BroadcastReceiver mReceiver;

    public Downloading() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_list);


      //  openbutton = (Button)findViewById(R.id.openbutton);

        Log.d("downloada", "imageList"  + imageList.toString());
        Log.d("downloada", "nameList"  + nameList.toString());

   /*     RecyclerView rv = (RecyclerView)findViewById(R.id.down_list_recycle);
        rv.setHasFixedSize(true);
        Download_Adapter adapter = new Download_Adapter(this,imageList,nameList);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv.setAdapter(adapter); */


    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...


        RecyclerView rv = (RecyclerView)findViewById(R.id.down_list_recycle);
        rv.setHasFixedSize(true);
        Download_Adapter adapter = new Download_Adapter(this,imageList,nameList,pack_name);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv.setAdapter(adapter);


        IntentFilter intentFilter = new IntentFilter("com.pratap.gplaystore.Downloading");

        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //extract our message from intent
                String msg_for_me = intent.getStringExtra("pack");

                openApp(context,msg_for_me);
                //log our message value
                Log.i("InchooTutorial", msg_for_me);

            }
        };
        //registering our receiver
        this.registerReceiver(mReceiver, intentFilter);

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //unregister our receiver
        this.unregisterReceiver(this.mReceiver);
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
            Toast.makeText(context, "This application has been installed ", Toast.LENGTH_SHORT).show();

            RecyclerView rv = (RecyclerView)findViewById(R.id.down_list_recycle);
            rv.setHasFixedSize(true);
            Download_Adapter adapter = new Download_Adapter(this,imageList,nameList,pack_name);
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            rv.setAdapter(adapter);


            //   i.addCategory(Intent.CATEGORY_LAUNCHER);
            // context.startActivity(i);

        }
        return true;
    }
}
