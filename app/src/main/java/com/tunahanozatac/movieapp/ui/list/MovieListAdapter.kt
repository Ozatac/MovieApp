package com.tunahanozatac.movieapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.movieapp.core.BaseAdapter
import com.tunahanozatac.movieapp.databinding.CellItemBinding
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieModel
import com.tunahanozatac.movieapp.util.adapter.getDiffUtilCallBack

class MovieListAdapter(
    var onClick: (MovieModel) -> Unit,
) : BaseAdapter<MovieModel, RecyclerView.ViewHolder>(
    getDiffUtilCallBack()
), ClickListener {
    private lateinit var bindingItemListBinding: CellItemBinding
    var listener: ItemMovieClickListener? = null

    override fun bindView(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as ListViewHolder).bind(it) {
                onClick.invoke(it)
            }
            holder.clickListener = this
        }
    }

    override fun createView(
        context: Context, parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): RecyclerView.ViewHolder {
        bindingItemListBinding = CellItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(bindingItemListBinding)
    }

    override fun onClick(item: MovieModel, newPosition: Int) {
        listener?.onButtonClick(item = item)
    }
}

interface ItemMovieClickListener {
    fun onButtonClick(item: MovieModel)
}