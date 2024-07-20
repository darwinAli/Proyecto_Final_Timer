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
import com.example.timerproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtxNuevoUsuario;
    EditText edtxNuevoEmail;
    EditText edtxNuevoPassword;
    Button btnRegistrarNuevoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtxNuevoUsuario=findViewById(R.id.edtxNuevoUsuario);
        edtxNuevoEmail=findViewById(R.id.edtxNuevoEmail);
        edtxNuevoPassword=findViewById(R.id.edtxNuevoPassword);
        btnRegistrarNuevoUsuario=findViewById(R.id.btnRegistrarNuevoUsuario);


        btnRegistrarNuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //registrar usuario
                String usuario=edtxNuevoUsuario.getText().toString().toLowerCase();


                if(DataManager.getInstance().getUsers().isEmpty()){
                    System.out.println("Agregando nuevo usuario porque esta vacio la tabla users");
                    registerUser();
                    goMain();
                }
                else {
                    //comprobando si hay un usuario que sea igual o no
                    DataManager.getInstance().getUsers().forEach(userr -> {
                        if(userr.getName().equalsIgnoreCase(usuario)){
                            System.out.println("el usuario existe");
                        }
                        else {
                            registerUser();
                            goMain();
                        }
                    });
                }



            }
        });

    }

    private void registerUser(){

        User user=new  User(
                edtxNuevoUsuario.getText().toString().toLowerCase(),
                edtxNuevoEmail.getText().toString().toLowerCase(),
                edtxNuevoPassword.getText().toString()
        );
        DataManager.getInstance().setUsers(user);

    }

    private void goMain(){
        // regresando al menu principal
        Intent goMain=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(goMain);
    }

}