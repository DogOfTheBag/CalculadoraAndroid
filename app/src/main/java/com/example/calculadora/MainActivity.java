package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    StringBuilder sb1;
    StringBuilder sb2;
    static int foco = 1; //foco en el text 1 o 2
    private EditText num1, num2, resultado;

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

        sb1 = new StringBuilder();
        sb2 = new StringBuilder();
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
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        resultado = findViewById(R.id.resultado);

        /*Para que cuando pulsemos un número, el programa sepa donde tiene que ir escrito eso
        * tendremos que establecer una variable que indique al listener de los botones donde se supone
        * que tiene que escribir.
        * Para ello, lo que haré es que el programa escuche donde está el foco que clica el usuario
        * si pulsa en el primer texto, el listener del foco le pasará un 1 al programa, y este lo tendrá listo
        * para cuando pulses los botones, que comprobarán donde están, y pulsaran acorde.*/

        /*basicamente escuchamos el cambio de foco. Con un if comprobamos si el texto actual tiene el foco
        * si lo tiene le diremos con un numero que texto tiene el foco, y escribiremos posteriormente ahi*/
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
        /*Le metemos el listener generico a todos los botones de numeros*/
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

        /*vamos a hacer la raiz cuadrada. La mayor complicación que tiene este listener es primero
        * comprobar si el numero que le introducimos es válido para hacer la raiz. Como siempre, comprobamos el foco
        * y con la funcion de la clase Math tiramos el sqrt facilito
        *
        * Uso aquí la funcion que quita el .0 del resultado, está abajo para referencia*/
        botonSqrt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    if (foco == 1){
                        if(sb1.length() == 0) return;
                        double x = Double.parseDouble(sb1.toString());
                        if(x<0){
                            toast("no se puede hacer un sqrt con negativos");
                            return;
                        }
                        toast("El resultado abajo");
                        resultado.setText(limpiarResultado(Math.sqrt(x)));
                    }else{
                        if(sb2.length() == 0) return;
                        double x = Double.parseDouble(sb2.toString());
                        if(x<0){
                            toast("no se puede hacer un sqrt con negativos");
                            return;
                        }
                        toast("El resultado abajo");
                        resultado.setText(limpiarResultado(Math.sqrt(x)));
                    }
                }catch (NumberFormatException e){
                    toast("Número invalido");
                }
            }
        });

        /*el delete de toda la vida, basicamente se va al final de la cadena en foco y borra el ultimo numero
        * aunque selecciones la mitad de la cadena borra el ultimo, lo he pensado asi en vez de dejar que borren entre medias.*/
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
        //LISTENERS DE LAS OPERACIONES
        /*Voy a usar la funcion de operar que le pasas el operador y te hace la cuenta, abajo esta explicada*/
        botonSuma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operar('+');
            }
        });

        botonResta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operar('-');
            }
        });

        botonMult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operar('*');
            }
        });

        botonDiv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operar('/');
            }
        });
    } /*AQUI ACABA EL ON CREATE, EN EL ON CREATE SOLO METEMOS LO QUE QUERAMOS QUE SE CREE AL ABRIR LA APP
        ESO QUIERE DECIR QUE FUNCIONES AUXILIARES Y ESO FUERA, AQUI DENTRO LOS LISTENERS, FOCOS, BOTONES, VARIABLES QUE SE USEN FUERA TAMBIEN AL PRINCIPIO */



        /*Voy a hacer aquí abajo las funciones internas que estamos usando, como la de operar, la de si tiene punto...*/
        /*********************************************FUNCIONES AUXILIARES*********************************************/


/*        EJEMPLO DE EVENTO
*         boton0setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick(View v){


            }
        });
* */

    /*Creo que la he explicado antes pero esta funcion busca si hay ya un punto de decimal, si lo hay no te deja poner otro*/
    private Boolean noTienePunto(StringBuilder sb){
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '.')
                return false;
        }
        return true;
    }

    /*La funcion de las operaciones, un switch que dependiendo del boton que hayas pulsado hace una operación*/
    private void operar(char op){
        if(sb1.length() == 0 || sb2.length() == 0) {
            toast("Tienen que haber números en ambos textos para operar");
            return;
        }
        try {
            double a = Double.parseDouble(sb1.toString());
            double b = Double.parseDouble(sb2.toString());
            double r;
            switch (op) {
                case '+':
                    r = a + b;
                    break;

                case '-':
                    r = a - b;
                    break;

                case '*':
                    r = a * b;
                    break;
                //No le dejamos dividir entre 0 que la gente es muy mala
                case '/':
                    if (b == 0) {
                        toast("No se puede dividir entre 0.");
                        return;
                    }
                    r = a / b;
                    break;

                default:
                    return;
            }
            resultado.setText(limpiarResultado(r));
        }catch(NumberFormatException e){
            toast("Solo podemos manejar numeros.");
        }
    }

    /*Esta funcion lo que hace es que si el resultado te salgo 475, como es double te va a salir como 475.0
    * lo que hace es quitar ese .0 de los numeros asi, los demas los deja*/
    private String limpiarResultado (double numero){
        String resultadoLimpio;
        /*el value of hace un poco como el toString, pero es más seguro a la hora de convertir valores primitivos para evitar errores
        * si haces un toString puede lanzar NullPointerException o errores del palo y este tira mejor*/
        resultadoLimpio = String.valueOf(numero);
        if(resultadoLimpio.endsWith(".0")) resultadoLimpio = resultadoLimpio.substring(0, resultadoLimpio.length()-2);

        return resultadoLimpio;
    }

    /*He encontrado esto de los Toast, que basicamente son notifiaciones modales, parecidas a JOptionPane en Swing
    * o sea que si tengo algo de error para el usuario puedo usar esto, le pasas el mensaje en la funcion y te la lanza*/
    private void toast(String m) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }
}