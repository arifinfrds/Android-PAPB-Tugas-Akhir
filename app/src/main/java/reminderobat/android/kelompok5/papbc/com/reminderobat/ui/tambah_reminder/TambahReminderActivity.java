package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.tambah_reminder;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ReminderNotificationReceiver;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel;
import reminderobat.android.kelompok5.papbc.com.reminderobat.util.GSONConverter;
import reminderobat.android.kelompok5.papbc.com.reminderobat.util.Number;

import static java.security.AccessController.getContext;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.KEY.KEY_REMINDER_OBAT_SHARED_PREF;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.SHARED_PREFERENCES.SHARED_PREF_NAME;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.TAG.TAG_REMINDER_MODEL;
import static reminderobat.android.kelompok5.papbc.com.reminderobat.Const.TAG.TAG_SHARED_PREF_SAVE;


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

    // deprecated
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private List<ReminderModel> mReminderModelArrayList;


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

        mReminderModelArrayList = new ArrayList<>();

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
            toast("pilih waktu");
            showTimePickerDialog();
        }

        if (id == R.id.btn_tambah_reminder) {
            tambahReminder();
            finish();
        }

    }

    private void tambahReminder() {
        toast("tambahReminder()");

        String deskripsi = mEtDeskripsi.getText().toString();

        if (TextUtils.isEmpty(deskripsi)) {
            toast("Mohon isi semua input.");
        } else {

            // get selected radio button
            int selectedId = mRadioGroup.getCheckedRadioButtonId();
            mSelectedRadioButton = findViewById(selectedId);

            //int idReminder = Number.getRandomNumberBetween(0, 999999);
            int idReminder = Number.getRandomNumberBetween(0, 999999);
            ReminderModel reminderModel = new ReminderModel(idReminder, deskripsi, mTime, getNamaObat());


            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getId(): " + reminderModel.getId());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getKeterangan(): " + reminderModel.getKeterangan());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getJam(): " + reminderModel.getJam());
            Log.d(TAG_REMINDER_MODEL, "tambahReminder: reminderModel.getNamaObat(): " + reminderModel.getNamaObat());

            showNotification(reminderModel);

            // save ke shared pref next branch...
            // writeToSharedPref(reminderModel);

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


    private void writeToSharedPref(ReminderModel reminder) {

        // load shared pref list yang sekarang
//        SharedPreferences sharedPref = getSharedPreferences(
//                SHARED_PREF_NAME,
//                Context.MODE_PRIVATE
//        );
        // SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        Set<String> defaultValueSetString = new HashSet<>();
        Set<String> reminderObatSetString = sharedPref.getStringSet(
                KEY_REMINDER_OBAT_SHARED_PREF,
                defaultValueSetString
        );

        Log.d(TAG_SHARED_PREF_SAVE, "writeToSharedPref: reminderObatSetString: " + reminderObatSetString);

        mReminderModelArrayList = GSONConverter.fromSetStringToList(
                reminderObatSetString,
                ReminderModel.class
        );

        Log.d(TAG_SHARED_PREF_SAVE, "writeToSharedPref: mReminderModelArrayList: " + mReminderModelArrayList.size());

        // write to shared pref logic
        mReminderModelArrayList.add(reminder);

        SharedPreferences sharedPrefSave = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPrefSave.edit();
        Set<String> newReminderObatSetString = GSONConverter.fromListToSetString(mReminderModelArrayList);

        editor.putStringSet(
                KEY_REMINDER_OBAT_SHARED_PREF,
                newReminderObatSetString
        );

        boolean isCommited = editor.commit();
        Log.d(TAG_SHARED_PREF_SAVE, "writeToSharedPref: isCommited: " + isCommited);

        Log.d(TAG_SHARED_PREF_SAVE, "writeToSharedPref: newReminderObatSetString: " + newReminderObatSetString);
    }

    public String getNamaObat() {
        return mSelectedRadioButton.getText().toString();
    }

    private String getRandomStringId(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
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
