package com.example.safety;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    public void onCreate()
    {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,"SOS Message", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Channel for SOS Message");
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,"SOS Call", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Channel for SOS Call");
            NotificationManager m = getSystemService(NotificationManager.class);
            m.createNotificationChannel(channel1);
            m.createNotificationChannel(channel2);
        }
    }
}
