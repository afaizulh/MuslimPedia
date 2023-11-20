package com.faizul.muslimpedia.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(
	val totalResults: Int,
	val articles: List<ArticlesItem>,
	val status: String
) : Parcelable

@Parcelize
data class ArticlesItem(
	val publishedAt: String,
	val author: String,
	val urlToImage: String,
	val description: String,
	val source: Source,
	val title: String,
	val url: String,
	val content: String
) : Parcelable

@Parcelize
data class Source(
	val name: String,
) : Parcelable
