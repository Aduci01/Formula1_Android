package com.fmgames.formula1.network

import com.fmgames.formula1.model.*
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManager {
    private val newsRetrofit: Retrofit
    private val newsApi: NewsApi
    private const val NEWS_URL = "https://www.formula1-news.com/content/en/latest/all.xml/"

    private val resultsRetrofit: Retrofit
    private val resultsApi: ResultsApi
    private val circuitsRetrofit: Retrofit
    private val circuitsApi: CircuitsApi
    private const val RAPID_API_URL = "https://api-formula-1.p.rapidapi.com"
    private const val API_KEY = "29d01f06cdmsh8e46038844bd010p1a930fjsn44affa6b9f99"

    init {
        //News Api
        newsRetrofit = Retrofit.Builder()
            .baseUrl(NEWS_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder()
                        .exceptionOnUnreadXml(false)
                        .addTypeConverter(String::class.java, HtmlEscapeStringConverter())
                        .build()
                )
            )
            .build()

        newsApi = newsRetrofit.create(NewsApi::class.java)



        //Results Api
        resultsRetrofit = Retrofit.Builder()
            .baseUrl(RAPID_API_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        resultsApi = resultsRetrofit.create(ResultsApi::class.java)

        //Circuits Api
        circuitsRetrofit = Retrofit.Builder()
            .baseUrl(RAPID_API_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        circuitsApi = circuitsRetrofit.create(CircuitsApi::class.java)
    }

    fun getResults(year: Int): Call<Results?> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["x-rapidapi-host"] = "api-formula-1.p.rapidapi.com"
        headerMap["x-rapidapi-key"] = API_KEY

        return resultsApi.getResults(headerMap, year)
    }

    fun getCircuits(): Call<Circuits?> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["x-rapidapi-host"] = "api-formula-1.p.rapidapi.com"
        headerMap["x-rapidapi-key"] = API_KEY

        return circuitsApi.getResults(headerMap)
    }

    fun getNews(): NewsFeed {
        //return newsApi.getNews() //Az API amit használni szerettem volna a hírek lekérésére idő közben megszűnt
        val news1 = NewsModel(title = "News service API is down", description = "The service is temporarily not working", url = "https://www.formula1.com/")
        val news2 = NewsModel(title = "F1 SPRINT: What the teams and drivers had to say", description = "The drivers and teams report back on all the action from a frantic Sprint from the Autodromo", url = "https://www.formula1.com/en/latest/article.f1-sprint-what-the-teams-and-drivers-had-to-say-as-the-sprint-sao-paulo-2021.6nk5uVXqYjp4hhJ4uBvbqZ.html")
        val news3 = NewsModel(title = "Hard work on starts paying off says Sainz after P3", description = "Carlos Sainz felt he made something of a breakthrough with his race starts at Interlagos as a lightening getaway on softs", url = "https://www.formula1.com/en/latest/article.hard-work-on-starts-paying-off-says-sainz-after-p3-in-sao-paulo-gp-sprint.vmSick4gWC1RuH56WXxtH.html")
        val news4 = NewsModel(title = "News service API is down", description = "The service is temporarily not working", url = "https://www.formula1.com/")

        val dummyList = listOf<NewsModel>(news1, news2, news3, news4)

        return NewsFeed(channelTitle = "rssFeed", newsList = dummyList)
    }


}