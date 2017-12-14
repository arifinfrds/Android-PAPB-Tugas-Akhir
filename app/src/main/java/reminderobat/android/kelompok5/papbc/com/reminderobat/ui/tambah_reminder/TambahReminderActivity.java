package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.tambah_reminder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;

public class TambahReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup mRadioGroup;
    private EditText mEtDeskripsi;
    private TextView mTvTime;
    private Button mBtnTambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRadioGroup = findViewById(R.id.radio_group_tambah_reminder);
        mEtDeskripsi = findViewById(R.id.et_deskripsi_tambah_reminder);
        mTvTime = findViewById(R.id.tv_time_tambah_reminder);
        mBtnTambah = findViewById(R.id.btn_tambah_reminder);
        mBtnTambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_tambah_reminder) {
            tambahReminder();
        }

    }

    private void tambahReminder() {
        Toast.makeText(this, "tambahReminder();", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
