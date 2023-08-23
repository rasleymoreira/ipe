package com.example.institutodepesquisaseldorado.ui.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.institutodepesquisaseldorado.R
import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelData
import com.example.institutodepesquisaseldorado.databinding.ItemMovieBinding
import com.example.institutodepesquisaseldorado.ui.base.OnItemClickListener
import com.example.institutodepesquisaseldorado.util.loadImage

class MovieAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<MovieModelData, MovieAdapter.MovieViewHolder>(
    MovieComparator
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.root.setOnClickListener{
            item?.let {
                listener.onItemClick(it)
            }
        }
        holder.bind(item)
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieModelData?){
            movie?.let {
                binding.tvNameMovie.text = it.title
                binding.tvDescriptionMovie.text = it.overview
                loadImage(binding.imgMovie, it.posterPath)
            }
        }
    }
}

object MovieComparator : DiffUtil.ItemCallback<MovieModelData>() {
    override fun areItemsTheSame(oldItem: MovieModelData, newItem: MovieModelData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModelData, newItem: MovieModelData): Boolean {
        return oldItem == newItem
    }
}