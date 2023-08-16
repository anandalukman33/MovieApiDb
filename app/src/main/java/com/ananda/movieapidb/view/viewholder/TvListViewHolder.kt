package com.ananda.movieapidb.view.viewholder

import android.view.View
import com.ananda.movieapidb.api.Api
import com.ananda.movieapidb.models.entity.Tv
import com.ananda.movieapidb.view.ui.details.tv.TvDetailActivity
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_poster.view.*

class TvListViewHolder constructor(
    val view: View
) : BaseViewHolder(view) {

    private lateinit var tv: Tv

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Tv) {
            tv = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_poster_title.text = tv.name
            tv.poster_path.let {
                Glide.with(context)
                    .load(Api.getPosterPath(it!!))
                    .listener(
                        GlidePalette.with(Api.getPosterPath(it))
                        .use(BitmapPalette.Profile.VIBRANT)
                        .intoBackground(item_poster_palette)
                        .crossfade(true))
                    .into(item_poster_post)
            }
        }
    }

    override fun onClick(v: View?) = TvDetailActivity.startActivityModel(context(), tv)

    override fun onLongClick(p0: View?) = false
}