package com.fmgames.formula1.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "item")
data class NewsModel @JvmOverloads constructor(
    @PropertyElement
    var title: String,

    @PropertyElement
    var description: String,

    @PropertyElement(name = "link")
    var url: String,
)

@Xml(name = "rss")
data class NewsFeed  @JvmOverloads constructor (
    @PropertyElement(name = "title")
    @Path("channel")
    var channelTitle: String? = null,

    @PropertyElement(name = "item")
    @Path("channel")
    var newsList: List<NewsModel>? =
        null
)
