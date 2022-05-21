package com.example.myapplication.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MovieListItemViewBinding
import com.example.myapplication.model.Movie

typealias WordClickHandler = (Movie) -> Unit


class MovieAdaptor() :
    ListAdapter<Movie, MovieAdaptor.ItemHolder>(MovieDiffCallback) {

    class ItemHolder(val binding: MovieListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding: MovieListItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_item_view,
            parent,false
        )
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.movie = movie
        holder.bind(movie)
    }
}


object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}
