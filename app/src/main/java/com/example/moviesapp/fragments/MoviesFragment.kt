package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.FragmentMoviesBinding
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
        setupRecycleView()
    }

    override fun onResume() {
        super.onResume()
        popularMoviesViewModel.requestPopularMovies()
        popularMoviesViewModel.popularMoviesActions.observe(viewLifecycleOwner, Observer {
            when (it) {
                PopularMoviesActions.OnLoading -> binding.progressBarMovies.show()
                is PopularMoviesActions.OnMoviesRetrieved -> {
                    binding.progressBarMovies.hide()
                    adapter.setMovies(it.movies)
                }
            }
        })
    }

    private fun setupRecycleView() {
        binding.recyclerViewMovies.apply {
            adapter = this@MoviesFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}
