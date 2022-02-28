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
import com.movie.db.databinding.ListGendreBinding
import com.movie.db.model.Genre
import com.movie.db.model.Result
import pokemon.co.id.connection.Host.URLIMAGE

class GenreHorizontalAdapter
    (
        private val onItemClicked: (position: Int) -> Unit
    ) :

    RecyclerView.Adapter<GenreHorizontalAdapter.ViewHolder>() {

    var items = ArrayList<Genre>()
    lateinit var mContext: Context
    private val lastPosition = -1
    private var viewBinding: ListGendreBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        viewBinding =
            ListGendreBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(viewBinding!!, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Genre = items[position]

        holder.viewBinding.tvGendre.setText(data.name)

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
        binding: ListGendreBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val viewBinding: ListGendreBinding

        init {
            viewBinding = binding
            binding.cvView.setOnClickListener(this)
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

    fun setDataList(data :  ArrayList<Genre>) {
        this.items = data
        notifyDataSetChanged()
    }
}
