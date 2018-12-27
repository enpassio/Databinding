package com.enpassio.databindingwithnewsapi.utils;

import com.enpassio.databindingwithnewsapi.BuildConfig;

final class Constants {

    private Constants() {
        //Make it impossible to instantiate this class
        throw new AssertionError();
    }

    // Constants and keys related to News Api
    static final String ARTICLES_ARRAY = "articles";
    static final String NEWS_API_KEY = "apiKey";
    static final String NEWS_API_VALUE = BuildConfig.NEWS_API_DOT_ORG_KEY;
    static final String ENDPOINT = "top-headlines";
    static final String PAGE_SIZE_PARAM = "pageSize";
    static final String COUNTRY = "country";
    static final String CATEGORY = "category";
    static final String SOURCE = "source";
    static final String SOURCE_NAME = "name";
    static final String AUTHOR = "author";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String IMAGE_URL = "urlToImage";
    static final String PUBLISHING_TIME = "publishedAt";
    static final String ARTICLE_BODY = "content";

    //Sample values used for building a url, which can be changed
    static final String BASE_URL = "https://newsapi.org/v2/";
    static final String SAMPLE_PAGE_SIZE = "10";
    static final String SAMPLE_CATEGORY = "general";
    static final String SAMPLE_COUNTRY = "gb";

}
