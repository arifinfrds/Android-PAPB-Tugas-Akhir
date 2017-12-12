package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentActivity extends AppCompatActivity  {
    TabLayout mtabs;
    ViewPager mpager;
    ImageView mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Toolbar mToolbar =  findViewById(R.id.toolbar);
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
