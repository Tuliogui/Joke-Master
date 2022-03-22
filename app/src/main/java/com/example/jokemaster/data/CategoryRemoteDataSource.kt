package com.example.jokemaster.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {
    fun findAllCategories(callback: ListCategoryCallback){
        HTTPClient.retrofit()                               //aqui tem a instancia retrofit
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories(HTTPClient.API_KEY)
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                    if (response.isSuccessful){
                        val  categories = response.body()
                        callback.onSucess(categories ?: emptyList())
                    } else{
                        //quando o servidor devolve status de error < 500
                        val  error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onComplete()

                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()

                }

            })





    /*
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )
            //aqui a lista ja esta pronta (response)

            Log.i("Teste", response.toString())

            //DEVOLVER OU FALHA OU SUCESSO
            callback.onSucess(response)

            //onError("FALHA NA CONEXÃO. TENTE NOVAMENTE MAIS TARDE!")

            callback.onComplete()
        }, 4000)

         */
    }

}