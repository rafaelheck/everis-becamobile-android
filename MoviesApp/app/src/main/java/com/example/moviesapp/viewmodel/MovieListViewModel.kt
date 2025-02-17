package com.example.moviesapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.api.MovieRestApiTask
import com.example.moviesapp.model.Movie
import com.example.moviesapp.repository.MoviesRepository
import java.lang.Exception

class MovieListViewModel: ViewModel() {

    private val movieRestApiTask = MovieRestApiTask()
    private val moviesRepository = MoviesRepository(movieRestApiTask)

    private var _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun init(context: Context){
        getTrendingMovies(context)
    }

    private fun getTrendingMovies(context: Context){
        moviesRepository.getTrendingMovies(context, ::onRequestSuccess, ::onRequestError)
    }

    private fun onRequestSuccess(list: List<Movie>){
        _moviesList.postValue(list)
    }

    private fun onRequestError(context: Context, code: Int?){
        Toast.makeText(context, "Error $code", Toast.LENGTH_SHORT)
    }
}