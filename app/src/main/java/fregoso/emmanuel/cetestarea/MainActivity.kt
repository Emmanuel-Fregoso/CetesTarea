package fregoso.emmanuel.cetestarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var etCantidad:EditText
    lateinit var sbMeses:SeekBar
    lateinit var rvCetes:RecyclerView
    lateinit var adapter: CetesAdapter
    private val cetesViewModel:CetesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCantidad = findViewById(R.id.etCantidad)
        sbMeses = findViewById(R.id.sbMeses)
        rvCetes = findViewById(R.id.rvCetes)
        adapter = CetesAdapter(cetesViewModel.cetes)
        var cantidadActual:Double
        try {
            cantidadActual = etCantidad.text.toString().toDouble()
        } catch (ex:NumberFormatException){
            cantidadActual = 0.0
            etCantidad.setText((0.0).toString())
        }
        var rendimiento:Double
        rvCetes.adapter = adapter
        rvCetes.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

        sbMeses.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.apply {
                    cetesViewModel.cetes.clear()
                    cantidadActual = etCantidad.text.toString().toDouble()
                    for(mes in 0.. progress){
                        rendimiento = ((11.0/12)/100) * cantidadActual
                        cantidadActual = (cantidadActual + rendimiento).toDouble()
                        var mesName:String
                        when (mes+1){
                            1-> mesName = "Enero"
                            2-> mesName = "Febrero"
                            3-> mesName = "Marzo"
                            4-> mesName = "Abril"
                            5-> mesName = "Mayo"
                            6-> mesName = "Junio"
                            7-> mesName = "Julio"
                            8-> mesName = "Agosto"
                            9-> mesName = "Septiembre"
                            10-> mesName = "Octubre"
                            11-> mesName = "Noviembre"
                            12-> mesName = "Diciembre"
                            else -> {
                                mesName = "Desconocido"
                            }
                        }
                        cetesViewModel.cetes.add(Cete(mesName,cantidadActual))
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        })


    }
}