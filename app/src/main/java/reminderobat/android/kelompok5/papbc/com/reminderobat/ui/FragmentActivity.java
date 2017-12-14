package reminderobat.android.kelompok5.papbc.com.reminderobat.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;

import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.KEY.KEY_REMINDER_OBAT_SHARED_PREF;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.SHARED_PREFERENCES.SHARED_PREF_NAME;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.TAG.TAG_SHARED_PREF_SAVE;

public class FragmentActivity extends AppCompatActivity {
    TabLayout mtabs;
    ViewPager mpager;
    ImageView mSetting;

    @Override
    protected void onResume() {
        super.onResume();

        // load shared pref list yang sekarang
//        SharedPreferences sharedPref = getSharedPreferences(
//                SHARED_PREF_NAME,
//                Context.MODE_PRIVATE
//        );
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        Set<String> defaultValueSetString = new HashSet<>();
        Set<String> reminderObatSetString = sharedPref.getStringSet(
                KEY_REMINDER_OBAT_SHARED_PREF,
                defaultValueSetString
        );

        Log.d(TAG_SHARED_PREF_SAVE, "FragmentActivity: onResume: reminderObatSetString: " + reminderObatSetString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mpager = findViewById(R.id.pager);
        mpager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mtabs = findViewById(R.id.tabs);
        mtabs.setTabTextColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.cian700));

        mtabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        mtabs.setupWithViewPager(mpager);
        mtabs.setTabGravity(TabLayout.GRAVITY_FILL);

        mSetting = findViewById(R.id.setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FragmentActivity.this, "Setting", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
