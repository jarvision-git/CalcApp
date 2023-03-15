package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var lastNumeric:Boolean=false
    var operator:Boolean=false
    var operator2:Boolean=false
    var lastdot:Boolean=false
    var numeric2=false

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val viev=binding.root
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View)
    {
        val txt=findViewById<TextView>(R.id.out)
        txt.append((view as Button).text)
        if (!lastNumeric && !operator) {
            lastNumeric = true

        }
        else if (lastNumeric&&operator&&!numeric2){
            numeric2=true
            operator2=false
            Log.i("values","$lastNumeric $operator  $numeric2  ${!operator2}")
        }
    }

    fun onClear(view: View)
    {
        val txt=findViewById<TextView>(R.id.out)
        txt.text=""
        lastdot=false
        lastNumeric=false
        numeric2=false
        operator=false
        operator2=false
    }

    fun onPoint(view: View)
    {
        val txt=findViewById<TextView>(R.id.out)
        if (lastNumeric&&!lastdot){
            txt.append(".")
            lastNumeric=false
            lastdot=true
        }
    }
    
    fun onOperator(view: View)
    {
        val txt=findViewById<TextView>(R.id.out)
        if (lastNumeric && !operator)
        {
            txt.append((view as Button).text)
            lastdot=false
            operator=true
        }
        else if (lastNumeric && operator && numeric2 && !operator2)
        {
            onEqual(view)
            txt.append((view as Button).text)
            lastdot=false
            operator2=true
            numeric2=false
        }

    }

    fun onEqual(view: View){
        if (lastNumeric)
        {
            val txt=findViewById<TextView>(R.id.out)
            var value =txt.text.toString()
            var prefix=""
            try {
                if(value.startsWith("-"))
                {
                    prefix="-"
                    value=value.substring(1)
                }
                if (value.contains("-"))
                {
                    val splitval=value.split("-")
                    var one =splitval[0]
                    var two= splitval[1]

                    if (!prefix.isEmpty())
                    {
                        one =prefix + one
                    }

                    txt.text=(one.toDouble()-two.toDouble()).toString()
                }

                else if (value.contains("*"))
                {
                    val splitval=value.split("*")
                    var one=splitval[0]
                    var two =splitval[1]
                    if (!prefix.isEmpty())
                    {
                        one =prefix + one
                    }

                    txt.text=(one.toDouble()*two.toDouble()).toString()

                }

                else if (value.contains("+"))
                {
                    val splitval=value.split("+")
                    var one=splitval[0]
                    var two =splitval[1]
                    if (!prefix.isEmpty())
                    {
                        one =prefix + one
                    }

                    txt.text=(one.toDouble()+two.toDouble()).toString()
                }

                else if (value.contains("/"))
                {
                    val splitval=value.split("/")
                    var one=splitval[0]
                    var two =splitval[1]
                    if (!prefix.isEmpty())
                    {
                        one =prefix + one
                    }
                    txt.text=(one.toDouble()/two.toDouble()).toString()
                }
            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}