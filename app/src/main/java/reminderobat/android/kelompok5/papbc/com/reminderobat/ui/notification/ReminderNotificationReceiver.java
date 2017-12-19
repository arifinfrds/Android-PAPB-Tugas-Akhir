package reminderobat.android.kelompok5.papbc.com.reminderobat.ui.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import reminderobat.android.kelompok5.papbc.com.reminderobat.R;
import reminderobat.android.kelompok5.papbc.com.reminderobat.ui.toturial.ToturialActivity;

/**
 * Created by RizqiAryansa on 12/14/2017.
 */

public class ReminderNotificationReceiver {


    public void Notification(int id, String keterangan, String jam, String namaobat, Context context) {

        Intent notificationIntent = new Intent(context, ToturialActivity.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (context, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context);
        notifyBuilder.setContentTitle(keterangan)
                .setContentText(jam)
                .setSubText(namaobat)
                .setSmallIcon(R.drawable.obat)
                .setContentIntent(notificationPendingIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, notifyBuilder.build());
    }

}
