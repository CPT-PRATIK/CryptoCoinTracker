package com.example.crytocointracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crytocointracker.databinding.CurrencyItemLayoutBinding
import com.example.crytocointracker.fragment.HomeFragmentDirections
import com.example.crytocointracker.fragment.MarketFragment
import com.example.crytocointracker.models.CryptoCurrency

class MarketAdapter(var context: Context, var list: List<CryptoCurrency>, var type: String):RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    inner class MarketViewHolder(view:View):RecyclerView.ViewHolder(view){
        var bindings=CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(com.example.crytocointracker.R.layout.currency_item_layout,parent,false))
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item=list[position]
        holder.bindings.currencyNameTextView.text=item.name
        holder.bindings.currencySymbolTextView.text=item.symbol
        Glide.with(context).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id +".png"
        ).thumbnail(Glide.with(context).load(com.example.crytocointracker.R.drawable.spinner))
            .into(holder.bindings.currencyImageView)

        Glide.with(context).load(
            "https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/"+ item.id +".png"
        ).thumbnail(Glide.with(context).load(com.example.crytocointracker.R.drawable.spinner))
            .into(holder.bindings.currencyChartImageView)

        holder.bindings.currencyPriceTextView.text= item.quotes[0].price.toString()
        "+${String.format("$%.02f",item.quotes[0].price)}"

        if(item.quotes!![0].percentChange24h>0){
            holder.bindings.currencyChangeTextView.setTextColor(context.resources.getColor(com.example.crytocointracker.R.color.green))
            holder.bindings.currencyChangeTextView.text="+${String.format("%.02f",item.quotes[0].percentChange24h)}%"
        }else{
            holder.bindings.currencyChangeTextView.setTextColor(context.resources.getColor(com.example.crytocointracker.R.color.red))
            holder.bindings.currencyChangeTextView.text="${String.format("%.02f",item.quotes[0].percentChange24h)}%"
        }
        holder.itemView.setOnClickListener{
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
        }









    }
}