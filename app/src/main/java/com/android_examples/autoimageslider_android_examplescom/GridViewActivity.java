package com.android_examples.autoimageslider_android_examplescom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        final ArrayList<String> list = getIntent().getStringArrayListExtra("Liked");

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, list));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent i = new Intent(getApplicationContext(), LikedGalleryActivity.class);
                i.putStringArrayListExtra("Liked", list);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

    public void onClickBack(View view){
        Intent intent = new Intent(getApplicationContext(), FullScreenActivity.class);
        startActivity(intent);
    }
}
