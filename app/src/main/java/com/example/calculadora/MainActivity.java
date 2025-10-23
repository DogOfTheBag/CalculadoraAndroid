package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    StringBuilder sb1;
    StringBuilder sb2;
    static int foco = 1; //foco en el text 1 o 2
    private editText num1, num2, resultado;

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
/*******************************AQUI EMPIEZA LO MIO***************************/

        Button boton0 = findViewById(R.id.button0);
        Button boton1 = findViewById(R.id.button1);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);
        Button boton4 = findViewById(R.id.button4);
        Button boton5 = findViewById(R.id.button5);
        Button boton6 = findViewById(R.id.button6);
        Button boton7 = findViewById(R.id.button7);
        Button boton8 = findViewById(R.id.button8);
        Button boton9 = findViewById(R.id.button9);
        Button botonClear = findViewById(R.id.buttonClear);
        Button botonDel = findViewById(R.id.buttonDel);
        Button botonSqrt = findViewById(R.id.buttonsqrt);
        Button botonDiv = findViewById(R.id.buttonDiv);
        Button botonMult = findViewById(R.id.buttonMult);
        Button botonResta = findViewById(R.id.buttonResta);
        Button botonSuma = findViewById(R.id.buttonSuma);
        Button botonPunto = findViewById(R.id.buttonPunto);
        EditText num1 = findViewById(R.id.num1);
        EditText num2 = findViewById(R.id.num2);
        EditText resultado = findViewById(R.id.resultado);


        num1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v ,boolean hasFocus){
                if(hasFocus) foco = 1;
            }
        });

        boton0.setOnClickListener(digitListener);
        boton1.setOnClickListener(digitListener);
        boton2.setOnClickListener(digitListener);
        boton3.setOnClickListener(digitListener);
        boton4.setOnClickListener(digitListener);
        boton5.setOnClickListener(digitListener);
        boton6.setOnClickListener(digitListener);
        boton7.setOnClickListener(digitListener);
        boton8.setOnClickListener(digitListener);
        boton9.setOnClickListener(digitListener);




        View.OnClickListener digitListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = ((Button) v).getText().toString();
                if (foco == 1) {
                    sb1.append(d);
                    num1.setText(sb1.toString());
                } else {
                    sb2.append(d);
                    num2.setText(sb2.toString());
                }
            }
        };


/*        EJEMPLO DE EVENTO
*         boton0setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick(View v){


            }
        });
* */

    }
}