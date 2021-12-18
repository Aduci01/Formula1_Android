package com.fmgames.formula1.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fmgames.formula1.R
import com.fmgames.formula1.databinding.ItemCircuitBinding
import com.fmgames.formula1.databinding.ItemResultBinding
import com.fmgames.formula1.model.CircuitsModel
import com.fmgames.formula1.model.ResultsModel

class CircuitsAdapter() : RecyclerView.Adapter<CircuitsAdapter.CircuitsViewHolder>() {
    private val circuitsList: MutableList<CircuitsModel> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_circuit, parent, false)
        return CircuitsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CircuitsViewHolder, position: Int) {
        val item = circuitsList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = circuitsList.size

    fun addCircuits(result: CircuitsModel) {
        circuitsList.add(result)
        notifyItemInserted(circuitsList.size - 1)
    }

    inner class CircuitsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemCircuitBinding.bind(itemView)
        var item: CircuitsModel? = null

        fun bind(circuit: CircuitsModel?, position: Int) {
            item = circuit

            binding.circuitItemTitle.text = item?.name.toString()

            Glide.with(binding.root)
                .load(item?.image)
                .transition(DrawableTransitionOptions().crossFade())
                .into(binding.circuitImage)
        }
    }
}