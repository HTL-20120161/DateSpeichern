package com.example.user.datenspeichern;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database helper;
    Button add,load;
    EditText name,nachname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new Database(this);

        add = (Button)findViewById(R.id.addButton);
        load = (Button)findViewById(R.id.loadButton);

        name = (EditText) findViewById(R.id.getVorname);
        nachname = (EditText) findViewById(R.id.getNachname);

        Add();
        Load();
    }

    public void Add()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wahr = helper.Insert(name.getText().toString(), nachname.getText().toString());
                if (wahr == true) {
                    Toast.makeText(MainActivity.this, "Daten wurden erfolgreich gespeichert", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Daten konnten nicht gespeichert werden", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    public void Load()
    {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor res = helper.getTable();
                if(res.getCount()== 0)
                {
                    Nachricht("FEHLER","KEINE DATEN");
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext())
                {
                    stringBuffer.append("ID :"+res.getString(0) +"\n");
                    stringBuffer.append("VORNAME :"+res.getString(1)+"\n");
                    stringBuffer.append("NACHNAME:"+res.getString(2)+"\n");

                }
                Nachricht("DATEN",stringBuffer.toString());
            }
        });
    }

    public void Nachricht (String titel , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titel);
        builder.setMessage(message);
        builder.show();
    }





}
