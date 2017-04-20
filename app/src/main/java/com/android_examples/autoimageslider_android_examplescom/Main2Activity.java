package com.android_examples.autoimageslider_android_examplescom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    ArrayList<String> urlArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
         String finalResult =intent.getStringExtra("result");

        try {
            JSONObject json = new JSONObject(finalResult);
            JSONObject photos = json.getJSONObject("photos");
            JSONArray photo = photos.getJSONArray("photo");

            for (int i = 0; i < photo.length(); i++) {
                JSONObject jPart = photo.getJSONObject(i);

                urlArray.add("https://farm" + jPart.getString("farm") + ".staticflickr.com/" + jPart.getString("server")
                        + "/" + jPart.getString("id") + "_" + jPart.getString("secret") + ".jpg");
            }

            Log.i("array", urlArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        for (String url : urlArray) {

            TextSliderView textSliderView = new TextSliderView(Main2Activity.this);
            textSliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

//        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
