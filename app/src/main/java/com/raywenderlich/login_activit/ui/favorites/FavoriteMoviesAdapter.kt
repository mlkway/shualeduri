package com.raywenderlich.login_activit.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.databinding.FavItemBinding
import com.raywenderlich.login_activit.storage.SavedMovies
import com.raywenderlich.login_activit.util.ImgListener

class FavoriteMoviesAdapter(private val imgListener: ImgListener): RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {

    var data: List<SavedMovies> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesAdapter.ViewHolder {
        return ViewHolder(FavItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteMoviesAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount()=data.size

    inner class ViewHolder(private val binding: FavItemBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        fun bind(){
            binding.imageView4.setOnClickListener(this)
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w200/"+ data[absoluteAdapterPosition].poster)
                .into(binding.imageView4)
        }

        override fun onClick(v: View?) {
            imgListener.onClick(data[absoluteAdapterPosition].id,data[absoluteAdapterPosition].poster,data[absoluteAdapterPosition].isMovie)
        }
    }

}