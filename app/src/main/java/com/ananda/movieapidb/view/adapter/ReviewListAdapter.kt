package com.ananda.movieapidb.view.adapter

import android.view.View
import com.ananda.movieapidb.R
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.Review
import com.ananda.movieapidb.view.viewholder.ReviewListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.whatif.whatIfNotNull

class ReviewListAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<Review>())
    }

    fun addReviewList(resource: Resource<List<Review>>) {
        resource.data.whatIfNotNull {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_review

    override fun viewHolder(layout: Int, view: View) = ReviewListViewHolder(view)
}
