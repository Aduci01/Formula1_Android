package com.fmgames.formula1.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmgames.formula1.R
import com.fmgames.formula1.databinding.ItemNewsBinding
import com.fmgames.formula1.model.NewsModel

class NewsAdapter(private val listener: OnNewsClickedListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsList: MutableList<NewsModel> = ArrayList()

    interface OnNewsClickedListener {
        fun onNewsSelected(news: NewsModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = newsList.size

    fun addNews(news: NewsModel) {
        newsList.add(news)
        notifyItemInserted(newsList.size - 1)
    }

    inner class NewsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemNewsBinding.bind(itemView)
        var item: NewsModel? = null

        init {
            binding.root.setOnClickListener { listener.onNewsSelected(item) }
        }

        fun bind(newNews: NewsModel?, position: Int) {
            item = newNews
            binding.newsItemTitle.text = item?.title ?: "Could not load News"
            binding.newsItemDesc.text = item?.description ?: ""

        }
    }
}