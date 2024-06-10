package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var waga: Double = 0.0
    private var wzrost: Double = 0.0
    private var wynik: Double = 0.0
    private var znaczenie: String = ""
    private val historiaWynikow = arrayListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calc.setOnClickListener {
            bmi()
        }
        binding.hist.setOnClickListener {
            historia()
        }
    }

    private fun bmi() {
        waga = binding.edWaga.text.toString().toDouble()
        wzrost = binding.edWzrost.text.toString().toDouble()
        wynik = waga/((wzrost / 100).pow(2.0))
        historiaWynikow.add(wynik)
        Toast.makeText(this, "Twoje BMI: %.2f".format(wynik), Toast.LENGTH_SHORT).show()
        if(binding.radioF.isChecked) {
            if(wynik < 19) {  znaczenie = "Niedowaga" }
            if(wynik >= 19 && wynik < 24) { znaczenie = "Waga prawidłowa" }
            if(wynik >= 24 && wynik < 29) { znaczenie = "Nadwaga" }
            if(wynik >= 29 && wynik < 39) { znaczenie = "Otyłość" }
            if(wynik >= 39) { znaczenie = "Otyłość zaawansowana" }
            Toast.makeText(this, znaczenie, Toast.LENGTH_SHORT).show()
        } else if(binding.radioM.isChecked) {
            if(wynik < 20) {  znaczenie = "Niedowaga" }
            if(wynik >= 20 && wynik < 25) { znaczenie = "Waga prawidłowa" }
            if(wynik >= 25 && wynik < 30) { znaczenie = "Nadwaga" }
            if(wynik >= 30 && wynik < 40) { znaczenie = "Otyłość" }
            if(wynik >= 40) { znaczenie = "Otyłość zaawansowana" }
            Toast.makeText(this, znaczenie, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nie wybrano płci!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun historia() {
        if(historiaWynikow.isEmpty()) {
            Toast.makeText(this, "Brak historii wyników", Toast.LENGTH_SHORT).show()
        } else {
            val historiaformat = historiaWynikow.joinToString {"%.2f".format(it)}
            Toast.makeText(this, "Historia wyników: $historiaformat", Toast.LENGTH_LONG).show()
        }
    }
}
