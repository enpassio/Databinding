package com.enpassio.databindingwithnewsapikotlin.utils

import com.enpassio.databindingwithnewsapikotlin.BuildConfig


// Constants and keys related to News Api
const val ARTICLES_ARRAY = "articles"
const val NEWS_API_KEY = "apiKey"
const val NEWS_API_VALUE = BuildConfig.NEWS_API_DOT_ORG_KEY
const val ENDPOINT = "top-headlines"
const val PAGE_SIZE_PARAM = "pageSize"
const val COUNTRY = "country"
const val CATEGORY = "category"
const val SOURCE = "source"
const val SOURCE_NAME = "name"
const val AUTHOR = "author"
const val TITLE = "title"
const val DESCRIPTION = "description"
const val IMAGE_URL = "urlToImage"
const val ARTICLE_URL = "url"
const val PUBLISHING_TIME = "publishedAt"
const val ARTICLE_BODY = "content"

//Sample values used for building a url, which can be changed
const val BASE_URL = "https://newsapi.org/v2/"
const val SAMPLE_PAGE_SIZE = "10"
const val SAMPLE_CATEGORY = "general"
const val SAMPLE_COUNTRY = "gb"