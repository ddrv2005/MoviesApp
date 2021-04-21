package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.models.Movie
import com.example.moviesapp.utils.loadImage
import com.squareup.picasso.Picasso

class MovieAdapter(val listener: (Int) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var movies: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(movies[position], listener)
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieHolder(val view: ItemMovieBinding): RecyclerView.ViewHolder(view.root){
        fun render(movie: Movie, listener: (Int) -> Unit){
            view.textViewName.text = movie.title
            movie.posterPath?.let {
                view.imageViewMovie.loadImage(it)
            }
            view.root.setOnClickListener {
                listener(movie.id)
            }
        }
    }
}