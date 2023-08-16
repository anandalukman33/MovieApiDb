package com.ananda.movieapidb.view.adapter

import android.view.View
import com.ananda.movieapidb.R
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.entity.Tv
import com.ananda.movieapidb.view.viewholder.TvListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.whatif.whatIfNotNull

class TvListAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<Tv>())
    }

    fun addTvList(resource: Resource<List<Tv>>) {
        resource.data.whatIfNotNull {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_poster

    override fun viewHolder(layout: Int, view: View) = TvListViewHolder(view)
}