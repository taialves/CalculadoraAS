package com.calculadora.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.math.exp

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

        supportActionBar!!.hide()



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