package com.raywenderlich.login_activit.ui.detailMovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.data.movieCast.Cast
import com.raywenderlich.login_activit.databinding.CastItemBinding

class CastAdapter: RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    var data: List<Cast?> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CastItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()=data.size

    inner class ViewHolder(private val binding: CastItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.characterNameTxt.text = data[absoluteAdapterPosition]?.character
            binding.realNameTxt.text = data[absoluteAdapterPosition]?.name
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w200/"+data[absoluteAdapterPosition]?.profilePath)
                .into(binding.imageView2)


        }
    }

}