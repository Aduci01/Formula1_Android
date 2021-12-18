package com.fmgames.formula1.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmgames.formula1.R
import com.fmgames.formula1.databinding.FragmentNewsBinding
import com.fmgames.formula1.fragments.adapters.NewsAdapter
import com.fmgames.formula1.model.NewsFeed
import com.fmgames.formula1.model.NewsModel
import com.fmgames.formula1.network.NetworkManager
import com.fmgames.formula1.repository.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment(), NewsAdapter.OnNewsClickedListener, NewsRepository {
    private lateinit var binding: FragmentNewsBinding
    private var newsData: NewsFeed? = null

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadNewsFeed()
    }

    private fun initRecyclerView() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = NewsAdapter(this)
        binding.mainRecyclerView.adapter = adapter
    }

    override fun onNewsSelected(news: NewsModel?) {
        if (news != null) {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(news.url)

            startActivity(openURL)
        }
    }

    override fun getNews(): NewsFeed? {
        return newsData
    }

    private fun loadNewsFeed() {
        /*NetworkManager.getNews().enqueue(object : Callback<NewsFeed?> {
            override fun onResponse(
                call: Call<NewsFeed?>,
                response: Response<NewsFeed?>
            ) {
                if (response.isSuccessful) {
                    displayNewsData(response.body())
                } else {
                    Toast.makeText(activity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<NewsFeed?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(activity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })*/

        displayNewsData(NetworkManager.getNews())
    }

    private fun displayNewsData(receivedData: NewsFeed?) {
        newsData = receivedData

        newsData?.toString()?.let { Log.d("", it) }

        adapter = NewsAdapter(this)
        binding.mainRecyclerView.adapter = adapter

        newsData?.newsList?.forEach { adapter.addNews(it) }
    }
}