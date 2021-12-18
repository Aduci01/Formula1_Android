package com.fmgames.formula1.repository

import com.fmgames.formula1.model.NewsFeed

interface NewsRepository {
    fun getNews() : NewsFeed?
}