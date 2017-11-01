package com.pratap.gplaystore;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.pratap.gplaystore.Constant.URL11;
import static com.pratap.gplaystore.PollRes.poll;

/**
 * Created by harsh.arora on 30-10-2017.
 */

public class Length_Fetch extends AsyncTask<String, String, ArrayList<String>> {

    String urldisplay =URL11;

    String verti_app;
    String hori_app;

    ArrayList<String> ls ;

    @Override
    protected ArrayList<String> doInBackground(String... urls) {
        ls = new ArrayList();
        Log.d("harsh", "doInBackground:entry");
        // urldisplay = urls[0];
        String Str= poll(urldisplay);
        //  ArrayList data=new ArrayList<>();
        Log.d("harsh", "doInBackground:"+Str);
        try {
            Log.d("harsh", "doInBackground:entryto try ");
            JSONObject jObject = new JSONObject(Str);
            String arr = jObject.getString("numbers");
            JSONObject jObject2 = new JSONObject(arr);
            hori_app = jObject2.getString("horizontal").toString();
            verti_app = jObject2.getString("vertical").toString();
            Log.d("harsh", "doInBackground hori_app:"+hori_app+"verti_app:"+verti_app);
            //Log.d("harsh", "doInBackground: json_Array print"+json_Array);
           // Log.d("harsh", "doInBackground length:"+json_Array.length());
            ls.add(verti_app);
            ls.add(hori_app);
            /*
            for(int i=0;i<json_Array.length();i++){
                JSONObject json_data = json_Array.getJSONObject(i);
                Log.d("harsh", "doInBackground jsonstring:"+json_data.toString());
                  verti_app = json_data.getString("vertical");
                Log.d("harsh", "doInBackground vertical:"+json_data.getString("vertical"));
                Log.d("harsh", "doInBackground i:"+i);
                hori_app = json_data.getString("horizontal");
                Log.d("harsh", "doInBackground horizontal:"+json_data.getString("horizontal"));

                ls.add(verti_app);
                ls.add(hori_app);

            }
*/
            Log.d("harshh", "doInBackground:"+ls.size());
        } catch (Exception e) {
            Log.d("Json", "doInBackground: " + e.getMessage());
        }
        return ls;
    }
    protected void onPostExecute(ArrayList<String> result) {
        super.onPostExecute(result);
    }
}
