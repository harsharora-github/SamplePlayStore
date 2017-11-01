package com.pratap.gplaystore;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.pratap.gplaystore.adapters.RecyclerViewDataAdapter;
import com.pratap.gplaystore.models.SectionDataModel;
import com.pratap.gplaystore.models.SingleItemModel;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    String topic = null;
    String app_name = null;
    String image_url = null;
    String app_url = null;
    String app_package = null;
    String dataa = null;
    Bitmap image;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> data1 = new ArrayList<>();
    ArrayList<String> data2 = new ArrayList<>();
    ArrayList<String> data3 = new ArrayList<>();



    ArrayList<SectionDataModel> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        allSampleData = new ArrayList<SectionDataModel>();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");

        }

  /*      try {
            data= new  Length_Fetch().execute().get();
            Log.d("harsh", "onCreate: "+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            data1= new  Headers_Fetch().execute().get();
            Log.d("harsh", "onCreate: "+data1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            data2= new  AppsName_Fetch().execute().get();
            Log.d("harsh", "onCreate: "+data2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/
        try {
            data3= new  Json_Fetch().execute().get();
            Log.d("harsh", "onCreate: "+data3.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        ArrayList<SingleItemModel> singleItem = null;
        SectionDataModel dm = null;
        int i =0;
        for (Object object: data3) {

            String[] separated =  object.toString().trim().split("\\-");
            if((topic == null) || !topic.equals(separated[0])){
                topic = separated[0];
                dm = new SectionDataModel();
                singleItem = new ArrayList<SingleItemModel>();
                dm.setHeaderTitle(topic);
                dataa = separated[1];
                String[] dataarr = dataa.toString().trim().split("\\|");
                Log.d("harsh", "dataarr: "+dataa);
                app_name =  dataarr[0];
                image_url = dataarr[1];
                app_url = dataarr[2];
                app_package = dataarr[3];
               if (topic != null) {
                   Log.d("harsh", "topic!null: "+app_name);
                    singleItem.add(new SingleItemModel(app_name,image_url));
                    dm.setAllItemsInSection(singleItem);
                    allSampleData.add(dm);
                }else {
                   Log.d("harsh", "topic-null: "+app_name);
                   singleItem.add(new SingleItemModel(app_name, image_url));
               }

            }else{
                dataa = separated[1];
                String[] dataarr = dataa.toString().trim().split("\\|");
                Log.d("harsh", "datasec: "+dataa);
                app_name =  dataarr[0];
                image_url = dataarr[1];
                app_url = dataarr[2];
                app_package = dataarr[3];
                if(singleItem != null) {
                    singleItem.add(new SingleItemModel(app_name, image_url));
                }
                /*
                if(i == data3.size()-1){
                    Log.d("harsh", "last-line: "+app_name);
                    if(dm != null) {
                        dm.setAllItemsInSection(singleItem);
                        allSampleData.add(dm);
                    }
                }
                */
            }
            i++;
        }



        }

    }

/*
        for (int i = 1; i <= Integer.parseInt(data.get(0)); i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle(data1.get(i).toString());

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= Integer.parseInt(data.get(1)); j++) {
                singleItem.add(new SingleItemModel(data2.get(j).toString(), "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
    */

