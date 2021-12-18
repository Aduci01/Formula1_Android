package com.fmgames.formula1.fragments


import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmgames.formula1.databinding.FragmentResultsBinding
import com.fmgames.formula1.fragments.adapters.ResultsAdapter
import com.fmgames.formula1.model.Results
import com.fmgames.formula1.network.NetworkManager
import com.fmgames.formula1.repository.ResultsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultsFragment : Fragment(), ResultsRepository, AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentResultsBinding
    private var results: Results? = null

    private lateinit var adapter: ResultsAdapter

    lateinit var seasons : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadResults(2021)

        seasons = Array(10) { i -> 2012 + i  }

        binding.spinner.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            view.context,
            R.layout.simple_spinner_item,
            seasons)

        ad.setDropDownViewResource(
            R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = ad
    }

    private fun initRecyclerView() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = ResultsAdapter()
        binding.mainRecyclerView.adapter = adapter
    }


    private fun loadResults(year: Int) {
        NetworkManager.getResults(year).enqueue(object : Callback<Results?> {
            override fun onResponse(
                call: Call<Results?>,
                response: Response<Results?>
            ) {
                if (response.isSuccessful) {
                    displayResultsData(response.body())
                } else {
                    Toast.makeText(activity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<Results?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(activity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayResultsData(receivedData: Results?) {
        results = receivedData

        adapter = ResultsAdapter()
        binding.mainRecyclerView.adapter = adapter

        results?.response?.forEach { adapter.addNews(it) }
    }

    override fun getResults(): Results? {
        return results
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        loadResults(seasons[position])
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}