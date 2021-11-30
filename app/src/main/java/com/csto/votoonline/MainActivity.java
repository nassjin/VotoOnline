package com.csto.votoonline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
RadioButton rb1,rb2,rb3;
Button BTN1,BTN2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        BTN1 = (Button) findViewById(R.id.button);
        BTN2 = (Button) findViewById(R.id.button2);

        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogo();
            }
        });

        BTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(I);
            }
        });


    }

    public void Dialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atencion!");
        builder.setMessage("¿Esta Seguro de Continuar?");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase db;
                voto conn = new voto(getApplicationContext());
                db= conn.getWritableDatabase();
                //DEFNIR UN CONTENEDOR PARA INSERTAR LA INFORMACION
                ContentValues CV = new ContentValues();

                if(rb1.isChecked()) {
                    CV.put("voto_nulo",1);

                }
                if(rb2.isChecked()) {
                    CV.put("voto_boric",1);

                }
                if(rb3.isChecked()) {
                    CV.put("voto_kast",1);

                }else{
                    CV.put("voto_blanco",1);
                }


                db.insert("votos",null,CV);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}