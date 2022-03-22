package com.example.jokemaster.data

import com.example.jokemaster.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDataSource {
    fun findRandom(callback: JokeCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom()
            .enqueue (object : Callback<Joke>{
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful){
                        val  joke = response.body()
                        callback.onSucess(joke ?: throw RuntimeException("Piada não encontrada"))
                    } else{
                        //quando o servidor devolve status de error < 500
                        val  error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }

            })
    }
}





