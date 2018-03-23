package lifestats.a350s18_21_lifestats;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.AlarmManager;


/**
 * Created by steph on 3/23/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @TargetApi(26)
    @Override
    public void onReceive(Context context, Intent intent) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = NotificationChannel.DEFAULT_CHANNEL_ID;
            String description = "Default";
            int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID,
                    name,  NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,
                NotificationChannel.DEFAULT_CHANNEL_ID)
                .setContentTitle("Log Goals")
                .setContentText("Remember to log your goals before going to bed.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private void setRecurringAlarm(Context context) {

        //AlarmManager alarms = AlarmManager.set(int type, long triggerAtMillis, PendingIntent operation)
        //alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
         //       updateTime.getTimeInMillis(),
         //       AlarmManager.INTERVAL_DAY, recurringDownload);
    }

}
