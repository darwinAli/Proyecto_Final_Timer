package com.example.timerproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.timerproject.bean.DataManager;

public class MenuPrincipalActivity extends AppCompatActivity {

    TextView  txvUsuario;
    Button btnCronometro;
    Button btnRelojMundial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCronometro=findViewById(R.id.btnCronometro);
        btnRelojMundial=findViewById(R.id.btnRelojMundial);

        if(DataManager.getInstance().getCurrentUser()!=null){
            String name= DataManager.getInstance().getCurrentUser().getName();
            txvUsuario=findViewById(R.id.txvUsuario);
            txvUsuario.setText("Bienvenido "+name);
        }
        else{
            txvUsuario=findViewById(R.id.txvUsuario);
            txvUsuario.setText("Bienvenido VISITANTE");
        }

        btnCronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCronometro=new Intent(MenuPrincipalActivity.this,CronometroActivity.class);
                startActivity(goCronometro);
            }
        });

        btnRelojMundial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRelojMundial=new Intent(MenuPrincipalActivity.this,RelojMundialActivity.class);
                startActivity(goRelojMundial);
            }
        });


    }
}