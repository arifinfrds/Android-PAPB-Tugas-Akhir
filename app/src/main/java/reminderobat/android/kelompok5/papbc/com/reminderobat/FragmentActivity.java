package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class FragmentActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Toolbar mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        pager = findViewById(R.id.pager);
        pager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        tabs = findViewById(R.id.tabs);
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.cian700));
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
