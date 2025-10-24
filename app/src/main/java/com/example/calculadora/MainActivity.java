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

/*Comenzamos definiendo todos los elementos que vayamos a usar para poder manejarlos prgramando*/

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
       //Button botonSqrt = findViewById(R.id.buttonsqrt);
        Button botonDiv = findViewById(R.id.buttonDiv);
        Button botonMult = findViewById(R.id.buttonMult);
        Button botonResta = findViewById(R.id.buttonResta);
        Button botonSuma = findViewById(R.id.buttonSuma);
        Button botonPunto = findViewById(R.id.buttonPunto);
        EditText num1 = findViewById(R.id.num1);
        EditText num2 = findViewById(R.id.num2);
        EditText resultado = findViewById(R.id.resultado);

        /*Para que cuando pulsemos un número, el programa sepa donde tiene que ir escrito eso
        * tendremos que establecer una variable que indique al listener de los botones donde se supone
        * que tiene que escribir.
        * Para ello, lo que haré es que el programa escuche donde está el foco que clica el usuario
        * si pulsa en el primer texto, el listener del foco le pasará un 1 al programa, y este lo tendrá listo
        * para cuando pulses los botones, que comprobarán donde están, y pulsaran acorde.*/


        num1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v ,boolean hasFocus){
                if(hasFocus) foco = 1;
            }
        });

        num2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v ,boolean hasFocus){
                if(hasFocus) foco = 2;
            }
        });


        /*Por si acaso nos falla también podremos establecer el foco con el onClickListener, funciona igual*/
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foco = 1;
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foco = 2;
            }
        });

        //agarrense porque este listener tiene miga
        /*Vamos a hacer un listener general para los 10 numeros. Esto quiere decir que en vez de tener que hacer
        * 10 listener individuales, el listener de aquí cogerá el texto que esta puesto en el boton (en este caso
        * el respectivo numero) y lo juntará a la cadena de numeros que tenemos ya puesta.
        * De esta forma podremos ir añadiendo numeros sin que se borren los anteriores.*/
        View.OnClickListener digitListener = new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //casteamos la vista momentaneamente al boton para coger el texto y pasarlo a cadena, y guardarlo
                String d = ((Button) v).getText().toString();
                if(foco == 1) {
                    /*Esta primera linea se encarga de comprobar si el primer numero es 0, para que si pones 01 no te deje,
                    * te borra el 0 y te queda 1 solo*/
                    if (sb1.length() == 1 && sb1.charAt(0) == '0') sb1.setLength(0);
                        /*juntamos el numero al StringBuilder que tenemos y lo establecemos como texto*/
                        sb1.append(d);
                        num1.setText(sb1.toString());
                        /*Esta linea la hacemos para que cuando introduzcamos el numero, se recoloque la barra
                        * donde escribamos el siguiente al final de lo escrito, si no se va al principio
                        * Podriamos usar sb1.getLenght() pero esto es mejor ya que usamos el texto que se ve y nos evitamos errores*/
                        num1.setSelection(num1.getText().length());

                     } else{
                    if (sb2.length() == 1 && sb2.charAt(0) == '0') sb2.setLength(0);
                        sb2.append(d);
                        num2.setText(sb2.toString());
                        num2.setSelection(num2.getText().length());
                }
            }
        };

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

        /*Este listener lo usamos para el punto del decimal, teniendo en cuenta el caso de que pongas punto directamente
        * para que te ponga solo un cero antes
        * El funcionamiento es casi lo mismo que el numero, pero primero tiene que comprobar con una función auxiliar si hay un punto en la cadena.*/
        botonPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foco == 1) {
                    if (noTienePunto(sb1)) {
                        if (sb1.length() == 0) sb1.append('0');
                        sb1.append('.');
                        num1.setText(sb1.toString());
                        num1.setSelection(num1.getText().length());
                    }
                } else {
                    if (noTienePunto(sb2)) {
                        if (sb2.length() == 0) sb2.append('0');
                        sb2.append('.');
                        num2.setText(sb2.toString());
                        num2.setSelection(num2.getText().length());
                    }
                }
            }
        }); //final listener


        botonDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (foco == 1) {
                    if (sb1.length() > 0) sb1.deleteCharAt(sb1.length() - 1);
                    num1.setText(sb1.toString());
                    num1.setSelection(num1.getText().length());
                } else {
                    if (sb2.length() > 0) sb2.deleteCharAt(sb2.length() - 1);
                    num2.setText(sb2.toString());
                    num2.setSelection(num2.getText().length());
                }
            }
        }); //final listener


        //el boton del clear que limpia todo
        botonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Reiniciamos todo, los stringBuilder a 0, textos vacios y el foco al primero*/
                sb1.setLength(0);
                sb2.setLength(0);
                num1.setText("");
                num2.setText("");
                resultado.setText("");
                foco = 1;
            }
        });



/*        EJEMPLO DE EVENTO
*         boton0setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick(View v){


            }
        });
* */

    }
}