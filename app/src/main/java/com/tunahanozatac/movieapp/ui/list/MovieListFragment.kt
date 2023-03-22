package com.tunahanozatac.movieapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.tunahanozatac.movieapp.R
import com.tunahanozatac.movieapp.core.BaseFragment
import com.tunahanozatac.movieapp.databinding.FragmentMovieListBinding
import com.tunahanozatac.movieapp.domain.model.NowPlayingModel.MovieModel
import com.tunahanozatac.movieapp.util.Constants
import com.tunahanozatac.movieapp.util.extensions.listen
import com.tunahanozatac.movieapp.util.extensions.setGone
import com.tunahanozatac.movieapp.util.extensions.setVisible
import com.tunahanozatac.movieapp.util.extensions.toast
import com.tunahanozatac.movieapp.util.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>(),
    ItemMovieClickListener {

    override val viewModel: MovieListViewModel by viewModels()

    private lateinit var adapter: MovieListAdapter

    override fun layoutResource(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMovieListBinding {
        return FragmentMovieListBinding.inflate(inflater, container, false)
    }

    override fun viewCreated() {
        setAdapter()
        setListeners()

        lifecycleScope.launchWhenCreated {
            viewModel.moviesList.collect {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect{
                val state = it.refresh
                binding?.loadingInc?.root?.isVisible = state is LoadState.Loading
            }
        }
    }

    private fun setListeners() {
        binding?.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getList()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setAdapter() {
        adapter = MovieListAdapter {}
        adapter.listener = this
        binding?.recyclerview?.adapter = adapter
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.recyclerview?.layoutManager = linearLayoutManager
        binding?.recyclerview?.addItemDecoration(
            DividerItemDecoration(
                context, linearLayoutManager.orientation
            )
        )
    }

    override fun observerData() {
        super.observerData()
        lifecycleScope.launch {
            viewModel.getList().listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        configureVisibility(binding?.imageSlider, true)
                        configureVisibility(binding?.errorInc?.root, false)
                        configureVisibility(binding?.loadingInc?.root, false)
                        val validMovies =
                            it.data?.results?.filter { filter -> filter.backdropPath != null }
                        val sliderIDList = validMovies?.map { sliderIDList -> sliderIDList.id }
                        val imageSliderList = validMovies?.map { imageSliderList ->
                            SlideModel(
                                Constants.TMDB_IMAGE_URL.plus(imageSliderList.backdropPath),
                                imageSliderList.title,
                                ScaleTypes.CENTER_CROP
                            )
                        }
                        imageSliderList?.let { it1 -> binding?.imageSlider?.setImageList(it1) }
                        binding?.imageSlider?.setItemClickListener(object : ItemClickListener {
                            override fun onItemSelected(position: Int) {
                                sliderIDList?.get(position)?.let { id ->
                                    navigateToDetails(id)
                                }
                            }
                        })
                    }
                    UIStatus.ERROR -> {
                        binding.apply {
                            binding?.imageSlider?.setGone()
                            binding?.errorInc?.root?.setVisible()
                            binding?.loadingInc?.root?.setGone()

                            binding?.errorInc?.apply {
                                animationView.setGone()
                                errorText.text = it.message
                            }
                        }
                    }
                    UIStatus.LOADING -> {
                        configureVisibility(binding?.imageSlider, false)
                        configureVisibility(binding?.errorInc?.root, false)
                        configureVisibility(binding?.loadingInc?.root, true)
                    }
                    else -> {
                        requireContext() toast getString(R.string.somethingWentWrong)
                    }
                }
            }
        }
    }

    private fun navigateToDetails(id: Int) {
        id.let {
            val action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(id)
            findNavController().navigate(action)
        }
    }

    override fun onButtonClick(item: MovieModel) {
        navigateToDetails(item.id)
    }
}