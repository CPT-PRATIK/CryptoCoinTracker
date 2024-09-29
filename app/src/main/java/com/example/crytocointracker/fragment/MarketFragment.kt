package com.example.crytocointracker.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.lifecycleScope
import com.example.crytocointracker.R
import com.example.crytocointracker.adapter.MarketAdapter
import com.example.crytocointracker.apis.ApiInterface
import com.example.crytocointracker.apis.Apiutilityy
import com.example.crytocointracker.databinding.FragmentMarketBinding
import com.example.crytocointracker.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale


class MarketFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        return inflater.inflate(R.layout.fragment_market, container, false)
    }




}