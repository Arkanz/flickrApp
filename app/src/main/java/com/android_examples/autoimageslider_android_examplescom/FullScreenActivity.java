package com.android_examples.autoimageslider_android_examplescom;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.ArrayList;

public class FullScreenActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    ArrayList<String> urlArray;
    ArrayList<String> liked=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String finalResult = intent.getStringExtra("result");

        urlArray = JsonRequest.getArray(finalResult);

        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        for (String url : urlArray) {

            TextSliderView textSliderView = new TextSliderView(FullScreenActivity.this);
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

        makeToast("Tap on picture to Like!");
    }
    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    //go back to the home screen
    public void restart(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    //add picture to liked arraylist<String>
    @Override
    public void onSliderClick(BaseSliderView slider) {

        makeToast("Liked!");
        liked.add(slider.getUrl());
        Log.i("hashSet:", liked.toString());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    public void makeToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //open a new activity with liked pictures
    public void likedPictures(View view){
        Intent likedGalleryIntent =  new Intent(getApplicationContext(), LikedGalleryActivity.class);
        likedGalleryIntent.putStringArrayListExtra("Liked", liked);
        startActivity(likedGalleryIntent);
    }
}
