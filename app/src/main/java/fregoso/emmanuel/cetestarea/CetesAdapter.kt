package fregoso.emmanuel.cetestarea

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToLong

class CetesAdapter(val cetes:List<Cete>): RecyclerView.Adapter<CeteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CeteViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cete_item,parent,false)
        return CeteViewHolder(vista)
    }

    override fun getItemCount(): Int = cetes.size

    override fun onBindViewHolder(holder: CeteViewHolder, position: Int) {
        holder.tvMes.text = cetes[position].mes
        holder.tvCantidad.text = String.format("%.2f",cetes[position].cantidad)
    }
}