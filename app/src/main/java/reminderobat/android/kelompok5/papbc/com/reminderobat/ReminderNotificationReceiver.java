package reminderobat.android.kelompok5.papbc.com.reminderobat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import reminderobat.android.kelompok5.papbc.com.reminderobat.deprecated.MainActivity;
import reminderobat.android.kelompok5.papbc.com.reminderobat.model.ReminderModel;

/**
 * Created by RizqiAryansa on 12/14/2017.
 */

public class ReminderNotificationReceiver extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 1;
    private static final String ACTION_CANCEL_NOTIFICATION =
            "reminderobat.android.kelompok5.papbc.com.reminderobat.ACTION_CANCEL_NOTIFICATION";
    private ReminderModel reminderModel;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context);
        notifyBuilder.setContentTitle(reminderModel.getKeterangan())
                .setContentText(reminderModel.getJam())
                .setSubText(reminderModel.getNamaObat())
                .setSmallIcon(R.drawable.obat)
                .setContentIntent(notificationPendingIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
}
