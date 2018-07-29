package com.example.simaw.reciclamais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import Util.MaskUtil;

public class cadastroCentro extends AppCompatActivity {
    private EditText cnpj;
    private EditText telefoneCentro;
    private EditText cepCentro;
    private ImageView buttonCadCentro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_centro);

        cnpj = (EditText) findViewById(R.id.cnpj);
        telefoneCentro= (EditText)findViewById(R.id.telefoneCentro);
        cepCentro=(EditText) findViewById(R.id.cepCentro);
        buttonCadCentro=(ImageView)findViewById(R.id.buttonCadCentro);

        cnpj.addTextChangedListener(MaskUtil.mask(cnpj, MaskUtil.FORMAT_CNPJ));
        telefoneCentro.addTextChangedListener(MaskUtil.mask(telefoneCentro, MaskUtil.FORMAT_FONE));
        cepCentro.addTextChangedListener(MaskUtil.mask(cepCentro, MaskUtil.FORMAT_CEP));

        /*buttonCadCentro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(cadastroCentro.this, MainActivityCentro.class);
                startActivity(it);
            }
        });*/

    }
}
