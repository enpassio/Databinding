package com.enpassion.twowaydatabindingkotlin.data

//This enum class is for facilitating the switch between UI state
enum class UIState {
    //While user wait for data to be fetched, a loading indicator is shown
    LOADING,

    //When there is data to show, list can become visible
    HAS_DATA,

    //When there is no data in the inventory, empty image will be shown
    EMPTY
}

enum class Gender {
    //For toys that are suitable for both sex
    UNISEX,

    //For toys that are mostly loved by girls
    GIRL,

    //For toys that are mostly loved by boys
    BOY
}

enum class ProcurementType {
    //For toys that are bought for money
    BOUGHT,

    //For toys that are received from someone else
    RECEIVED
}