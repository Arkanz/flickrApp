package com.android_examples.autoimageslider_android_examplescom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.ArrayList;

public class LikedGalleryActivity extends AppCompatActivity   implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_gallary);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("Liked");
        int id = intent.getIntExtra("id", 0);

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        TextSliderView textSliderView1 = new TextSliderView(LikedGalleryActivity.this);
        textSliderView1
                .image(list.get(id))
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);
        sliderLayout.addSlider(textSliderView1);

        for (String url : list) {
                if (url.equals(list.get(id))){ // this will skip clicked picture so it only shows once
                    continue;
                }
            TextSliderView textSliderView = new TextSliderView(LikedGalleryActivity.this);
            textSliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        sliderLayout.setCustomAnimation(new DescriptionAnimation(){

            public void onNextItemAppear(View view) {

                view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);

            }
        });
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);

    }

    public void restart(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), GridViewActivity.class);
        intent.putStringArrayListExtra("Liked",list);
        startActivity(intent);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
