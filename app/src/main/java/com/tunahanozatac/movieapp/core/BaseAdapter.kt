package com.tunahanozatac.movieapp.core

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffCallback) {

    abstract fun bindView(holder: VH, position: Int)
    abstract fun createView(
        context: Context, parent: ViewGroup, inflater: LayoutInflater, viewType: Int
    ): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createView(parent.context, parent, LayoutInflater.from(parent.context), viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bindView(holder, position)
    }
}