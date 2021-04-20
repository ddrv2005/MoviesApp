package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.ErrorLayoutBinding
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.utils.display
import com.example.moviesapp.utils.hide
import com.example.moviesapp.utils.show
import com.example.moviesapp.viewmodels.PopularMoviesActions
import com.example.moviesapp.viewmodels.PopularMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment: Fragment() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModel()
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        MovieAdapter {
            findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(it))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setupRecycleView()
    }

    override fun onResume() {
        super.onResume()
        popularMoviesViewModel.requestPopularMovies()
        popularMoviesViewModel.popularMoviesActions.observe(viewLifecycleOwner, Observer {
            when (it) {
                PopularMoviesActions.OnLoading -> binding.progressBarMovies.show()
                PopularMoviesActions.OnError -> showError()
                is PopularMoviesActions.OnMoviesRetrieved -> {
                    binding.progressBarMovies.hide()
                    adapter.setMovies(it.movies)
                }
            }
        })
    }

    private fun initListeners() {
        binding.toolbarMovie.setOnMenuItemClickListener {
            if (it.itemId == R.id.movie_search) {
                findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToSearchMovieFragment())
            }
            true
        }

        binding.layoutError.buttonRetry.setOnClickListener {
            showViews(true)
            popularMoviesViewModel.requestPopularMovies()
        }
    }

    private fun setupRecycleView() {
        binding.recyclerViewMovies.apply {
            adapter = this@MoviesFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun showError() {
        showViews(false)
    }

    private fun showViews(show: Boolean) {
        binding.progressBarMovies.display(show)
        binding.toolbarMovie.display(show)
        binding.recyclerViewMovies.display(show)
        binding.layoutError.root.display(!show)
    }
}
