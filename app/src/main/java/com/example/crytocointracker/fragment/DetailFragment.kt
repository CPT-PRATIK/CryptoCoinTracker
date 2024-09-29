package com.example.crytocointracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.crytocointracker.R
import com.example.crytocointracker.databinding.FragmentDetailBinding
import com.example.crytocointracker.models.CryptoCurrency


class DetailFragment : Fragment() {

    lateinit var binding:FragmentDetailBinding
    private val item:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater)
        val data:CryptoCurrency=item.data!!

        setUpDetails(data)
        loadChart(data)
        setButtonOnClick(data)

        return binding.root
    }

    private fun setButtonOnClick(item: CryptoCurrency) {
        val oneMonth=binding.button
        val oneWeek=binding.button1
        val oneDay=binding.button2
        val fourHour=binding.button3
        val oneHour=binding.button4
        val fifteenMinute=binding.button5


        val clickListner=View.OnClickListener {
            when(it.id){
                fifteenMinute.id->loadCharData(it,"15",item,oneDay,oneMonth,oneWeek,fourHour,oneHour)
                oneHour.id->loadCharData(it,"1H",item,oneDay,oneMonth,oneWeek,fourHour,fifteenMinute)
                fourHour.id->loadCharData(it,"4H",item,oneDay,oneMonth,oneWeek,fifteenMinute,oneHour)
                oneDay.id->loadCharData(it,"D",item,fifteenMinute,oneMonth,oneWeek,fourHour,oneHour)
                oneWeek.id->loadCharData(it,"W",item,oneDay,oneMonth,fifteenMinute,fourHour,oneHour)
                oneMonth.id->loadCharData(it,"M",item,oneDay,fifteenMinute,oneWeek,fourHour,oneHour)

            }
        }
        fifteenMinute.setOnClickListener(clickListner)
        oneHour.setOnClickListener(clickListner)
       fourHour.setOnClickListener(clickListner)
        oneDay.setOnClickListener(clickListner)
        oneWeek.setOnClickListener(clickListner)
        oneMonth.setOnClickListener(clickListner)
    }

    private fun loadCharData(
        it: View?,
        s: String,
        item: CryptoCurrency,
        oneDay: AppCompatButton,
        oneMonth: AppCompatButton,
        oneWeek: AppCompatButton,
        fourHour: AppCompatButton,
        oneHour: AppCompatButton,
    ) {
        disableButton(oneDay,oneMonth,oneWeek,fourHour,oneHour)
        it!!.setBackgroundResource(com.example.crytocointracker.R.drawable.active_button)
        binding.detaillChartWebView.settings.javaScriptEnabled=true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        binding.detaillChartWebView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol =" +item.symbol.toString()
                    + "USD&interval="+s+" &hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                    "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=" +
                    "[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )

    }

    private fun disableButton(oneDay: AppCompatButton, oneMonth: AppCompatButton, oneWeek: AppCompatButton, fourHour: AppCompatButton, oneHour: AppCompatButton) {
            oneDay.background=null
        oneWeek.background=null
        oneMonth.background=null
        oneWeek.background=null
        fourHour.background=null
        oneHour.background=null
    }


    private fun loadChart(item: CryptoCurrency) {
        binding.detaillChartWebView.settings.javaScriptEnabled=true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        binding.detaillChartWebView.loadUrl(
            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol =" +item.symbol.toString()
                    + "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=" +
                    "F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=" +
                    "[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
        )


    }

    private fun setUpDetails(data:CryptoCurrency){
        binding.detailSymbolTextView.text=data.symbol

        Glide.with(requireContext()).load(
            "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ data.id +".png"
        ).thumbnail(Glide.with(requireContext()).load(com.example.crytocointracker.R.drawable.spinner))
            .into(binding.detailImageView)

         binding.detailPriceTextView.text=
        "+${String.format("$%.4f",data.quotes[0].price)}"

        if(data.quotes!![0].percentChange24h>0){
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(com.example.crytocointracker.R.color.green))
            binding.detailChangeImageView.setImageResource(com.example.crytocointracker.R.drawable.ic_caret_up)
            binding.detailChangeTextView.text="+${String.format("%.02f",data.quotes[0].percentChange24h)}%"
        }else{
            binding.detailChangeImageView.setImageResource(com.example.crytocointracker.R.drawable.ic_caret_down)
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(com.example.crytocointracker.R.color.red))
            binding.detailChangeTextView.text="${String.format("%.02f",data.quotes[0].percentChange24h)}%"
        }
    }
}