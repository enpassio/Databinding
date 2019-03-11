package com.enpassio.databindingwithnewsapikotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import com.enpassio.databindingwithnewsapikotlin.data.Article
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "NetworkUtils"

fun thereIsConnection(context: Context): Boolean {
    // Get a reference to the ConnectivityManager to check state of network connectivity
    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    // Get details on the currently active default data network
    val networkInfo = connMgr.activeNetworkInfo
    // If there is a network connection, return true, otherwise false
    return networkInfo != null && networkInfo.isConnected
}

fun fetchArticles(): List<Article>? {
    // Create URL object
    val url = buildUrl()

    // Perform HTTP request to the URL and receive a JSON response back
    var jsonResponse: String? = null
    try {
        jsonResponse = makeHttpRequest(url)
    } catch (e: IOException) {
        Log.e(TAG, "Problem making the HTTP request.", e)
    }

    // Return the list of {@link Article}s
    return extractFeatureFromJson(jsonResponse)
}

/**
 * Create a new URL string based on some sample data.
 */

private fun buildUrl(): String {
    val uri = Uri.parse(BASE_URL).buildUpon()
        .appendPath(ENDPOINT)
        .appendQueryParameter(CATEGORY, SAMPLE_CATEGORY)
        .appendQueryParameter(COUNTRY, SAMPLE_COUNTRY)
        .appendQueryParameter(PAGE_SIZE_PARAM, SAMPLE_PAGE_SIZE)
        .appendQueryParameter(NEWS_API_KEY, NEWS_API_VALUE)
        .build()

    return uri.toString()
}

/**
 * Here we use an extension function provided by kotlin to make an http request.
 * Note that this function is good only for small requests.
 */
private fun makeHttpRequest(url: String): String {
    return URL(url).readText()
}

/**
 * Return a list of [Article] objects that has been built up from
 * parsing the given JSON response.
 */
private fun extractFeatureFromJson(articlesJSON: String?): List<Article>? {
    // If the JSON string is empty or null, then return early.
    if (articlesJSON.isNullOrBlank()) {
        return null
    }

    // Create an empty ArrayList that we can start adding articles to
    val articles = ArrayList<Article>()

    // Try to parse the JSON response string. If there's a problem with the way the JSON
    // is formatted, a JSONException exception object will be thrown.
    // Catch the exception so the app doesn't crash, and print the error message to the logs.
    try {

        // Create a JSONObject from the JSON response string
        val baseJsonResponse = JSONObject(articlesJSON)

        // Extract the JSONArray associated with the key called "articles",
        // which represents a list of articles.
        val articlesArray = baseJsonResponse.getJSONArray(ARTICLES_ARRAY)

        // For each article in the articlesArray, create an {@link Article} object
        for (i in 0 until articlesArray.length()) {

            // Get a single article at position i within the list of articles
            val currentArticle = articlesArray.getJSONObject(i)

            //Retrieve the field that you need from this json object:

            // Extract the value for the key called "author"
            val author = currentArticle.getString(AUTHOR)

            // Extract the value for the key called "title"
            val title = currentArticle.getString(TITLE)

            // Extract the value for the key called "description"
            val description = currentArticle.getString(DESCRIPTION)

            // Extract the value for the article url
            val articleUrl = currentArticle.getString(ARTICLE_URL)

            // Extract the value for the image url
            val imageUrl = currentArticle.getString(IMAGE_URL)

            //Extract the value for the key "publishedAt"
            val publishingTime = formatDateTime(currentArticle.getString(PUBLISHING_TIME))

            //Extract the value for the key "content"
            val articleBody = currentArticle.getString(ARTICLE_BODY)

            //Source name is inside a source json object, so we first need to get this object
            val sourceJSON = currentArticle.getJSONObject(SOURCE)

            //Then we get the source name from this sourceJSON
            val sourceName = sourceJSON.getString(SOURCE_NAME)

            // Create a new {@link Article} object with
            val article =
                Article(
                    sourceName,
                    author,
                    title,
                    description,
                    articleUrl,
                    imageUrl,
                    publishingTime,
                    articleBody
                )

            // Add the new {@link Article} to the list of articles.
            articles.add(article)
        }

    } catch (e: JSONException) {
        // If an error is thrown when executing any of the above statements in the "try" block,
        // catch the exception here, so the app doesn't crash. Print a log message
        // with the message from the exception.
        Log.e(TAG, "Problem parsing the NewsApi JSON results", e)
    }

    // Return the list of articles
    return articles
}

private fun formatDateTime(dateTime: String): String {
    val timeZone = TimeZone.getTimeZone("UTC")
    val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    sourceFormat.timeZone = timeZone
    var parsedTime: Date? = null
    try {
        parsedTime = sourceFormat.parse(dateTime)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    val tz = TimeZone.getDefault()
    val destFormat = SimpleDateFormat("LLL dd, yyyy'T'HH:mm")
    destFormat.timeZone = tz
    return destFormat.format(parsedTime)
}

