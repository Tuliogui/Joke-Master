package com.example.jokemaster.data

import com.example.jokemaster.model.Joke

interface JokeCallback {
    fun onSucess(response: Joke)

    fun onError(response: String)

    fun onComplete()

}