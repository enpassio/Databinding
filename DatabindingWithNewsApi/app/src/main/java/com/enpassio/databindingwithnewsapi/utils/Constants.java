package com.enpassio.databindingwithnewsapi.utils;

import com.enpassio.databindingwithnewsapi.BuildConfig;

public final class Constants {

    private Constants() {
        //Make it impossible to instantiate this class
        throw new AssertionError();
    }

    // Constants and keys related to News Api
    public static final String ARTICLES_ARRAY = "articles";
    public static final String NEWS_API_KEY = "apiKey";
    public static final String NEWS_API_VALUE = BuildConfig.NEWS_API_DOT_ORG_KEY;
    public static final String ENDPOINT = "top-headlines";
    public static final String PAGE_SIZE_PARAM = "pageSize";
    public static final String COUNTRY = "country";
    public static final String CATEGORY = "category";
    public static final String SOURCE = "source";
    public static final String SOURCE_NAME = "name";
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_URL = "urlToImage";
    public static final String ARTICLE_URL = "url";
    public static final String PUBLISHING_TIME = "publishedAt";
    public static final String ARTICLE_BODY = "content";

    //Sample values used for building a url, which can be changed
    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String SAMPLE_PAGE_SIZE = "10";
    public static final String SAMPLE_CATEGORY = "general";
    public static final String SAMPLE_COUNTRY = "gb";

}
