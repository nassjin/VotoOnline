package com.csto.votoonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
Button BTON_VOLVER;
TextView v_blanco,v_nulo,v_boric,v_kast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BTON_VOLVER = (Button) findViewById(R.id.btn_volver);
        v_blanco =(TextView) findViewById(R.id.txt_blanco);
        v_nulo =(TextView) findViewById(R.id.txt_nulo);
        v_boric =(TextView) findViewById(R.id.txt_boric);
        v_kast =(TextView) findViewById(R.id.txt_kast);
        SQLiteDatabase db;
        voto conn = new voto(getApplicationContext());
        db= conn.getReadableDatabase();
        Cursor C =db.query("votos",null,null,null,null,null,null);
        Integer TotalVotoBlanco=0,TotalVotoNulo=0,TotalVotoBoric=0,TotalVotoKast=0;
        if(C!=null)
        {
            if(C.moveToFirst())
            {
                do{
                    int v_blanco = C.getInt(1);
                    int v_nulo = C.getInt(2);
                    int v_boric = C.getInt(3);
                    int v_kast = C.getInt(4);

                    if(v_blanco==1)
                    {
                        TotalVotoBlanco++;
                    }
                    if (v_nulo==1){
                        TotalVotoNulo++;

                    }
                    if (v_boric==1){
                        TotalVotoBoric++;
                    }
                    if (v_kast==1){
                        TotalVotoKast++;
                    }


                }
                while(C.moveToNext());
            }
            v_blanco.setText(""+TotalVotoBlanco);
            v_nulo.setText(""+TotalVotoNulo);
            v_boric.setText(""+TotalVotoBoric);
            v_kast.setText(""+TotalVotoKast);

            BTON_VOLVER.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I = new Intent(getApplicationContext(),MainActivity.class);

                    startActivity(I);
                }
            });

        }
    }
}