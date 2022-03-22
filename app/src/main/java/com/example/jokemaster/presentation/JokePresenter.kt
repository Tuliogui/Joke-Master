package com.example.jokemaster.presentation

import com.example.jokemaster.data.JokeCallback
import com.example.jokemaster.data.JokeRemoteDataSource
import com.example.jokemaster.model.Joke
import com.example.jokemaster.view.JokeFragment

class JokePresenter (
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
        ): JokeCallback {

    fun findBy(categoryName: String){
        view.showProgress()
        dataSource.findBy(categoryName, this)
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