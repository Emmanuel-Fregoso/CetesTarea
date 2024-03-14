package fregoso.emmanuel.cetestarea

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CeteViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    val tvMes =itemView.findViewById<TextView>(R.id.tvMes)
    val tvCantidad =itemView.findViewById<TextView>(R.id.tvCantidad)
}
