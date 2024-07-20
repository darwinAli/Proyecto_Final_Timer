package com.example.timerproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.util.Timer;

public class CronometroActivity extends AppCompatActivity {

    TextView txvTime;
    TextView txvMiliSegundos;
    Button btnIniciar;
    Button btnDetener;
    Button btnReniciar;



    CountDownTimer   countDownTimer;
    boolean isRunning = false;

    long tiempoTranscurrido;

    long startTimeInMillis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cronometro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvTime=findViewById(R.id.txvTime);
        txvMiliSegundos=findViewById(R.id.txvMiliSegundos);
        btnIniciar=findViewById(R.id.btnIniciar);
        btnDetener=findViewById(R.id.btnDetener);
        btnReniciar=findViewById(R.id.btnReniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               System.out.println("presionaste btnIniciar");
                startChronometer();


            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste btnDetener");
                stopChronometer();


            }
        });

        btnReniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste btnReniciar");
                resetChronometer();


            }
        });


    }

    private void startChronometer() {
        if (!isRunning) {

            countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1) {
                @Override
                public void onTick(long millisUntilFinished) {

                    tiempoTranscurrido  = System.currentTimeMillis() - startTimeInMillis;
                    actualizarInterfaz();
                }

                @Override
                public void onFinish() {

                }
            };
            startTimeInMillis = System.currentTimeMillis();
            countDownTimer.start();
            isRunning = true;
        }
    }

    private void stopChronometer() {
        if (isRunning) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    private void resetChronometer() {
        stopChronometer();
        startTimeInMillis = 0L;
        tiempoTranscurrido  = 0L;
        txvTime.setText("00:00:00");
        txvMiliSegundos.setText(".000");
    }

    private void actualizarInterfaz() {
        int secs = (int) (tiempoTranscurrido / 1000);
        int mins = secs / 60;
        int hrs = mins / 60;


        secs = secs % 60;
        mins = mins % 60;

        int milliseconds = (int) (tiempoTranscurrido  % 1000);

        txvTime.setText(String.format(" %02dh : %02dm : %02ds",hrs, mins, secs));
        txvMiliSegundos.setText(String.format(".%03d", milliseconds));
    }



}