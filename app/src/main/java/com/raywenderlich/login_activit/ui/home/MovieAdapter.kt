package com.raywenderlich.login_activit.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.data.Results
import com.raywenderlich.login_activit.databinding.MovieItemBinding
import com.raywenderlich.login_activit.util.ImgListener


class MovieAdapter(private val imgListener: ImgListener): PagingDataAdapter<Results, MovieAdapter.MovieViewHolder>(UserComparator) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    companion object{
        const val img500 = "https://image.tmdb.org/t/p/w200/"
    }


    inner class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        fun bind(){
            binding.moveiImg.setOnClickListener(this)
            binding.movieNameTxt.text =getItem(absoluteAdapterPosition)?.title.toString()
            itemView.context
            Glide.with(itemView.context).load(img500 +getItem(absoluteAdapterPosition)?.posterPath)
                .into(binding.moveiImg)

        }

        override fun onClick(v: View?) {
            imgListener.onClick(getItem(absoluteAdapterPosition)?.id,getItem(absoluteAdapterPosition)?.posterPath,true)
        }
    }

    object UserComparator : DiffUtil.ItemCallback<Results>(){
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

    }

}