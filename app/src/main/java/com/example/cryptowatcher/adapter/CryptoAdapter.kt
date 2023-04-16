package com.example.cryptowatcher.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptowatcher.R
import com.example.cryptowatcher.model.CryptoModel

class CryptoAdapter(private val cryptoList:List<CryptoModel>,private val listener: Listener) : RecyclerView.Adapter<CryptoAdapter.RowHolder>() {

        interface Listener{
            fun onItemClick(cryptoModel: CryptoModel)
        }

        private var colors : Array<String> = arrayOf("#FFFFFF","#FF0000")

    class RowHolder(view:View) : RecyclerView.ViewHolder(view) {
            fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener: Listener){

                itemView.setOnClickListener {
                    listener.onItemClick(cryptoModel)
                }
                itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
                itemView.findViewById<TextView>(R.id.text_name_crypto).text = cryptoModel.currency
                itemView.findViewById<TextView>(R.id.text_price_crypto).text =  cryptoModel.price
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}