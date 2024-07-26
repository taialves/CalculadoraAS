package com.calculadora.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultado : TextView = findViewById(R.id.resultado)
        val expressao : TextView = findViewById(R.id.expressao)
        var historico = "";

        findViewById<TextView>(R.id.num0).setOnClickListener{AcrescentarUmaExpressao("0",true)}
        findViewById<TextView>(R.id.num1).setOnClickListener{AcrescentarUmaExpressao("1",true)}
        findViewById<TextView>(R.id.num2).setOnClickListener{AcrescentarUmaExpressao("2",true)}
        findViewById<TextView>(R.id.num3).setOnClickListener{AcrescentarUmaExpressao("3",true)}
        findViewById<TextView>(R.id.num4).setOnClickListener{AcrescentarUmaExpressao("4",true)}
        findViewById<TextView>(R.id.num5).setOnClickListener{AcrescentarUmaExpressao("5",true)}
        findViewById<TextView>(R.id.num6).setOnClickListener{AcrescentarUmaExpressao("6",true)}
        findViewById<TextView>(R.id.num7).setOnClickListener{AcrescentarUmaExpressao("7",true)}
        findViewById<TextView>(R.id.num8).setOnClickListener{AcrescentarUmaExpressao("8",true)}
        findViewById<TextView>(R.id.num9).setOnClickListener{AcrescentarUmaExpressao("9",true)}
        findViewById<TextView>(R.id.ponto).setOnClickListener{AcrescentarUmaExpressao(".",true)}
        findViewById<TextView>(R.id.historico).setOnClickListener{
            if(historico.isNotBlank()){
                AcrescentarUmaExpressao(historico,true)
            }
        }

        // operadores

        val soma = findViewById<TextView>(R.id.soma).setOnClickListener{AcrescentarUmaExpressao(" + ", false)}
        val subtracao = findViewById<TextView>(R.id.subtracao).setOnClickListener{AcrescentarUmaExpressao(" - ", false)}
        val multiplicacao = findViewById<TextView>(R.id.multiplicacao).setOnClickListener{AcrescentarUmaExpressao(" X ", false)}
        val divisao = findViewById<TextView>(R.id.divisao).setOnClickListener{AcrescentarUmaExpressao(" / ", false)}


        val limpar = findViewById<TextView>(R.id.limpar).setOnClickListener{
            expressao.text = ""
            resultado.text = ""
        }

        val backspace = findViewById<TextView>(R.id.backspace).setOnClickListener{
            val string = expressao.toString()

            if(string.isNotBlank()){
                expressao.text = string.substring(0, string.length - 1)
            }
            resultado.text = ""
        }

        val igual = findViewById<TextView>(R.id.igual).setOnClickListener{

            try {
                val expressaoFormatada = expressao.text.toString().replace("X","*")
                val calculo = ExpressionBuilder(expressaoFormatada.toString()).build()
                val calculoResultado = calculo.evaluate()
                val resultadoFinal = calculoResultado.toLong()

                if(calculoResultado == resultadoFinal.toDouble()){
                    resultado.text = resultadoFinal.toString()
                }else{
                    resultado.text = calculoResultado.toString()
                }
                historico = resultado.text.toString()

                expressao.append(" =")
            }catch(e: Exception){

            }
        }

    }

    fun AcrescentarUmaExpressao(string: String, limpar_dados : Boolean){

        val resultado : TextView = findViewById(R.id.resultado)
        val expressao : TextView = findViewById(R.id.expressao)

        if (resultado.text.isNotEmpty()){
            expressao.text = ""
        }

        if(limpar_dados){
            resultado.text = ""
            expressao.append(string)
        }else{
            expressao.append(resultado.text)
            expressao.append(string)
            resultado.text = ""
        }
    }
}