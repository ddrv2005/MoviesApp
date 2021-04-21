package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.utils.display
import com.example.moviesapp.utils.hide
import com.example.moviesapp.utils.show
import com.example.moviesapp.viewmodels.SearchMovieActions
import com.example.moviesapp.viewmodels.SearchMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment: Fragment() {

    private val searchMoviesViewModel: SearchMoviesViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        MovieAdapter {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchMovieFragmentToMovieDetailFragment(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setupRecycleView()
    }

    override fun onResume() {
        super.onResume()
        searchMoviesViewModel.searchMovieActions.observe(viewLifecycleOwner, Observer {
            when (it) {
                SearchMovieActions.OnLoading -> binding.searchView.show()
                SearchMovieActions.OnError -> showError()
                is SearchMovieActions.OnMoviesRetrieved -> {
                    binding.progressBarMovies.hide()
                    if (it.result.isNotEmpty()) {
                        adapter.setMovies(it.result)
                    } else {
                        showEmpty()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        binding.recyclerViewMovies.adapter = null
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                showViews(true)
                searchMoviesViewModel.searchMovie(query)
                return true
            }
        })
    }

    private fun setupRecycleView() {
        binding.recyclerViewMovies.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun showError() {
        binding.progressBarMovies.hide()
        Toast.makeText(requireContext(), R.string.error_from_server, Toast.LENGTH_LONG).show()
    }

    private fun showEmpty() {
        showViews(false)
    }

    private fun showViews(show: Boolean) {
        binding.recyclerViewMovies.display(show)
        binding.progressBarMovies.display(show)
        binding.layoutEmpty.root.display(!show)
    }

}