package org.mediasoup.droid.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    findViewById(R.id.mediasoup).postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(SplashScreenActivity.this, RoomActivity.class));
        finish();
      }
    }, 1500);
  }
}
