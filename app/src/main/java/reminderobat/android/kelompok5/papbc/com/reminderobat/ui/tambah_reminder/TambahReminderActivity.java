package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.tambah_reminder;

import android.app.TimePickerDialog;

import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.notification.ReminderNotificationReceiver;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.reminder.ReminderFragment;
import reminderobat.android.kelompok5.papbc.com.reminderobat.util.Number;


import static reminderobat.android.kelompok5.papbc.com.reminderobat.util.Const.TAG.TAG_REMINDER_MODEL;


public class TambahReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup mRadioGroup;
    private RadioButton mSelectedRadioButton;

    private EditText mEtDeskripsi;
    private TextView mTvTime;

    private Button mBtnTambah;

    TimePickerDialog mTimePickerDialog;
    private Calendar mCalendar;
    private int mCalendarHour;
    private int mCalendarMinute;
    String mTimeFormat;

    String mTime;


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
        mTvTime.setOnClickListener(this);

        mBtnTambah = findViewById(R.id.btn_tambah_reminder);
        mBtnTambah.setOnClickListener(this);

        disableKeyboardPopupOnFirst();


    }


    private void showTimePickerDialog() {

        mCalendar = Calendar.getInstance();
        mCalendarHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mCalendarMinute = mCalendar.get(Calendar.MINUTE);


        mTimePickerDialog = new TimePickerDialog(
                TambahReminderActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            mTimeFormat = "AM";
                        } else if (hourOfDay == 12) {
                            mTimeFormat = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            mTimeFormat = "PM";
                        } else {
                            mTimeFormat = "AM";
                        }

                        String time = hourOfDay + ":" + minute + " " + mTimeFormat;
                        mTvTime.setText(time);

                        mTime = time;
                    }
                }, mCalendarHour, mCalendarMinute, false);
        mTimePickerDialog.show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.tv_time_tambah_reminder) {
            showTimePickerDialog();
        }

        if (id == R.id.btn_tambah_reminder) {
            tambahReminder();
        }

    }

    private void tambahReminder() {

        String deskripsi = mEtDeskripsi.getText().toString();

        if (TextUtils.isEmpty(deskripsi)) {
            toast("Mohon isi semua input.");
        } else {

            // get selected radio button
            int selectedId = mRadioGroup.getCheckedRadioButtonId();
            mSelectedRadioButton = findViewById(selectedId);

            int idReminder = Number.getRandomNumberBetween(0, 999999);

            if (mTime == null) {
                mTime = "09:00 AM";
            }

            ReminderModel reminderModel = new ReminderModel(idReminder, deskripsi, mTime, getNamaObat());

            // untuk debuggingdi logcat
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getId(): " + reminderModel.getId());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getKeterangan(): " + reminderModel.getKeterangan());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getJam(): " + reminderModel.getJam());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getNamaObat(): " + reminderModel.getNamaObat());

            showNotification(reminderModel);

            // pindah data object ke ReminderFragment
            ReminderFragment.mReminderModel = reminderModel;

            finish();


        }
    }


    private void showNotification(ReminderModel reminderModel) {
        ReminderNotificationReceiver notificationReceiver = new ReminderNotificationReceiver();

        notificationReceiver.Notification(
                reminderModel.getId(),
                reminderModel.getKeterangan(),
                reminderModel.getJam(),
                reminderModel.getNamaObat(),
                this
        );
    }

    public String getNamaObat() {
        return mSelectedRadioButton.getText().toString();
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

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void disableKeyboardPopupOnFirst() {
        // sembunyikan keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
