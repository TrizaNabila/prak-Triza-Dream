package com.example.triza_dream

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triza_dream.catatan.BansosEntity

class BansosAdapter(private val list: List<BansosEntity>) : RecyclerView.Adapter<BansosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNama: TextView = view.findViewById(R.id.txtNamaBansos)
        val txtJenis: TextView = view.findViewById(R.id.txtJenisBansos)
        val txtKet: TextView = view.findViewById(R.id.txtKetBansos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bansos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.txtNama.text = item.namaPenerima
        holder.txtJenis.text = item.jenisBantuan
        holder.txtKet.text = item.keterangan
    }

    override fun getItemCount(): Int = list.size
}