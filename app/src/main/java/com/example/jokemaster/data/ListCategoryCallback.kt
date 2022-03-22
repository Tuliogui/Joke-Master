package com.example.jokemaster.data

interface ListCategoryCallback {
    fun onSucess(response: List<String>)

    fun onError(response: String)

    fun onComplete()

}