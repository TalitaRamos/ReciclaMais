package com.example.simaw.reciclamais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class cadastro extends AppCompatActivity {

    private ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        imageView3 = (ImageView)findViewById(R.id.imageView3) ;

        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(cadastro.this, EscolhaUsuarioActivity.class);
                startActivity(it);
            }
        });
    }
}
