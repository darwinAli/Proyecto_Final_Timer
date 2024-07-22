package com.example.timerproject.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

public class ReceptorAlarma extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Mostrar un mensaje de alarma
        Toast.makeText(context, "¡Alarma Sonando!", Toast.LENGTH_LONG).show();

        // Vibrar el teléfono
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(2000); // Vibrar por 2 segundos
        }

        // Reproducir sonido de alarma
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        RingtoneManager.getRingtone(context, alarmUri).play();
    }
}
