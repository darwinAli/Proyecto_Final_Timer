package com.example.timerproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class RelojMundialActivity extends AppCompatActivity {

   TextView txvFechaHoraBuenosAires;
   TextView txvFechaHoraMadrid;
   TextView txvFechaHoraMonteVideo;
   TextView txvFechaHoraTokyo;

    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reloj_mundial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvFechaHoraBuenosAires=findViewById(R.id.txvFechaHoraBuenosAires);
        txvFechaHoraMadrid=findViewById(R.id.txvFechaHoraMadrid);
        txvFechaHoraMonteVideo=findViewById(R.id.txvFechaHoraMonteVideo);
        txvFechaHoraTokyo=findViewById(R.id.txvFechaHoraTokyo);



      actualizarInterfaz();
    }

    private void buenosAires(){

        TimeZone timeZone = TimeZone.getTimeZone("America/Argentina/Buenos_Aires");
        Calendar calendar= Calendar.getInstance(timeZone);
        SimpleDateFormat formatearFecha= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatearFecha.setTimeZone(timeZone);
        String currentTimeInBuenosAires = formatearFecha.format(calendar.getTime());
        System.out.println("fecha y horario buenos aires final:"+currentTimeInBuenosAires);
        txvFechaHoraBuenosAires.setText(currentTimeInBuenosAires);

    }

    private void madrid(){
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Madrid");
        Calendar calendar= Calendar.getInstance(timeZone);
        SimpleDateFormat formatearFecha= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatearFecha.setTimeZone(timeZone);
        String currentTimeInMadrid = formatearFecha.format(calendar.getTime());
        txvFechaHoraMadrid.setText(currentTimeInMadrid);
    }
    private void monteVideo(){
        TimeZone timeZone = TimeZone.getTimeZone("America/Montevideo");
        Calendar calendar= Calendar.getInstance(timeZone);
        SimpleDateFormat formatearFecha= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatearFecha.setTimeZone(timeZone);
        String currentTimeInMonteVideo = formatearFecha.format(calendar.getTime());
        txvFechaHoraMonteVideo.setText(currentTimeInMonteVideo);
    }
    private void tokyo(){
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
        Calendar calendar= Calendar.getInstance(timeZone);
        SimpleDateFormat formatearFecha= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatearFecha.setTimeZone(timeZone);
        String currentTimeInTokyo = formatearFecha.format(calendar.getTime());
        txvFechaHoraTokyo.setText(currentTimeInTokyo);
    }

    private void actualizarInterfaz(){

        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                buenosAires();
                madrid();
                monteVideo();
                tokyo();
            }

            @Override
            public void onFinish() {

            }

        };
        countDownTimer.start();

    }
}