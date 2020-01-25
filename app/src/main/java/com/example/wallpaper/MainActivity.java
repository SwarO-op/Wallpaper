package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    int z = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSetWallpaper = (Button)findViewById(R.id.set);
        Button buttonChange = (Button)findViewById(R.id.chg);
        final ImageView imagePreview = (ImageView)findViewById(R.id.preview);
        imagePreview.setImageResource(R.drawable.five);
        final int b[] = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six};

        buttonSetWallpaper.setOnClickListener(new Button.OnClickListener(){
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setResource(R.drawable.five);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }});
        buttonChange.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {

                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(2000);

                            for ( z = 0; z < b.length + 4; z++) {
                                if (z < b.length) {
                                    sleep(10000);
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            imagePreview.setImageResource(b[z]);
                                        }
                                    });
                                } else {
                                    z = 0;
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("finally");
                        }
                    }
                };
                timer.start();

            }});
    }
}
