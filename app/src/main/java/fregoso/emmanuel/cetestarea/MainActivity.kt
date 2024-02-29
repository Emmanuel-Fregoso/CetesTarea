package fregoso.emmanuel.cetestarea

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fregoso.emmanuel.cetestarea.databinding.ActivityMainBinding

import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var sbMeses:SeekBar
    lateinit var etCantidad:EditText
    private lateinit var binding: ActivityMainBinding
    private val calculadoraViewModel:CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbMeses = findViewById(R.id.sbMeses)
        etCantidad = findViewById(R.id.etCantidad)

        sbMeses.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Toast.makeText(baseContext,"$progress",Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {

            //cambio aquí
            realizarCalculo()
        }

        binding.sbPlazo.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progreso: Int, p2: Boolean) {
                binding.tvMeses.text = "A un plazo de $progreso meses"
                realizarCalculo()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

    }


    override fun onStart() {
        super.onStart()
        Log.d("Calculadora","Estoy en onStart")
    }

    fun realizarCalculo(){

        calculadoraViewModel.cantidad = obtenerCantidad()
        calculadoraViewModel.plazo = binding.sbPlazo.progress


        if(calculadoraViewModel.cantidad==0.0 || calculadoraViewModel.plazo == 0){
            mostrarMensaje("Ingresa valores válidos")
        }else{
            val rendimiento = calculadoraViewModel.rendimiento
            binding.tvResultado.text = "\$ ${String.format("%.2f",rendimiento)}"
        }


    }
    override fun onResume() {
        super.onResume()
        Log.d("Calculadora","Estoy en onResume")


    }

    override fun onPause() {
        super.onPause()
        Log.d("Calculadora","Estoy en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Calculadora","Estoy en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Calculadora","Estoy en onDestroy")
    }

    private fun obtenerCantidad():Double{
        var cantidad = calculadoraViewModel.cantidad
        if(binding.etCantidad.text.isNullOrBlank()){
            mostrarMensaje("Ingresa un número")
        }else{
            cantidad = binding.etCantidad.text.toString().toDouble()//validar si no está vacío
        }
        return cantidad
    }

    private fun mostrarMensaje(mensaje:String){
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT)
            .show()
    }


}