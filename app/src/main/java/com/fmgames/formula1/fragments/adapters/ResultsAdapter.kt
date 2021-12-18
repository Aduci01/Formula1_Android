package com.fmgames.formula1.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fmgames.formula1.R
import com.fmgames.formula1.databinding.ItemResultBinding
import com.fmgames.formula1.model.ResultsModel

class ResultsAdapter() : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {
    private val resultsList: MutableList<ResultsModel> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ResultsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val item = resultsList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = resultsList.size

    fun addNews(result: ResultsModel) {
        resultsList.add(result)
        notifyItemInserted(resultsList.size - 1)
    }

    inner class ResultsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemResultBinding.bind(itemView)
        var item: ResultsModel? = null

        init {

        }

        fun bind(result: ResultsModel?, position: Int) {
            item = result

            binding.driverName.text = item?.position.toString() + ". " +  item?.driver?.name
            binding.teamName.text = item?.team?.name
            binding.points.text = "Points: " + item?.points.toString()

            Glide.with(binding.root)
                .load(item?.driver?.image)
                .transition(DrawableTransitionOptions().crossFade())
                .into(binding.image)

            Glide.with(binding.root)
                .load(item?.team?.logo)
                .transition(DrawableTransitionOptions().crossFade())
                .into(binding.teamLogo)
        }
    }
}