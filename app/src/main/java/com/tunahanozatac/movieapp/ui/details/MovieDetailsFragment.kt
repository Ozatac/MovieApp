package com.tunahanozatac.movieapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.movieapp.R
import com.tunahanozatac.movieapp.core.BaseFragment
import com.tunahanozatac.movieapp.databinding.FragmentMovieDetailsBinding
import com.tunahanozatac.movieapp.util.Constants
import com.tunahanozatac.movieapp.util.extensions.*
import com.tunahanozatac.movieapp.util.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, DetailsViewModel>() {

    override val viewModel: DetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    private var movieID by Delegates.notNull<Int>()
    private lateinit var imdbID: String

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMovieDetailsBinding {
        return FragmentMovieDetailsBinding.inflate(inflater, container, false)
    }

    override fun viewCreated() {
        setListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = args.id
    }

    private fun setListeners() {
        binding?.imdbButton?.setOnClickListener {
            if (::imdbID.isInitialized) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(Constants.IMDB_REDIRECT_URL.plus(imdbID)))
                startActivity(intent)
            } else {
                showToast(context, "Please wait.")
            }
        }
    }

    override fun observerData() {
        super.observerData()
        lifecycleScope.launch {
            viewModel.getDetails(id = movieID).listen { response ->
                when (response.state) {
                    UIStatus.SUCCESS -> {
                        response.data?.apply {
                            this@MovieDetailsFragment.imdbID = imdbID
                            if (backdropPath != null) {
                                binding?.movieDetailsCollapsingToolbar?.setVisible()
                                binding?.movieDetailsProgressbar?.let {
                                    binding?.movieDetailsImage?.loadWithGlide(
                                        Constants.TMDB_IMAGE_URL.plus(backdropPath), it
                                    )
                                }
                            } else {
                                binding?.movieDetailsCollapsingToolbar?.setGone()
                            }
                            binding?.rateTV?.text = String.format("%.1f", voteAvg)
                            binding?.dateTV?.text = releaseDate.convertToFormattedDate()
                            binding?.titleTV?.text = title
                            binding?.movieDetailsTV?.text = overview
                        }

                        configureVisibility(binding?.loadingInc?.root, false)
                        configureVisibility(binding?.errorInc?.root, false)
                    }

                    UIStatus.ERROR -> {
                        configureVisibility(binding?.errorInc?.root, true)
                        configureVisibility(binding?.loadingInc?.root, false)
                        binding?.errorInc?.apply {
                            errorText.text = response.message
                        }
                    }

                    UIStatus.LOADING -> {
                        configureVisibility(binding?.loadingInc?.root, true)
                        configureVisibility(binding?.errorInc?.root, false)
                    }

                    else -> {
                        requireContext() toast getString(R.string.somethingWentWrong)
                    }
                }
            }
        }
    }
}