package com.example.crytocointracker.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2

import com.example.crytocointracker.adapter.TopLossGainPagerAdapter
import com.example.crytocointracker.adapter.TopMarketAdapter
import com.example.crytocointracker.apis.ApiInterface

import com.example.crytocointracker.apis.Apiutilityy
import com.example.crytocointracker.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        binding= FragmentHomeBinding.inflate(layoutInflater)
        getTopCurrencyList()

        setTabLayout()
        return binding.root
    }

    private fun setTabLayout(){
        val adapter=TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter=adapter
        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position==0){
                    binding.topGainIndicator.visibility = View.VISIBLE
                    binding.topLoseIndicator.visibility = View.GONE

                }else{
                    binding.topGainIndicator.visibility = View.GONE
                    binding.topLoseIndicator.visibility = View.VISIBLE
                }
            }
        })
        TabLayoutMediator(binding.tabLayout,binding.contentViewPager){
            tab,position->
            var title= if(position==0){
                "Top gainers"
            }else{
                "In Loss"
            }
            tab.text=title
        }.attach()




    }

    private fun getTopCurrencyList(){
        lifecycleScope.launch(Dispatchers.IO){
            val res= Apiutilityy.getInstance().create(ApiInterface::class.java).getMarketData()

            withContext(Dispatchers.Main){
                binding.topCurrencyRecyclerView.adapter=TopMarketAdapter(requireContext(),res.body()!!.data.cryptoCurrencyList)
            }

        }

    }


}