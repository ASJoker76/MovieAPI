package com.movie.db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.movie.db.R
import com.movie.db.databinding.ListItemFilmBinding
import com.movie.db.model.ResultX
import pokemon.co.id.connection.Host.URLIMAGE

class MovieAdapter
    (
        private val onItemClicked: (position: Int) -> Unit
    ) :

    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var items = ArrayList<ResultX>()
    lateinit var mContext: Context
    private val lastPosition = -1
    private var viewBinding: ListItemFilmBinding? = null
    private var Rating = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        viewBinding =
            ListItemFilmBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(viewBinding!!, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ResultX = items[position]

        Rating = data.vote_average
        holder.viewBinding.tvTitle.setText(data.title)
        holder.viewBinding.tvRealeseDate.setText(data.release_date)
        holder.viewBinding.tvDesc.setText(data.overview)

        val newValue = Rating.toFloat()
        holder.viewBinding.ratingBar.setNumStars(5)
        holder.viewBinding.ratingBar.setStepSize(0.5.toFloat())
        holder.viewBinding.ratingBar.setRating(newValue / 2)

        Glide.with(mContext)
            .load(URLIMAGE + data.poster_path)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_image)
                    .transform(RoundedCorners(16))
            )
            .into(holder.viewBinding.imgPhoto)

        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = FADE_DURATION.toLong()
            itemView.startAnimation(anim)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //Class Holder
    inner class ViewHolder(
        //itemView: View,
        binding: ListItemFilmBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val viewBinding: ListItemFilmBinding

        init {
            viewBinding = binding
            binding.cvFilm.setOnClickListener(this)
        }

//        init {
//            itemView.setOnClickListener(this)
//        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    companion object {
        private const val FADE_DURATION = 1000 //FADE_DURATION in milliseconds
    }

    fun setDataList(data :  ArrayList<ResultX>) {
        this.items = data
        notifyDataSetChanged()
    }
}
