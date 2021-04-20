package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.databinding.FragmentMovieDetailBinding
import com.example.moviesapp.models.MovieDetail
import com.example.moviesapp.utils.hide
import com.example.moviesapp.utils.loadImage
import com.example.moviesapp.utils.show
import com.example.moviesapp.viewmodels.MovieDetailActions
import com.example.moviesapp.viewmodels.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment: Fragment() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private val arguments: MovieDetailFragmentArgs by navArgs()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        movieDetailViewModel.movieDetailActions.observe(viewLifecycleOwner, Observer {
            when (it) {
                MovieDetailActions.OnLoading -> binding.progressBar.show()
                is MovieDetailActions.OnMovieDetailRetrieved -> loadInfo(it.movieDetail)
            }
        })
        if (arguments.movieId != -1) {
            movieDetailViewModel.requestPopularMovies(arguments.movieId)
        } else {
            showError()
        }
    }

    private fun loadInfo(movieDetail: MovieDetail) {
        binding.progressBar.hide()
        movieDetail.posterPath?.let {
            binding.imageViewMovieDetail.loadImage(it)
        }

        binding.textViewNameDatail.text = movieDetail.title
        binding.textViewMoviesDetail.text = movieDetail.overview
    }

    private fun showError() {

    }
}