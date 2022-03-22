package com.example.jokemaster.presentation

import android.graphics.Color
import com.example.jokemaster.data.CategoryRemoteDataSource
import com.example.jokemaster.data.ListCategoryCallback
import com.example.jokemaster.model.Category
import com.example.jokemaster.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
    ):ListCategoryCallback {

    //INPUT
    fun findAllCategories(){
        view.showProgress()
        dataSource.findAllCategories(this)

    }

    override fun onSucess(response: List<String>) {
        val start = 40  // H - matiz
        val end = 190   // H - matiz
        val diff = (end - start) / response.size
        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),   // H, matiz
                100.0f,                             // S, saturação
                100.0f,                             // V, valor
            )
        Category(s, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }


    override fun onError(response: String){
        view.showFailure(response)
    }

    override fun onComplete(){
    view.hideProgress()
    }

    //SIMULAR REQUISIÇÃO HTTP

}