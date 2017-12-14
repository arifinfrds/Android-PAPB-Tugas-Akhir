package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.toturial.ToturialActivity;

public class SplashScreenActivity extends Activity {

    SharedPreferences mNotificationPreferences;
    final String NOTIF_STATUS = "notification";
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = this.findViewById(R.id.progressBar);
//        progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFF00, PorterDuff.Mode.CLEAR);


        mNotificationPreferences = getSharedPreferences(Const.SHARED_PREFERENCES.SHARED_PREF_NAME, MODE_PRIVATE);

        new Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                Intent x = new Intent(SplashScreenActivity.this, ToturialActivity.class);
                startActivity(x);
                finish();
            }
        },6000);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mNotificationPreferences.edit();
            preferenceEditor.putBoolean(NOTIF_STATUS, true);
            preferenceEditor.apply();
    }
}