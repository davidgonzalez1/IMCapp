package com.ramon12.android.imcapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextPeso;
    private EditText mEditTextEstatura;
    private Button mButtonCacular;
    private TextView mTextViewImc;
    private TextView mTextViewEstado;
    private Button mButtonLimpiar;

    public void ocultarteclado(){
        InputMethodManager OCUT = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        OCUT.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPeso = (EditText) findViewById(R.id.edit_text_peso);
        mEditTextEstatura = (EditText) findViewById(R.id.edit_text_estatura);
        mButtonCacular = (Button) findViewById(R.id.button_calcular);
        mTextViewImc = (TextView) findViewById(R.id.text_view_imc);
        mTextViewEstado = (TextView) findViewById(R.id.text_view_nutricional);
        mButtonLimpiar = (Button) findViewById(R.id.button_limpiar);
        mButtonLimpiar.setVisibility(View.INVISIBLE);

        mButtonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View o) {
                mTextViewEstado.setText("");
                mTextViewImc.setText("");
                mEditTextEstatura.setText("");
                mEditTextPeso.setText("");
                double peso = 0,estatura = 0;
                mButtonLimpiar.setVisibility(View.INVISIBLE);
        }
        });

        mButtonCacular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultar teclado
                ocultarteclado();
try {
    if( mEditTextPeso.length() > 0 && mEditTextEstatura.length() > 0){
        double peso = Double.parseDouble(mEditTextPeso.getText().toString());
        double estatura = Double.parseDouble(mEditTextEstatura.getText().toString());
        DecimalFormat df = new DecimalFormat("0.00");
        double IMC = peso / (estatura * estatura);
        String dato = df.format((Double) IMC);
        mTextViewImc.setText(dato);
        double imc = IMC;
        if (imc <= 18.49) {
            mTextViewEstado.setText("Peso Bajo ");
        } else if (imc <= 24.99) {
            mTextViewEstado.setText("Peso Normal ");
        } else if (imc <= 29.99) {
            mTextViewEstado.setText("SobrePeso ");
        } else if (imc <= 39.99) {
            mTextViewEstado.setText("Obesidad ");
        } else if (imc >= 40.0) {
            mTextViewEstado.setText("Obesidad Estrema ");
        }
        mButtonLimpiar.setVisibility(View.VISIBLE);
    }else{
        mButtonLimpiar.setVisibility(View.INVISIBLE);
    }

}catch (NumberFormatException w){

    mTextViewEstado.setText(w.getMessage());
               }
            }
        });
    }
}
