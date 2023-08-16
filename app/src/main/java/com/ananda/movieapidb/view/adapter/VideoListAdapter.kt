package com.ananda.movieapidb.view.adapter

import android.view.View
import com.ananda.movieapidb.R
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.Video
import com.ananda.movieapidb.view.viewholder.VideoListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.whatif.whatIfNotNull

class VideoListAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<Video>())
    }

    fun addVideoList(resource: Resource<List<Video>>) {
        resource.data.whatIfNotNull {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_video

    override fun viewHolder(layout: Int, view: View) = VideoListViewHolder(view)
}