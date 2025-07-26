package com.communityuni.hocnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i=getIntent();
        String s=i.getStringExtra("CHITIET");
        TextView txtChiTiet= (TextView) findViewById(R.id.txtChiTiet);
        txtChiTiet.setText(s);
    }

    public void xuLyTaoNotification(View view) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentTitle("Có thông báo");
        builder.setContentText("Nhấn vào đây để xem chi tiết");
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent resultIntent=new Intent(this,MainActivity.class);
        String s="Obama, Putin, Kim Jong Un là 3 anh em! họ rất yêu nhau!";
        resultIntent.putExtra("CHITIET",s);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent=
                stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(123,builder.build());
    }

    public void xuLyDongNotification(View view) {
        NotificationManager notificationManager=
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.cancel(123);
    }
}
