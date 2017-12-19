package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;

public class FragmentActivity extends AppCompatActivity {
    TabLayout mtabs;
    ViewPager mpager;


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



    }

}
