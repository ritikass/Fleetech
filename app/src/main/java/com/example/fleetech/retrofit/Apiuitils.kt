package com.example.fleetech.retrofit

object Apiuitils {
    private const val SEMI_STAGING_URL = "https://Fleetech.in/NRVDriverAPI/api/"
    val testUrl: Api
        get() = RetrofitClient.getClient(SEMI_STAGING_URL)!!.create(
            Api::class.java
        )


}