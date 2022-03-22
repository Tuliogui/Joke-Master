package com.example.jokemaster.presentation

import com.example.jokemaster.data.JokeCallback
import com.example.jokemaster.data.JokeDayRemoteDataSource
import com.example.jokemaster.model.Joke
import com.example.jokemaster.view.JokeDayFragment

class JokeDayPresenter (
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
        ): JokeCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
    }

    override fun onSucess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}