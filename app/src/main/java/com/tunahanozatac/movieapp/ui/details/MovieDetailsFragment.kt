package com.tunahanozatac.movieapp.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.movieapp.core.BaseFragment
import com.tunahanozatac.movieapp.databinding.FragmentMovieDetailsBinding
import com.tunahanozatac.movieapp.ui.list.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>() {

    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMovieDetailsBinding {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false)
    }

    override fun viewCreated() {
        Log.d("MovieDetailsFragment", args.id.toString())
    }

    override fun observerData() {
        super.observerData()
    }
}