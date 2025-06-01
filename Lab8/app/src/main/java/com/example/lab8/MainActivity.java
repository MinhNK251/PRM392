package com.example.lab8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnSendNotification = findViewById(R.id.btn_send_notification);
        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

//    private void sendNotification(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("Title push notification")
//                .setContentText("Message push notification")
//                .setSmallIcon(R.mipmap.ic_launcher)
////                .setLargeIcon(bitmap)
//                .setColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary))
//                .build();
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if(notificationManager != null)
//            notificationManager.notify(getNotificationId(), notification);
//    }

private void sendNotification(){
    // Step 1: Create Notification Channel (Required for Android 8.0+)
    String channelId = "default_channel_id";
    String channelName = "Default Channel";

    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        NotificationChannel channel = new NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
        );
        notificationManager.createNotificationChannel(channel);
    }

    // Step 2: Build Notification
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    Notification notification = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notification = new Notification.Builder(this, channelId)
                .setContentTitle("Title push notification")
                .setContentText("Message push notification")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .build();
    }

    // Step 3: Show Notification
    if(notificationManager != null)
        notificationManager.notify(getNotificationId(), notification);
}


    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}