package com.example.timerproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.timerproject.bean.DataManager;

public class MainActivity extends AppCompatActivity {


    EditText edtxUsuario;
    EditText edtxPassword;
    Button btnRegistrarse;
    Button btnIngresar;
    Button btnIngresoNoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegistrarse=findViewById(R.id.btnRegistrarse);
        edtxUsuario=findViewById(R.id.edtxUsuario);
        edtxPassword=findViewById(R.id.edtxPassword);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnIngresoNoUsuario=findViewById(R.id.btnIngresoNoUsuario);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click en ingresar");

                // logica para saber si existe el usuario ingresado

               String usuario=edtxUsuario.getText().toString().toLowerCase();
               String password=edtxPassword.getText().toString();

               if(DataManager.getInstance().getUsers().isEmpty()){
                   System.out.println("Esta vacio");
               }
               else{
                   DataManager.getInstance().getUsers().forEach(user -> {
                       if(user.getName().equalsIgnoreCase(usuario)){
                           System.out.println("usuario correcto:"+usuario);
                           if(user.getPassword().equals(password)){
                               System.out.println("password correcto:"+password);

                               DataManager.getInstance().setCurrentUser(user);

                               Intent goMenuPrincipal= new Intent(MainActivity.this,MenuPrincipalActivity.class);
                               startActivity(goMenuPrincipal);
                           }
                           else {
                               System.out.println("password incorrecto:"+password);
                           }
                       }
                       else {
                           System.out.println("usuario no existe:"+usuario);
                       }
                   });
               }



            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLogin=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(goLogin);
            }
        });

       btnIngresoNoUsuario.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goMenuPrincipal=new Intent(MainActivity.this,MenuPrincipalActivity.class);
               startActivity(goMenuPrincipal);
           }
       });
    }
}