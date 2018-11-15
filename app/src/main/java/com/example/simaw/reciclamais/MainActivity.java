package com.example.simaw.reciclamais;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private LineChartView lineChartView;
    private LinearLayout linearLayout;
    private LinearLayout linearReclamacao;
    private Button btn;
    private EditText sugestao;
    private ImageButton imgBtn;
    private final Boolean[] modoAnonimo = {false};
    private TextView nomeUsuario;
    private Spinner spinnerEscolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        linearLayout = (LinearLayout) findViewById(R.id.caixa_layout);
        linearReclamacao = (LinearLayout)findViewById(R.id.caixa_layout_reclamacao);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawSinAbsChart ();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSugestao(true);
            }
        });

        linearReclamacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSugestao(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void drawSinAbsChart() {
        String decimalPattern = "#.##";
        DecimalFormat decimalFormat = new DecimalFormat(decimalPattern);

        lineChartView = (LineChartView) findViewById(R.id.chart);

        List<PointValue> values = new ArrayList<PointValue>();

        PointValue tempPointValue;
        for (float i = 0; i <= 360.0; i+= 15.0f) {
            tempPointValue = new PointValue(i, Math.abs((float)Math.sin(Math.toRadians(i))));
            tempPointValue.setLabel(decimalFormat
                    .format(Math.abs((float)Math.sin(Math.toRadians(i)))));
            values.add(tempPointValue);
        }

        Line line = new Line(values)
                .setColor(Color.GREEN)
                .setCubic(false)
                .setHasPoints(true).setHasLabels(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        List<AxisValue> axisValuesForX = new ArrayList<>();
        List<AxisValue> axisValuesForY = new ArrayList<>();
        AxisValue tempAxisValue;
        for (float i = 0; i <= 360.0f; i += 30.0f){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(i+"\u00b0");
            axisValuesForX.add(tempAxisValue);
        }

        for (float i = 0.0f; i <= 1.00f; i += 0.25f){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(""+i);
            axisValuesForY.add(tempAxisValue);
        }

        Axis xAxis = new Axis(axisValuesForX);
        Axis yAxis = new Axis(axisValuesForY);
        data.setAxisXBottom(xAxis);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
    }

    public void showDialogSugestao(Boolean tipoAcao){
        final String[] validador = new String[1];
        final LovelyCustomDialog alertDialog = new LovelyCustomDialog(this)
                .setCancelable(true)
                .setTopColorRes(R.color.md_green_500)
                .setTopTitleColor(getResources().getColor(R.color.md_white_1000))
                .setIcon(R.drawable.ic_question)
                .setTitleGravity(Gravity.CENTER)
                .setMessageGravity(Gravity.CENTER)
                .setTopTitle("Sugestão".toUpperCase())
                .setView(R.layout.layout_dialog_sugestao);

        alertDialog.configureView(new LovelyCustomDialog.ViewConfigurator() {
            @Override
            public void configureView(View v) {
            }
        });

        if(tipoAcao){
            alertDialog.configureView(new LovelyCustomDialog.ViewConfigurator() {
                @Override
                public void configureView(View v) {
                    btn = (Button)v.findViewById(R.id.enviar_sugestao);
                    btn.setEnabled(false);
                    btn.setTextColor(getResources().getColor(R.color.md_grey_500));
                    sugestao = (EditText)v.findViewById(R.id.sugestao_editText);
                    imgBtn = (ImageButton)v.findViewById(R.id.anomin_btn);
                    nomeUsuario = (TextView)v.findViewById(R.id.nome_usuario);
                    spinnerEscolha = (Spinner)v.findViewById(R.id.spinner);
                    spinnerEscolha.setVisibility(View.GONE);
                   //spinnerEscolha.setAdapter(new ArrayAdapter<destinatarioEnum>(,destinatarioEnum.values()));

                    imgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!modoAnonimo[0]){
                                nomeUsuario.setBackgroundColor(getResources().getColor(R.color.md_grey_200));
                                Toast.makeText(getApplicationContext(), "Seus dados não serão divulgados", Toast.LENGTH_LONG).show();
                                modoAnonimo[0] = true;
                            }else{
                                nomeUsuario.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
                                Toast.makeText(getApplicationContext(), "Modo anônimo desligado", Toast.LENGTH_LONG).show();
                                modoAnonimo[0] = false;
                            }
                        }
                    });

                    sugestao.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            validador[0]= s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(!validador[0].equals("")||!validador[0].isEmpty()){
                                btn.setEnabled(true);
                                btn.setTextColor(getResources().getColor(R.color.actionbar_dark));
                            }else{
                                btn.setEnabled(false);
                                btn.setTextColor(getResources().getColor(R.color.md_grey_500));
                            }
                        }
                    });
                }
            });

            alertDialog.setListener(R.id.enviar_sugestao, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //salvar sugestão
                    Toast.makeText(getApplicationContext(), "Agradecemos sua sugestão!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            alertDialog.setListener(R.id.cancel_sugestao, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }else{
            //SE FOR RECLAMAÇÃO
            alertDialog.configureView(new LovelyCustomDialog.ViewConfigurator() {
                @Override
                public void configureView(View v) {
                    btn = (Button)v.findViewById(R.id.enviar_sugestao);
                    btn.setEnabled(false);
                    btn.setTextColor(getResources().getColor(R.color.md_grey_500));
                    sugestao = (EditText)v.findViewById(R.id.sugestao_editText);
                    imgBtn = (ImageButton)v.findViewById(R.id.anomin_btn);

                    nomeUsuario = (TextView)v.findViewById(R.id.nome_usuario);
                    spinnerEscolha = (Spinner)v.findViewById(R.id.spinner);
                    spinnerEscolha.setVisibility(View.GONE);

                    alertDialog.setTopTitle("Reclamação".toUpperCase());
                    alertDialog.setIcon(R.drawable.ic_emoji);
                    sugestao.setHint("Digite sua reclamação");

                    imgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!modoAnonimo[0]){
                                nomeUsuario.setBackgroundColor(getResources().getColor(R.color.md_grey_200));
                                Toast.makeText(getApplicationContext(), "Seus dados não serão divulgados", Toast.LENGTH_LONG).show();
                                modoAnonimo[0] = true;
                            }else{
                                nomeUsuario.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
                                Toast.makeText(getApplicationContext(), "Modo anônimo desligado", Toast.LENGTH_LONG).show();
                                modoAnonimo[0] = false;
                            }
                        }
                    });

                    sugestao.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            validador[0]= s.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(!validador[0].equals("")||!validador[0].isEmpty()){
                                btn.setEnabled(true);
                                btn.setTextColor(getResources().getColor(R.color.actionbar_dark));
                            }else{
                                btn.setEnabled(false);
                                btn.setTextColor(getResources().getColor(R.color.md_grey_500));
                            }
                        }
                    });
                }
            });

            alertDialog.setListener(R.id.enviar_sugestao, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //salvar reclamação
                    Toast.makeText(getApplicationContext(), "Reclamação salva com sucesso!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            alertDialog.setListener(R.id.cancel_sugestao, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }


        alertDialog.show();
    }
}

