package com.ananda.movieapidb.view.adapter

import android.view.View
import com.ananda.movieapidb.R
import com.ananda.movieapidb.models.Resource
import com.ananda.movieapidb.models.entity.Person
import com.ananda.movieapidb.view.viewholder.PeopleViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow
import com.skydoves.whatif.whatIfNotNull

class PeopleAdapter : BaseAdapter() {

    init {
        addSection(ArrayList<Person>())
    }

    fun addPeople(resource: Resource<List<Person>>) {
        resource.data.whatIfNotNull {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_person

    override fun viewHolder(layout: Int, view: View) = PeopleViewHolder(view)
}
