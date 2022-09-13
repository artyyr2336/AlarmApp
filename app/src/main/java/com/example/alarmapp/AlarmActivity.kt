package com.example.alarmapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Camera
import android.hardware.camera2.CameraDevice
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.os.health.TimerStat
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.*

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var ringtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var ringtone = RingtoneManager.getRingtone(this, ringtoneUri)

        if (ringtone === null) {
            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, ringtoneUri)

        } else {
            ringtone.play()
        }

        findViewById<Button>(R.id.alarm_button_stop).setOnClickListener {
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
            ringtone.stop()
        }



    }


}


