package reminderobat.android.kelompok5.papbc.com.reminderobat.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.reminder.ReminderFragment;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.daftar.DaftarFragment;

/**
 * Created by Firmanda on 12/12/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {
    String[] title = new String[]{
            "Daftar", "Reminder"
    };

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DaftarFragment();
                break;
            case 1:
                fragment = new ReminderFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
