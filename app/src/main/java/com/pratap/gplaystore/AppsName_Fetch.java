package com.pratap.gplaystore;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.pratap.gplaystore.Constant.URL12;
import static com.pratap.gplaystore.Constant.URL13;
import static com.pratap.gplaystore.PollRes.poll;

/**
 * Created by harsh.arora on 31-10-2017.
 */

public class AppsName_Fetch extends AsyncTask<String, String, ArrayList<String>> {

    String urldisplay =URL13;

    String app_name;


    ArrayList<String> As ;

    @Override
    protected ArrayList<String> doInBackground(String... urls) {

        Log.d("harsh", "doInBackground:entry");
        // urldisplay = urls[0];
        String Str= poll(urldisplay);
        As=new ArrayList<>();
        Log.d("harsh", "doInBackground:"+Str);
        try {
            Log.d("harsh", "doInBackground:entryto try ");
            JSONObject jObject = new JSONObject(Str);
            JSONArray json_Array = jObject.getJSONArray("app_names");
            Log.d("harsh", "doInBackground: json_Array print to string"+json_Array.toString());

            for(int i=0;i<json_Array.length();i++){
                JSONObject json_data = json_Array.getJSONObject(i);
                app_name= json_data.getString("app");
                Log.d("harsh", "doInBackground title:"+json_data.getString("app"));



                As.add(app_name);
            }

            Log.d("harsh", "doInBackground:"+As.size());
        } catch (Exception e) {
            Log.d("Json", "doInBackground: " + e.getMessage());
        }
        return As;
    }
    protected void onPostExecute(ArrayList<String> result) {
        super.onPostExecute(result);
    }
}
