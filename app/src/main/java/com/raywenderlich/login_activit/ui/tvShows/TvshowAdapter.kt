package com.raywenderlich.login_activit.ui.tvShows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.data.TvShows
import com.raywenderlich.login_activit.databinding.MovieItemBinding
import com.raywenderlich.login_activit.util.ImgListener

class TvshowAdapter(private val imgListener: ImgListener): PagingDataAdapter<TvShows, TvshowAdapter.TvshowViewHolder>(UserComparator) {



    companion object{
        const val img = "https://image.tmdb.org/t/p/w200/"
    }


    inner class TvshowViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        fun bind(){
            binding.moveiImg.setOnClickListener(this)
            binding.movieNameTxt.text = getItem(absoluteAdapterPosition)?.originalName.toString()
            Glide.with(itemView.context).load(img+getItem(absoluteAdapterPosition)?.posterPath)
                .into(binding.moveiImg)
        }

        override fun onClick(v: View?) {
            imgListener.onClick(getItem(absoluteAdapterPosition)?.id,getItem(absoluteAdapterPosition)?.posterPath,false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowAdapter.TvshowViewHolder {
        return TvshowViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TvshowAdapter.TvshowViewHolder, position: Int) {
        holder.bind()
    }

    object UserComparator : DiffUtil.ItemCallback<TvShows>(){
        override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
            return  oldItem == newItem
        }

    }


}