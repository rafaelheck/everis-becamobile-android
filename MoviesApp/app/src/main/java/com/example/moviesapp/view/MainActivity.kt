package com.example.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.MovieListViewModel.MovieListViewModel
import com.example.moviesapp.R
import com.example.moviesapp.api.MovieRestApiTask
import com.example.moviesapp.model.Movie
import com.example.moviesapp.repository.MoviesAdapter
import com.example.moviesapp.repository.MoviesRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListViewModel = ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init()
        initObserver()
    }

    private fun initObserver(){
        movieListViewModel.moviesList.observe(this, Observer {
            populateList(it)
        })
    }

    private fun populateList(list: List<Movie>){
        trendingMovies.apply{
            hasFixedSize()
            adapter = MoviesAdapter(list)
        }
    }
}