package com.tunahanozatac.movieapp.ui.list

import com.tunahanozatac.movieapp.R
import com.tunahanozatac.movieapp.core.BaseViewHolder
import com.tunahanozatac.movieapp.databinding.CellItemBinding
import com.tunahanozatac.movieapp.domain.model.nowPlayingModel.MovieModel
import com.tunahanozatac.movieapp.util.Constants
import com.tunahanozatac.movieapp.util.extensions.convertToFormattedDate
import com.tunahanozatac.movieapp.util.extensions.loadWithGlide
import com.tunahanozatac.movieapp.util.extensions.setGone
import com.tunahanozatac.movieapp.util.extensions.setVisible

class ListViewHolder(
    private val binding: CellItemBinding
) : BaseViewHolder<MovieModel>(binding) {

    var clickListener: ClickListener? = null

    override fun bind(data: MovieModel, onItemClick: ((MovieModel) -> Unit)?) {
        super.bind(data, onItemClick)
        if (data.backdropPath == null) {
            binding.movieImageProgress.setGone()
            binding.movieImage.setImageResource(R.drawable.ic_error_48)
        } else {
            binding.movieImageProgress.setVisible()
            binding.movieImage.loadWithGlide(
                Constants.TMDB_IMAGE_URL.plus(data.backdropPath), binding.movieImageProgress
            )
        }

        binding.movieTitleTV.text = data.title
        binding.movieDescriptionTV.text = data.overview
        binding.movieDateTV.text = data.releaseDate.convertToFormattedDate()

        binding.root.setOnClickListener {
            clickListener?.onClick(data, bindingAdapterPosition)
        }
    }
}

interface ClickListener {
    fun onClick(item: MovieModel, newPosition: Int)
}