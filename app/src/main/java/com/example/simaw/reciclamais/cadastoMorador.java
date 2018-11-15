package com.example.simaw.reciclamais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.simaw.reciclamais.Util.MaskUtil;


public class cadastoMorador extends AppCompatActivity {
    private EditText cpf;
    private EditText telefoneMorador;
    private EditText cepMorador;
    private EditText nascimentoMorador;
    private EditText nomeMorador;
    private ImageView buttonCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadasto_morador);

        nomeMorador=(EditText)findViewById(R.id.nomeMorador);
        cpf = (EditText) findViewById(R.id.cpf);
        telefoneMorador= (EditText)findViewById(R.id.telefoneMorador);
        cepMorador=(EditText) findViewById(R.id.cepMorador);
        nascimentoMorador=(EditText)findViewById(R.id.idadeMorador);
        buttonCad=(ImageView)findViewById(R.id.buttonCad);

        //ADICIONANDO AS MASCARAS
        cpf.addTextChangedListener(MaskUtil.mask(cpf, MaskUtil.FORMAT_CPF));
        telefoneMorador.addTextChangedListener(MaskUtil.mask(telefoneMorador, MaskUtil.FORMAT_FONE));
        cepMorador.addTextChangedListener(MaskUtil.mask(cepMorador, MaskUtil.FORMAT_CEP));
        nascimentoMorador.addTextChangedListener(MaskUtil.mask(nascimentoMorador,MaskUtil.FORMAT_DATE));

        buttonCad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(cadastoMorador.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}
