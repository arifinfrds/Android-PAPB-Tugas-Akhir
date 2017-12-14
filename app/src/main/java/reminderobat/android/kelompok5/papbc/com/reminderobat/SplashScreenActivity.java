package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.app.Activity;
import android.os.Bundle;

import java.util.Timer;

public class SplashScreenActivity extends Activity {
    Thread mthWait;
    Timer mtmTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }
}
