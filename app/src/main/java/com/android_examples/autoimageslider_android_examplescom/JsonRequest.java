package com.android_examples.autoimageslider_android_examplescom;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 4/21/2017.
 */

public class JsonRequest {

    private final static String apiKey="255cd77ffd23e19990ec8c918b13746e";


        //get an arrayList of urls in String formant
    static public ArrayList<String> getArray(String finalResult){
        ArrayList<String> urlArray = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(finalResult);
            JSONObject photos = json.getJSONObject("photos");
            JSONArray photo = photos.getJSONArray("photo");

            for (int i = 0; i < photo.length(); i++) {
                JSONObject jPart = photo.getJSONObject(i);

                urlArray.add("https://farm" + jPart.getString("farm") + ".staticflickr.com/" + jPart.getString("server")
                        + "/" + jPart.getString("id") + "_" + jPart.getString("secret") + ".jpg");
            }

            return urlArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String requestRawJson(String textId){
        return "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + apiKey + "&tags=" + textId + "&format=json&nojsoncallback=1";
    }


}
