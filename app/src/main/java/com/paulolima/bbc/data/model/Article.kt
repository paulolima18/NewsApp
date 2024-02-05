package com.paulolima.bbc.data.model

import com.google.gson.annotations.SerializedName

data class Article(

    @field:SerializedName("articles")
    val headlines: List<Headline> = emptyList()
)