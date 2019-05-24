package com.enpassio.databindingwithnewsapikotlin.model

//This enum class is for facilitating the switch between UI state
enum class UIState{

    //While user wait for data to be fetched, a loading indicator is shown
    LOADING,

    //When data is successfully retrieved and list can become visible
    SUCCESS,

    //When there is a problem with network, an error message should be shown
    NETWORK_ERROR
}