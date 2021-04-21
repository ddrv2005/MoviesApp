package com.example.moviesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.models.Actor
import com.example.moviesapp.models.Movie

class ActorAdapter: RecyclerView.Adapter<ActorAdapter.ActorHolder>() {

    private var actor: List<Actor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        return ActorHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(actor[position])
    }

    override fun getItemCount(): Int = actor.size

    fun setMovies(actor: List<Actor>){
        this.actor = actor
        notifyDataSetChanged()
    }

    class ActorHolder(val view: ItemMovieBinding): RecyclerView.ViewHolder(view.root){
        fun render(movie: Actor){

        }
    }
}