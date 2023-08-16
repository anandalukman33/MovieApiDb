package com.ananda.movieapidb.view.adapter

import android.view.View
import com.ananda.movieapidb.R
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.entity.Genre
import com.ananda.movieapidb.view.viewholder.GenreListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.whatif.whatIfNotNull

class GenreListAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<Genre>())
    }

    fun addGenreList(resource: Resource<List<Genre>>) {
        resource.data.whatIfNotNull {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_genre

    override fun viewHolder(layout: Int, view: View) = GenreListViewHolder(view)
}