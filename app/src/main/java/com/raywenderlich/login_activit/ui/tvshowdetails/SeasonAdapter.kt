package com.raywenderlich.login_activit.ui.tvshowdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.data.showdetails.Season
import com.raywenderlich.login_activit.databinding.EpisodeItemBinding

class SeasonAdapter: RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    var data: List<Season?> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonAdapter.ViewHolder {
        return ViewHolder(EpisodeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SeasonAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()=data.size

    inner class ViewHolder(private val binding: EpisodeItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.textView2.text = data[absoluteAdapterPosition]?.name
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w200/"+data[absoluteAdapterPosition]?.posterPath)
                .into(binding.imageView3)
        }
    }

}