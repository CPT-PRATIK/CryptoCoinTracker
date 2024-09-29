package com.example.crytocointracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.crytocointracker.databinding.TopCurrencyLayoutBinding
import com.example.crytocointracker.models.CryptoCurrency

class TopMarketAdapter(val context: Context, val list:List<CryptoCurrency>): RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view:View):RecyclerView.ViewHolder(view){
        var binding= TopCurrencyLayoutBinding.bind(view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
       return TopMarketViewHolder(LayoutInflater.from(context).inflate(com.example.crytocointracker.R.layout.top_currency_layout,parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item= list[position]

        holder.binding.topCurrencyNameTextView.text = item.name

        Glide.with(context).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id +".png"
        ).thumbnail(Glide.with(context).load(com.example.crytocointracker.R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)

        if(item.quotes?.get(0)?.percentChange24h ?: 0.0 > 0) {
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(com.example.crytocointracker.R.color.green))
            holder.binding.topCurrencyChangeTextView.text = "+ ${String.format("%.2f", item.quotes?.get(0)?.percentChange24h)} %"
        } else {
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(com.example.crytocointracker.R.color.red))
            holder.binding.topCurrencyChangeTextView.text = "${String.format("%.2f", item.quotes?.get(0)?.percentChange24h)} %"
        }

    }

}