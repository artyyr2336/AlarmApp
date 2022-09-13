package com.example.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonAlarm = findViewById<Button>(R.id.alarm_button)

        buttonAlarm.setOnClickListener {

            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(0)
                    .setTitleText("Введите время")
                    .build()




            picker.addOnPositiveButtonClickListener {


                val calendar = Calendar.getInstance()

                val h = calendar.get(Calendar.HOUR_OF_DAY)
                val m = calendar.get(Calendar.MINUTE)


                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.MINUTE, picker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, picker.hour)

                if (h > picker.hour.toInt() || (h == picker.hour.toInt() && m > picker.minute.toInt())) {
                    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
                }


                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val alarmClockInfo =
                    AlarmManager.AlarmClockInfo(calendar.timeInMillis, getAlarmPendingIntent())
                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent())
                Toast.makeText(this, "Будильник установлен", Toast.LENGTH_SHORT).show()
            }


            picker.show(supportFragmentManager, "tag_picker")
        }
    }

    private fun getAlarmPendingIntent(): PendingIntent {

        val alarmInfoIntent = Intent(this, MainActivity::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(
            this,
            0,
            alarmInfoIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getAlarmActionPendingIntent(): PendingIntent {
        val intent = Intent(this, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(
            this,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}