package com.movie.db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.movie.db.R
import com.movie.db.model.ResultX
import pokemon.co.id.connection.Host.URLIMAGE

class MovieHorizontalAdapter
    (
        private val onItemClicked: (position: Int) -> Unit
    ) :

    RecyclerView.Adapter<MovieHorizontalAdapter.ViewHolder>() {

    var items = ArrayList<ResultX>()
    lateinit var mContext: Context
    private val lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.list_item_film_horizontal, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ResultX = items[position]
        Glide.with(mContext)
            .load(URLIMAGE + data.poster_path)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_image)
                    .transform(RoundedCorners(16))
            )
            .into(holder.imgPhoto)
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
        itemView: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imgPhoto: ImageView

        init {
            imgPhoto = itemView.findViewById(R.id.imgPhoto)
            itemView.setOnClickListener(this)
        }

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
