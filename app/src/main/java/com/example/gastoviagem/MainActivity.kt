/*
 * Copyright (c) 2023. Created by Alexsander at 11/2. All rights reserved.
 * GitHub: https://github.com/alexsanderfer/
 * Portfolio: https://alexsanderfer.netlify.app/
 */

package com.example.gastoviagem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.gastoviagem.databinding.ActivityMainBinding

class MainActivity : ComponentActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    /*
     Função responsável por fazer a criação da Activity.
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adiciona evento ao elemento da interface.
        binding.buttonCalculate.setOnClickListener(this)
    }

    /*
    * Função responsável por tratar qualquer evento de click nos elementos.
    * */
    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    /*
    * Função responsável por validar se os elementos receberam e retornam valores válidos.
    * */
    fun isValid(): Boolean {
        return with(binding) {
            editDistance.text.toString().isNotEmpty() &&
                    editPrice.text.toString().isNotEmpty() &&
                    editAutonomy.text.toString().isNotEmpty() &&
                    editAutonomy.text.toString().toFloatOrNull() != null &&
                    editAutonomy.text.toString().toFloat() != 0f
        }
    }

    /*
    * Função responsável por calcular o Gasto das viagens, se baseando em: distância, preço por litro e autonomia do veículo.
    * */
    private fun calculate() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy
            binding.textTotalValue.text = getString(R.string.r, "%.2f".format(totalValue))
        } else {
            Toast.makeText(this, getString(R.string.validation_fill_all_fields), Toast.LENGTH_SHORT).show()
        }
    }
}