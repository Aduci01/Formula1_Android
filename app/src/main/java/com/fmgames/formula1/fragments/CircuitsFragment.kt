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
import com.fmgames.formula1.databinding.FragmentCircuitsBinding
import com.fmgames.formula1.databinding.FragmentResultsBinding
import com.fmgames.formula1.fragments.adapters.CircuitsAdapter
import com.fmgames.formula1.fragments.adapters.ResultsAdapter
import com.fmgames.formula1.model.Circuits
import com.fmgames.formula1.model.Results
import com.fmgames.formula1.network.NetworkManager
import com.fmgames.formula1.repository.CircuitsRepository
import com.fmgames.formula1.repository.ResultsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CircuitsFragment : Fragment(), CircuitsRepository {
    private lateinit var binding: FragmentCircuitsBinding
    private var circuits: Circuits? = null

    private lateinit var adapter: CircuitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCircuitsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadCircuits()
    }

    private fun initRecyclerView() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = CircuitsAdapter()
        binding.mainRecyclerView.adapter = adapter
    }


    private fun loadCircuits() {
        NetworkManager.getCircuits().enqueue(object : Callback<Circuits?> {
            override fun onResponse(
                call: Call<Circuits?>,
                response: Response<Circuits?>
            ) {
                if (response.isSuccessful) {
                    displayCircuitsData(response.body())
                } else {
                    Toast.makeText(activity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<Circuits?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(activity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayCircuitsData(receivedData: Circuits?) {
        circuits = receivedData

        adapter = CircuitsAdapter()
        binding.mainRecyclerView.adapter = adapter

        circuits?.response?.forEach { adapter.addCircuits(it) }
    }

    override fun getCircuits(): Circuits? {
        return circuits
    }
}