package com.example.timerproject;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class TemporizadorActivity extends AppCompatActivity {


    TextView txvTimeTemporizador;
    Button btnIniciarTemporizador;
    Button btnDetenerTemporizador;
    Button btnReniciarTemporizador;
    Button btnSetTime;
    Button btnCambiarTime;


    CountDownTimer countDownTimer;
    boolean isRunning = false;
    long tiempoUsuario;

    String tiempoUsuarioString;
    long startTimeInMillis;
    long tiempoTranscurrido;

    long tiempoRestante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temporizador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvTimeTemporizador=findViewById(R.id.txvTimeTemporizador);
        btnIniciarTemporizador=findViewById(R.id.btnIniciarTemporizador);
        btnDetenerTemporizador=findViewById(R.id.btnDetenerTemporizador);
        btnReniciarTemporizador=findViewById(R.id.btnReniciarTemporizador);
        btnSetTime=findViewById(R.id.btnSetTime);
        btnCambiarTime=findViewById(R.id.btnCambiarTime);

        btnIniciarTemporizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 System.out.println("presionaste el boton iniciar");
                iniciarTemporizador();
            }
        });

        btnDetenerTemporizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste el boton detener");
                stopTemporizador();
            }
        });

        btnReniciarTemporizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste el boton reniciar");
                reniciarTemporizador();
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste setTime");

                showTimePickerDialog();

            }
        });

        btnCambiarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("presionaste cambiar tiempo");

                showTimePickerDialog();

            }
        });
    }

    private void showTimePickerDialog() {
        // Obtén la hora actual
        final Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        // Crea el TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tiempoUsuario = (hourOfDay * 3600 + minute * 60) * 1000;
                        txvTimeTemporizador.setText(String.format("Selected Time: %02dh : %02dm", hourOfDay, minute));
                    }
                }, currentHour, currentMinute, true);

        timePickerDialog.show();
    }

    private void iniciarTemporizador(){

        txvTimeTemporizador.setVisibility(View.VISIBLE);
        btnSetTime.setVisibility(View.INVISIBLE);
         if(!isRunning){

             countDownTimer=new CountDownTimer(tiempoUsuario, 1000) {
                 @Override
                 public void onTick(long millisUntilFinished) {
                     tiempoTranscurrido  = System.currentTimeMillis() - startTimeInMillis;
                     tiempoRestante= tiempoUsuario-tiempoTranscurrido;
                     actualizarInterfaz();
                 }

                 @Override
                 public void onFinish() {
                    txvTimeTemporizador.setText("TIEMPO TERMINADO");
                 }
             };


             startTimeInMillis = System.currentTimeMillis();
             countDownTimer.start();
             isRunning = true;
         }
    }

    private void stopTemporizador(){
        if (isRunning) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    private  void reniciarTemporizador(){
        stopTemporizador();
        int secs = (int) (tiempoUsuario / 1000);
        int mins = secs / 60;
        int hrs = mins / 60;

        secs = secs % 60;
        mins = mins % 60;
        txvTimeTemporizador.setText(String.format(" %02dh : %02dm : %02ds",hrs, mins, secs));

    }

    private void actualizarInterfaz(){
        int secs = (int) (tiempoRestante / 1000);
        int mins = secs / 60;
        int hrs = mins / 60;

        secs = secs % 60;
        mins = mins % 60;

        txvTimeTemporizador.setText(String.format(" %02dh : %02dm : %02ds",hrs, mins, secs));
    }

}